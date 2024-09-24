
package projekat.CustomExceptions;

public class VehicleAlreadyRentedException extends Exception{
    public VehicleAlreadyRentedException(String poruka){
        super(poruka);
    }
}
