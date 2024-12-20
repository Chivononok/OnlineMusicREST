package onlineMusic.exceptions;

public class SubscriptionNotFoundException extends RuntimeException{
    public SubscriptionNotFoundException(Long id){
        super("could not find subscription " + id);
    }
}
