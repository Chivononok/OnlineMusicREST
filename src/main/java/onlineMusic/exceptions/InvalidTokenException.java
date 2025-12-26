package onlineMusic.exceptions;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String txt){
        super(txt);
    }
}
