import java.util.Arrays;
import java.util.Random;
import java.io.*;
import java.util.*;

public class App {
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void bubbleSort(int[] array) {
        for (int j = array.length - 1; j > 1; j--) {
            for (int i = 0; i < j; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                }
            }
        }
    }

    public static int [] createRandomArray(int arrayLength) {
        Random random = new Random();
        int[] a = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            a[i] = random.nextInt(0, 100);
        }
        return a;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\nType array length to create array or filename to read and sort a file \n\nType \"f\" for filename and \"a\" for array length");
        System.out.print(">");
        Scanner arrayOrFile = new Scanner(System.in);
        String arrayOrFileValue = arrayOrFile.nextLine();
        if (arrayOrFileValue.equals("a")) {
            System.out.println("\nWhat would you like the array length to be?");
            System.out.print(">");
            Scanner arrayLengthAsker = new Scanner(System.in);
            int arrayLength = arrayLengthAsker.nextInt();
            int [] a = createRandomArray(arrayLength);
            System.out.println("Unsorted Array: " + Arrays.toString(a));
            writeArrayToFile("temp.txt", a);
            arrayLengthAsker.close();
        } else {
            System.out.println("\nWhat is the name of the file?");
            System.out.print(">");
            Scanner askFilename = new Scanner(System.in);
            String filename = askFilename.nextLine();
            int [] redArray = readFiletoArray(filename);
            bubbleSort(redArray);
            System.out.println(Arrays.toString(redArray));
            writeArrayToFile("temp2.txt", redArray);
            askFilename.close();
        }
        arrayOrFile.close();
        
    
    }

    public static void writeArrayToFile(String filename, int[] a) {
        try {
            File file = new File(filename);
            file.createNewFile();
            FileWriter myWriter = new FileWriter(filename);
            for (int i : a) {
                myWriter.write(String.valueOf(i) + "\n");
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("The system could not write the array to file");
        }

    }

    public static int[] readFiletoArray(String filename) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                arrayList.add(Integer.parseInt(s));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("The system had an error");
        } catch (NumberFormatException e) {

        }

        int[] array = new int[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }

        return array;
    }
}
