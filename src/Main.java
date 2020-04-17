import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        String what_to_do = args[0];

        if(what_to_do.equals("-d") || what_to_do.equals("-e"))
            encryptDecrypt(what_to_do, args);
        else if(what_to_do.equals("-b"))
            breakEncryption(args);
    }

    private static void encryptDecrypt(String what_to_do, String[] paths){
        String pathToKeyFile = "";
        String pathToInputFile = "";
        String pathToOutputFile = "";

        for (int i = 0; i < paths.length; i++) {
            if(paths[i].equals("-k"))
                pathToKeyFile = paths[i+1];
            else if(paths[i].equals("-i"))
                pathToInputFile = paths[i+1];
            else if(paths[i].equals("-o"))
                pathToOutputFile = paths[i+1];
        }

        byte[] totalKey = Operation.transformFileToByteArray(pathToKeyFile);
        byte[][] keys = Operation.transformingToMatrix(totalKey, 16);
        byte[] message = Operation.transformFileToByteArray(pathToInputFile);
        AES aes = new AES(totalKey);//todo: change AES to 3AES
        byte [] cypher = aes.encrypt(message);

    }

    private static void breakEncryption(String[] paths){
        String pathToMessage;
        String pathToCipher;
        String pathToOutput;

        for (int i = 0; i < paths.length; i++) {
            if(paths[i].equals("-m"))
                pathToMessage=paths[i+1];
            else if(paths[i].equals("-c"))
                pathToCipher=paths[i+1];
            else if(paths[i].equals("-o"))
                pathToOutput=paths[i+1];
        }


    }



    private byte[][] findKeys(String keyPath){
        return null;
    }
}
