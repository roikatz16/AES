public class AES3 {

    private AES [] ASEs;
    private byte[][] keys;
    private byte[] message;
    private byte[] cypher;

    public AES3 (byte[][] keys, byte[] message) {
        ASEs = new AES[3];
        this.keys = keys;
        this.message = message;
        this.cypher = new byte[message.length];
    }

    public byte[] encrypt(){
        ASEs[0] = new AES(keys[0]);
        ASEs[1] = new AES(keys[1]);
        ASEs[2] = new AES(keys[2]);

        return null;
    }


}
