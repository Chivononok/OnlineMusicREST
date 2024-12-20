package onlineMusic.exceptions;

public class SongNotFoundException extends RuntimeException{
    public SongNotFoundException(Long id){
        super("could not find song " + id);
    }
}
