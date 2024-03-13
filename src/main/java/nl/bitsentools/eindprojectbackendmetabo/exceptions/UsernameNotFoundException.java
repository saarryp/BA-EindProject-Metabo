package nl.bitsentools.eindprojectbackendmetabo.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String message){
        super(message);
    }
}
