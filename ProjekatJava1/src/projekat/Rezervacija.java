package projekat;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Rezervacija {
    private int rezervacijaID;
    private int voziloID;
    private LocalDate pocetak;
    private LocalDate kraj;
    private String ime;

    public Rezervacija(int rezervacijaID, int voziloID, LocalDate pocetak, LocalDate kraj, String ime) {
        this.rezervacijaID = rezervacijaID;
        this.voziloID = voziloID;
        this.pocetak = pocetak;
        this.kraj = kraj;
        this.ime = ime;
    }

    @Override
    public String toString() {
        return "ID rezervacije: " + rezervacijaID + "\nVoziloID: "  + voziloID + "\nPocetak: " + pocetak + "\nKraj: " + kraj + "\nime: " + ime;
    }
    
    public static void upisRezervacija(ArrayList<Rezervacija> rez){
        JSONArray jsonRezervacijeUpis = new JSONArray();
        FileWriter fw;
        for(Rezervacija r : rez){
            JSONObject jsonRezervacija = new JSONObject();
            jsonRezervacija.put("ID", r.rezervacijaID);
            jsonRezervacija.put("voziloID", r.voziloID);
            jsonRezervacija.put("pocetak", r.pocetak.toString());
            jsonRezervacija.put("kraj", r.kraj.toString());
            jsonRezervacija.put("ime", r.ime);
            jsonRezervacijeUpis.add(jsonRezervacija);
        }
        try{
            fw = new FileWriter("rezervacije.json");
            fw.write(jsonRezervacijeUpis.toJSONString()); 
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Upis nije uspesan.");
        }
             
    }
    
    public static ArrayList<Rezervacija> citanjeRezervacija(){
        ArrayList<Rezervacija> rez = new ArrayList<>();   
        File fajl = new File("rezervacije.json");
        
        if (fajl.length() == 0) 
            return rez;
            
        try{
            JSONArray jsonRezervacijeIspis = (JSONArray) new JSONParser().parse(new FileReader("rezervacije.json"));  
            for(Object o : jsonRezervacijeIspis){
            JSONObject obj= (JSONObject) o;
            int id = Integer.parseInt(obj.get("ID").toString());
            int voziloID = Integer.parseInt(obj.get("voziloID").toString());
            LocalDate pocetak = LocalDate.parse(obj.get("pocetak").toString());
            LocalDate kraj = LocalDate.parse(obj.get("kraj").toString());
            String ime = obj.get("ime").toString();
            rez.add(new Rezervacija(id, voziloID, pocetak, kraj, ime));
          }
        } catch (IOException | NumberFormatException | ParseException e){
            System.err.println("Citanje nije uspesno: " + e.getMessage());
        }
        return rez;
    }  
    
}
