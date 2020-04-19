public class AES {

    enum Direction {Up,Down};
    byte[] key;
    byte[] plaintext;
    byte[] cypher;

    public AES(byte[] key) {
        this.key = key;
        this.cypher=null;
        this.plaintext=null;
    }

    public byte[] encrypt(byte[] block) throws Exception {
        this.plaintext=block;
        /* 1. convert to matrix  */
        byte[][] matrix = Operation.transformingToMatrix(block,4);

        byte[][] matrixTras=Operation.matrixTransition1(matrix);
        /* 2. shift columns  */
        byte[][] shiftedColumns = Operation.shiftColumns(matrixTras, Direction.Up);

        /* 3. convert back to vector  */
       byte[] backToVector = Operation.transformingToVector(shiftedColumns);

       /* 4. xor xor gossip girl */
        byte[] xored = Operation.xor(backToVector, key);

        this.cypher = xored;
        return cypher;
    }


    public byte[] decrypt (byte[] block) throws Exception {
        this.cypher=block;

        /* 1. xor xor gossip girl  */
        byte[] xored = Operation.xor(cypher, key);

        /* 2. convert to matrix  */
        byte[][] matrix = Operation.transformingToMatrix(xored, 4);

        /* 3. shift columns  */
        byte[][] shiftedColumns = Operation.shiftColumns(matrix, Direction.Down);

        byte[][] matrixTran=Operation.matrixTransition2(shiftedColumns);
        /* 3. convert back to vector  */
        byte[] backToVector = Operation.transformingToVector(matrixTran);

        this.plaintext = backToVector;
        return plaintext;
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
