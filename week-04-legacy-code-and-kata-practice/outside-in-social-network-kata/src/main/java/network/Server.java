package network;

public interface Server {

    String readNextRequest();

    void outputResponse(String response);

}
