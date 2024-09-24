
package projekat.CustomExceptions;

public class VehicleNotFoundException extends Exception{
    public VehicleNotFoundException(String poruka){
        super(poruka);
    }
}
