
package projekat;

import projekat.CustomExceptions.*;

public interface Iznajmljivanje {
    
    void iznajmiVozilo(int voziloID, String i) throws VehicleNotFoundException, VehicleAlreadyRentedException;
    void vratiVozilo(int voziloID, String i) throws VehicleNotFoundException, VehicleNotRentedException;

}
