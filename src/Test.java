import java.io.IOException;

public class Test {
    public static void main(String[] args) throws Exception {
        String pathToInputFile = "C:/Users/odeya/Desktop/AES test files/message_short";
       // testPrint(pathToInputFile);
        //testReadWrite();
       // testXor();
        //testShiftColumns();
        //testTransforms();
//        testEncryption();
        breakEncryption();
    }

    public static void breakEncryption() throws Exception {
        String[] arguments = new String[7];
        arguments[0] = "-b";
        arguments[1] = "-m";
        arguments[2] = "C:/Users/odeya/Desktop/AES test files/message_long";
        arguments[3] = "-c";
        arguments[4] = "C:/Users/odeya/Desktop/AES test files/cipher_long";
        arguments[5] = "-o";
        arguments[6] = "C:/Users/odeya/Desktop/AES test files/test_output4";
        Main.main(arguments);

        String[] arguments2 = new String[7];
        arguments2[0] = "-e";
        arguments2[1] = "-k";
        arguments2[2] = "C:/Users/odeya/Desktop/AES test files/test_output4";
        arguments2[3] = "-i";
        arguments2[4] = "C:/Users/odeya/Desktop/AES test files/message_long";
        arguments2[5] = "-o";
        arguments2[6] = "C:/Users/odeya/Desktop/AES test files/test_please";
        Main.main(arguments2);
    }

    private static void testEncryption() throws Exception {
        String[] arguments = new String[7];
        arguments[0] = "-e";
        arguments[1] = "-k";
        arguments[2] = "C:/Users/odeya/Desktop/AES test files/key_short";
        arguments[3] = "-i";
        arguments[4] = "C:/Users/odeya/Desktop/AES test files/message_short";
        arguments[5] = "-o";
        arguments[6] = "C:/Users/odeya/Desktop/AES test files/test_output";
        Main.main(arguments);

        String[] arguments2 = new String[7];
        arguments2[0] = "-d";
        arguments2[1] = "-k";
        arguments2[2] = "C:/Users/odeya/Desktop/AES test files/key_short";
        arguments2[3] = "-i";
        arguments2[4] = "C:/Users/odeya/Desktop/AES test files/test_output";
        arguments2[5] = "-o";
        arguments2[6] = "C:/Users/odeya/Desktop/AES test files/test_output2";
        Main.main(arguments2);
    }

    private static void testTransforms(){
        byte[] test = new byte[16];
        for(int i=0; i<test.length; i++){
            test[i] = (byte)(i+1);
        }
        printByteArray(test);
        System.out.println();
        System.out.println("--------------");
        byte[][] matrix = Operation.transformingToMatrix(test, 4);
        print2dByteArray(matrix);
        System.out.println("--------------");
        System.out.println();
        byte[] vector = Operation.transformingToVector(matrix);
        printByteArray(vector);
    }

    private static void testShiftColumns(){
                byte[]t={0,1,2,3};
        byte[][] test=new byte[4][4];
        for(int i=0; i<4;i++){
            for(int j=0;j<4;j++) {
                test[i][j] = (byte)i;
                System.out.print(test[i][j]);
            }
            System.out.println("\n");
        }
        byte[][] up=Operation.shiftColumns(test,AES.Direction.Up);
        System.out.println("\n");
        for(int i=0; i<4;i++){
            for(int j=0;j<4;j++) {
                System.out.print(up[i][j]);
            }
            System.out.println("\n");
        }

        byte[][] down =Operation.shiftColumns(up,AES.Direction.Down);
        System.out.println("\n");
        for(int i=0; i<4;i++){
            for(int j=0;j<4;j++) {
                System.out.print(down[i][j]);
            }
            System.out.println("\n");
        }
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

    public static void testPrint(String path) throws IOException {
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
