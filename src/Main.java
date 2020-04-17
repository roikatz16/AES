public class Main {
    public static void main(String[] args) throws Exception {
        String what_to_do = args[0];

        if(what_to_do.equals("-d") || what_to_do.equals("-e"))
            encryptDecrypt(what_to_do, args);
        else if(what_to_do.equals("-b"))
            breakEncryption(args);
    }

    private static void encryptDecrypt(String what_to_do, String[] paths) throws Exception {
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

        byte[] totalKey = Operation.readFileAsBytes(pathToKeyFile);
        byte[] message = Operation.readFileAsBytes(pathToInputFile);
       AES3 aes3 = new AES3(totalKey, message);
        byte[] output;
        if(what_to_do.equals("-e")){
            output =  aes3.encrypt();
        }
        else {
            output =  aes3.decrypt();
        }
        Operation.writeByesToFile(pathToOutputFile, output);
    }

    private static void breakEncryption(String[] paths){//todo!
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


}
