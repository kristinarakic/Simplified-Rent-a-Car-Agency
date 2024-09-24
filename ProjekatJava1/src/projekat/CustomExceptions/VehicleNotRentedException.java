
package projekat.CustomExceptions;

public class VehicleNotRentedException extends Exception{
    public VehicleNotRentedException(String poruka){
        super(poruka);
    }
}
