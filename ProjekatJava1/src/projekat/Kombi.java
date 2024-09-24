
package projekat;

public class Kombi extends Vozilo{
    private double teret;

    public Kombi(int ID, String marka, String model, int godinaProizvodnje, double cenaPoDanu, boolean dostupnost, double teret) {
        super(ID, marka, model, godinaProizvodnje, cenaPoDanu);
        this.teret = teret;
    }
    
    @Override
    public void prikaziInfo() {
          String dostupnostTekst;
             if (daLiJeDostupno()) {
                    dostupnostTekst = "Dostupno";
             } else {
                    dostupnostTekst = "Nije dostupno";
            }
        System.out.println("\nKombi: " + getMarka() 
                + " \nID vozila: " + getID() 
                + " \nModel: " + getModel() 
                + " \nGod. proizvodnje: " + getGodinaProizvodnje()
                + " \nCena po danu: " + getCenaPoDanu() 
                + " \nDostupnost: " + dostupnostTekst 
                + " \nTeret: " + teret + "kg");

    }
    
}
