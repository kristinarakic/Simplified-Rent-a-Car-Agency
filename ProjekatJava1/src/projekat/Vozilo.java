
package projekat;

public abstract class Vozilo {
    private int ID;
    private String marka;
    private String model;
    private int godinaProizvodnje;
    private double cenaPoDanu;
    private boolean dostupnost; 

    public Vozilo(int ID, String marka, String model, int godinaProizvodnje, double cenaPoDanu) {
        this.ID = ID;
        this.marka = marka;
        this.model = model;
        this.godinaProizvodnje = godinaProizvodnje;
        this.cenaPoDanu = cenaPoDanu;
        this.dostupnost = true;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public int getID() {
        return ID;
    }
    
    public boolean daLiJeDostupno() {
        return dostupnost;
    }

    public void setDostupnost(boolean dostupno) {
        this.dostupnost = dostupno;
    }

    public abstract void prikaziInfo();
    
}
