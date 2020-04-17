public class AES3 {

    private AES [] ASEs;
    private byte[][] keys;
    private byte[] plaintext;
    private byte[] cypher;

    public AES3 (byte[][] keys) {
        ASEs = new AES[3];
        this.keys = keys;
        ASEs[0] = new AES(keys[0]);
        ASEs[1] = new AES(keys[1]);
        ASEs[2] = new AES(keys[2]);
    }

    public byte[] encrypt(byte[] message){
        this.plaintext = message;
        byte[] cypher1 = ASEs[0].encrypt(plaintext);
        byte[] cypher2 = ASEs[1].encrypt(cypher1);
        this.cypher = ASEs[2].encrypt(cypher2);

        return cypher;
    }

    public byte[] decrypt(byte[] message){
        this.cypher = message;
        byte[] cypher2 = ASEs[0].decrypt(cypher);
        byte[] cypher1 = ASEs[1].decrypt(cypher2);
        this.plaintext = ASEs[2].decrypt(cypher1);

        return plaintext;
    }
}
