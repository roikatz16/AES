import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String pathToInputFile = "C:/Users/odeya/Desktop/AES test files/message_short";
       // testPrint(pathToInputFile);
        testReadWrite();
       // testXor();
    }

    private static void testReadWrite() throws IOException {
        String pathToRead = "C:/Users/odeya/Desktop/AES test files/test_input";
        String pathToWrite = "C:/Users/odeya/Desktop/AES test files/test_output";
        byte[] readAndWrite = Operation.readFileAsBytes(pathToRead);
        for (byte b: readAndWrite){
            System.out.print(b);
        }
        Operation.writeByesToFile(pathToWrite, readAndWrite);
    }

    private static void testXor() {
        byte a = (byte)12;
        byte b = (byte)95;
        byte c = (byte) (a ^ b);
        String s1 = String.format("%8s", Integer.toBinaryString(a & 0xFF)).replace(' ', '0');
        String s2 = String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0');
        String s3 = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
        System.out.println(s1); // 10000001
        System.out.println(s2); // 10000001
        System.out.println(s3); // 10000001
    }

    public static void testPrint(String path){
        byte[] message = Operation.readFileAsBytes(path);
        printByteArray(message);
        System.out.println("-------------------");
        byte[][] two_dimensions_array = Operation.transformingToMatrix(message, 4);
        print2dByteArray(two_dimensions_array);
    }

    public static void printByteArray(byte[] array){
        for(int i=0; i< array.length;i++){
            System.out.print( array[i] );
            System.out.print( ", " );
        }
    }

    public static void print2dByteArray(byte[][] array){
        for(int i=0; i< array.length;i++){
            printByteArray( array[i] );
            System.out.println();
        }
    }
}
