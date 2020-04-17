public class Test {
    public static void main(String[] args) {
        String pathToInputFile = "C:/Users/odeya/Desktop/AES test files/message_short";
        testPrint(pathToInputFile);
    }

    public static void testPrint(String path){
        byte[] message = Operation.transformFileToByteArray(path);
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
