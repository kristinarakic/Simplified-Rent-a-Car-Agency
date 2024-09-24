
package projekat;

import java.util.ArrayList;
import java.util.HashMap;
import projekat.CustomExceptions.*;

public class Provera implements Iznajmljivanje{
    private ArrayList<Vozilo> vozila;
    private HashMap<String, Integer> korisnici;

    public Provera() {
        vozila = new ArrayList<>();
        korisnici = new HashMap<>();
    }
    
    public ArrayList<Vozilo> getVozila() {
        return vozila;
    }

    public void dodajVozilo(Vozilo v) {
        vozila.add(v);
    }
    
    @Override
    public void iznajmiVozilo(int voziloID, String i) throws VehicleNotFoundException, VehicleAlreadyRentedException {
        Vozilo v = pronadjiVozilo(voziloID);
        if (v != null) {
            if (!v.daLiJeDostupno()) {
                throw new VehicleAlreadyRentedException("Vozilo je vec iznajmljeno.");
            }
            korisnici.put(i, voziloID);
            System.out.println("Iznajmljeno vozilo:  " + v.getMarka() + " " + v.getModel());
            System.out.println("Vozilo iznajmio/la: " + i);
            v.setDostupnost(false);
        } else {
            throw new VehicleNotFoundException("Vozilo, ID: " + voziloID + " ne postoji u sistemu.");
        }
    }

    @Override
    public void vratiVozilo(int voziloID, String i) throws VehicleNotFoundException, VehicleNotRentedException  {
        int ID = voziloID;    
        String ime = i;
        Vozilo v = pronadjiVozilo(ID);
        if(v == null)
            throw new VehicleNotFoundException("Vozilo, ID: " + voziloID + " ne postoji u sistemu.");
        
        for (HashMap.Entry<String, Integer> unos : korisnici.entrySet()) {
            if (korisnici.isEmpty()) 
                System.out.println("Vozilo nije rezervisano.");
            while (!unos.getKey().equals(ime) || !unos.getValue().equals(ID)) {
                if (unos.getKey().equals(ime) && !unos.getValue().equals(ID)) {
                    System.err.println("Unet pogresan ID.");
                    ID = Agencija.unosID();
                } else if (!unos.getKey().equals(ime) && unos.getValue().equals(ID)) {
                    System.err.println("Uneto pogresno ime.");
                    ime = Agencija.unosImena();
                } else {
                    System.err.println("Uneti neispravni podaci.");
                }
            } 
            v = pronadjiVozilo(ID);
            System.out.println("Vraceno vozilo:  " + v.getMarka() + " " + v.getModel());
            System.out.println("Vozilo vratio/la: " + ime);
            korisnici.remove(ime);
            v.setDostupnost(true);
        }        
       
    }
    
    private Vozilo pronadjiVozilo(int voziloID) {
        for (Vozilo v : vozila) {
            if (v.getID() == voziloID) {
                return v;
            }
        }
        return null;
    }
    
    public void rezervisiVozilo(int voziloID) throws VehicleNotFoundException, VehicleAlreadyRentedException  {           
        Vozilo v = pronadjiVozilo(voziloID);
        if (v != null) {
            if (!v.daLiJeDostupno()) {
                throw new VehicleAlreadyRentedException("Vozilo je vec rezervisano.");
            }
            v.setDostupnost(false);          
        } else {
            throw new VehicleNotFoundException("Vozilo, ID: " + voziloID + " ne postoji u sistemu.");
        }
    }
}
