package core.exception;

/**
 * TicketParseException
 * Date: 03/06/2023
 *
 * @author Yauheni Antsipenka
 */
public class TicketParseException extends RuntimeException {
    public TicketParseException(Exception e) {
        super(e);
    }
}
