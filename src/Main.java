import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) throws Exception {
        String what_to_do = args[0];


        if(what_to_do.equals("-d") || what_to_do.equals("-e"))
            encryptDecrypt(what_to_do, args);
        else if(what_to_do.equals("-b"))
            breakEncryption(args);


    }

    private static void encryptDecrypt(String what_to_do, String[] paths) throws Exception {
        String pathToKeyFile = null;
        String pathToInputFile = null;
        String pathToOutputFile = null;

        for (int i = 0; i < paths.length; i++) {
            if(paths[i].equals("-k"))
                pathToKeyFile = paths[i+1];
            else if(paths[i].equals("-i"))
                pathToInputFile = paths[i+1];
            else if(paths[i].equals("-o"))
                pathToOutputFile = paths[i+1];
        }

        byte[] totalKey = Operation.readFileAsBytes(pathToKeyFile);
        byte[][] keys = Operation.transformingToMatrix(totalKey, 16);
        byte[] message = Operation.readFileAsBytes(pathToInputFile);
        AES3 aes3 = new AES3(keys, message);
        byte[] output;
        if(what_to_do.equals("-e")){
            output =  aes3.encrypt();
            String str = new String(output, StandardCharsets.UTF_8);
            System.out.println(str);
        }
        else {
            output =  aes3.decrypt();
            //String str = new String(output, StandardCharsets.UTF_8);
            //System.out.println(str);

        }
        Operation.writeByesToFile(pathToOutputFile, output);
    }

    private static void breakEncryption(String[] paths) throws Exception {//todo: test!
        String pathToPlainText = null;
        String pathToCipher = null;
        String pathToOutput = null;

        for (int i = 0; i < paths.length; i++) {
            if(paths[i].equals("-m"))
                pathToPlainText=paths[i+1];
            else if(paths[i].equals("-c"))
                pathToCipher=paths[i+1];
            else if(paths[i].equals("-o"))
                pathToOutput=paths[i+1];
        }

        byte[] plainText = Operation.readFileAsBytes(pathToPlainText);
        byte[] cypher = Operation.readFileAsBytes(pathToCipher);
        String str = new String(cypher, StandardCharsets.UTF_8);
        System.out.println(str);
        System.out.println();
        System.out.println("----------------");
        System.out.println();
        byte[] plainTextBlock = new byte[16];
        byte[] CypherTextBlock = new byte[16];
        for(int i=0; i<16; i++){
            plainTextBlock[i] = plainText[i];
            CypherTextBlock[i] = cypher[i];
        }

        byte[] K1 = Operation.randomKey(16);
        byte[] K2 = Operation.randomKey(16);
        byte[][] keys = new byte[][]{K1, K2, CypherTextBlock};
        AES3 aes3 = new AES3(keys, plainTextBlock);
        byte[] K3 = aes3.encrypt();
        keys[2]= K3;
        byte[] output = Operation.transformingToVector(keys);
        Operation.writeByesToFile(pathToOutput, output);
    }


}
