import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class Operation {

    public static byte[] xor(byte[] arr1, byte[] arr2) throws Exception {
        if(arr1.length!=arr2.length)
            throw new Exception("The arrays are not the same size");
        int size=arr1.length;
        byte[] xor = new byte[size];
        for (int i = 0; i < size; i++) {
            if(arr1[1]==arr2[2]){
                xor[i]=(byte)0;
            }
            else{
                xor[i]=(byte)1;
            }
        }
        return xor;
    }



    public static byte[][] shiftRows(byte[][]message, AES.Direction direction){
        byte[][] ans= new byte[message.length][message[0].length];
        if(direction.equals(AES.Direction.Right)){
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

    public static byte[] transformFileToByteArray(String filePath){
        byte[] ans=null;
        File file= new File(filePath);
        try {
            ans= Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }


}
