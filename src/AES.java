public class AES {

    enum Direction {Right,Left};
    byte[] key;
    byte[] plaintext;
    byte[] cypher;

    public AES(byte[] key) {
        this.key = key;
        this.cypher=null;
        this.plaintext=null;
    }

    public byte[] encrypt(byte[] message){
        this.plaintext=message;
        byte[][] splitMessage=Operation.transformingToMatrix(message,key.length);
        byte[][] shiftedRows=Operation.shiftRows(splitMessage, Direction.Right);
        byte[][] encrypted=xorWithKey(shiftedRows);
        byte[] ans=Operation.transformingToVector(encrypted);
        this.cypher=ans;
        return ans;
    }


    public byte[] decrypt (byte[] encryptedMessage){
        this.cypher=encryptedMessage;
        byte[][] splitMessage=Operation.transformingToMatrix(encryptedMessage,key.length);
        byte[][] decrypted= xorWithKey(splitMessage);
        byte[][] shiftedRows=Operation.shiftRows(splitMessage, Direction.Left);
        byte[] ans=Operation.transformingToVector(shiftedRows);
        this.plaintext=ans;
        return ans;
    }


    private byte[][] xorWithKey(byte[][] shiftedRows) {
        byte[][] ans = new byte[shiftedRows.length][];
        for (int i = 0; i < ans.length; i++) {
            try {
                ans[i] = Operation.xor(shiftedRows[i],this.key);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ans;
    }



    public byte[] getKey() {
        return key;
    }

    public byte[] getPlaintext() {
        return plaintext;
    }

    public byte[] getCypher() {
        return cypher;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public void setPlaintext(byte[] plaintext) {
        this.plaintext = plaintext;
    }

    public void setCypher(byte[] cypher) {
        this.cypher = cypher;
    }
}
