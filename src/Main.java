import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {

        String flag=args[0];//1?
        if(flag.equals("-e") || flag.equals("-d")){
            try {
                String keyPath = args[2];
                String inputPath = args[4];
                String outputPath = args[6];
            }catch (Exception e){
                System.out.println("One or more of the details missing");
            }


        }
        System.out.println("by");


    }

    private static byte[] transformFileToByteArray(String filePath){
        byte[] ans=null;
        File file= new File(filePath);
        try {
            ans= Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    private byte[][] findKeys(String keyPath){
        return null;
    }
}
