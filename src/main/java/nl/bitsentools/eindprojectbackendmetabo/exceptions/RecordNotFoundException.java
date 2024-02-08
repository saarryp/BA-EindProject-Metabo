package nl.bitsentools.eindprojectbackendmetabo.exceptions;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super("Product niet gevonden");
    }

    public RecordNotFoundException(String message) {
        super(message);

    }
}
