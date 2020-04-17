public class AES3 {

    private AES [] ASEs;
    private byte[][] splittedMessage;


    public AES3 (byte[][] keys, byte[] message) {
        ASEs = new AES[3];
        ASEs[0] = new AES(keys[0]);
        ASEs[1] = new AES(keys[1]);
        ASEs[2] = new AES(keys[2]);
        splittedMessage = Operation.transformingToMatrix(message, 16);
    }

    public byte[] encrypt() throws Exception {
        byte[][] temp = new byte[splittedMessage.length][16];
        for(int i = 0; i< splittedMessage.length; i++){
          temp[i] = encryptBlock(splittedMessage[i]);
        }
        byte[] cypher = Operation.transformingToVector(temp);
        return cypher;
    }

    private byte[] encryptBlock(byte[] block) throws Exception {
        byte[] cypher1 = ASEs[0].encrypt(block);
        byte[] cypher2 = ASEs[1].encrypt(cypher1);
        byte[] cypher = ASEs[2].encrypt(cypher2);

        return cypher;
    }

    public byte[] decrypt() throws Exception {
        byte[][] temp = new byte[splittedMessage.length][16];
        for(int i = 0; i< splittedMessage.length; i++){
            temp[i] = decryptBlock(splittedMessage[i]);
        }

        byte[] plaintext = Operation.transformingToVector(temp);
        return plaintext;
    }

    private byte[] decryptBlock(byte[] block) throws Exception {
        byte[] cypher2 = ASEs[0].decrypt(block);
        byte[] cypher1 = ASEs[1].decrypt(cypher2);
        byte[] plaintext = ASEs[2].decrypt(cypher1);

        return plaintext;
    }

    public AES[] getASEs() {
        return ASEs;
    }

    public void setASEs(AES[] ASEs) {
        this.ASEs = ASEs;
    }

    public byte[][] getSplittedMessage() {
        return splittedMessage;
    }

    public void setSplittedMessage(byte[][] splittedMessage) {
        this.splittedMessage = splittedMessage;
    }
}
