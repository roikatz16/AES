import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Operation {

    public static byte[] xor(byte[] arr1, byte[] arr2) throws Exception {
        if(arr1.length!=arr2.length) {
            throw new Exception("The arrays are not the same size");
        }
        byte[] xor = new byte[arr1.length];
        int i=0;
        for (byte b : arr1) {
            xor[i] = (byte) (b ^ arr2[i]); // '^' is the xor operator (java)
            i++;
        }
        return xor;
    }



    public static byte[][] shiftColumns(byte[][]message, AES.Direction direction){//todo: change!
        byte[][] ans= new byte[message.length][message[0].length];
        if(direction.equals(AES.Direction.Up)){
            for(int i=1;i<message.length;i++){
                for (int j = 0; j < message[i].length; j++) {
                    int newIndex = (j+i)%message[i].length;
                    ans[newIndex] = message[i];
                }
            }
        }
        else{
            for(int i=1;i<message.length;i++){
                for (int j = 0; j < message[i].length; j++) {
                    int newIndex = (j-i+ message[i].length)% message[i].length;
                    ans[newIndex] = message[i];
                }
            }
        }
        return ans;
    }

    public static byte[] transformingToVector(byte[][] array){
        byte[] ans = new byte[array.length*array[0].length];
        int index=0;
        for(int i=0;i<array.length;i++){
            for(int j=0; j<array[i].length;j++){
                ans[index]=array[i][j];
                index++;
            }
        }
        return ans;
    }

    public static byte[][] transformingToMatrix(byte[] messToSplit, int sizeOfRow){
        ArrayList<byte[]> rows=new ArrayList<>();
        int index=0;
        while(index<messToSplit.length){
            byte[] temp=new byte[sizeOfRow];
            for(int i=0; i<temp.length && index<messToSplit.length; i++){
                temp[i]=messToSplit[index];
                index++;
            }
            rows.add(temp);
        }

        byte[][] ans=new byte[rows.size()][];
        for(int i=0;i<rows.size();i++){
            ans[i]=rows.get(i);
        }
        return ans;
    }

    public static byte[] readFileAsBytes(String filePath){
        byte[] ans=null;
        File file= new File(filePath);
        try {
            ans= Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static void writeByesToFile(String filePath, byte[] output) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        fileOutputStream.write(output);
    }



}
