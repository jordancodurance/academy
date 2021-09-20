public class Copier {

    private final Source source;
    private final Destination destination;

    public Copier(Source source, Destination destination) {
        this.source = source;
        this.destination = destination;
    }

    public void copy() {
        char receivedCharacter = source.getChar();
        while (receivedCharacter != '\n') {
            destination.setChar(receivedCharacter);
            receivedCharacter = source.getChar();
        }
    }

}
