package onlineMusic.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String txt){
        super(txt);
    }
}
