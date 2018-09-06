package ElizabethMod.tools;

public class InvalidCommandException extends Exception{

    public InvalidCommandException(String reason) {
        super(reason);
    }
}
