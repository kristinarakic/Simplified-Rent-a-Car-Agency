
package projekat;

public class Automobil extends Vozilo {
    private int brojSedista; 

    public Automobil(int ID, String marka, String model, int godinaProizvodnje, double cenaPoDanu, boolean dostupnost, int brojSedista) {
        super(ID, marka, model, godinaProizvodnje, cenaPoDanu);
        this.brojSedista = brojSedista;
    }

    @Override
    public void prikaziInfo() {
        String dostupnostTekst;
             if (daLiJeDostupno()) {
                    dostupnostTekst = "Dostupno";
             } else {
                    dostupnostTekst = "Nije dostupno";
            }
        System.out.println("\nAutomobil: " + getMarka() 
                + " \nID vozila: " + getID()
                + " \nModel: " + getModel() 
                + " \nGod. proizvodnje: " + getGodinaProizvodnje() 
                + " \nCena po danu: " + getCenaPoDanu() 
                + " \nDostupnost: " + dostupnostTekst 
                + " \nBr. sedista: " + brojSedista);
    }
    
}
