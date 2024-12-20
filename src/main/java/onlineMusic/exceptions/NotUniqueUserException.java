package onlineMusic.exceptions;

public class NotUniqueUserException extends RuntimeException {
    public NotUniqueUserException(String txt){
        super(txt);
    }
}
