import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author Shivashriganesh Mahato
 */
public class Main {
    // Name of file to print results to
    private static final String FileName = "vals.txt";
    // Minimum and maximum values to test
    private static final int Min = 1;
    private static final int Max = (int) Math.pow(2, 16) / 2;

    public static void main(String[] args) {
        // Ensure min is greater than max
        if (Min > Max) {
            System.out.println("Min can't be greater than Max!");
            System.exit(1);
        }

        BufferedWriter bw = null;
        FileWriter fw = null;

        StringBuilder content = new StringBuilder();

        // Loop through ever number from min to max (in BigIntegers)
        for (BigInteger i = new BigInteger(Integer.toString(Min));
             i.compareTo(new BigInteger(Integer.toString(Max))) <= 0;
             i = i.add(new BigInteger("1"))) {
            // Set a local variable num to the current iteration number
            BigInteger num = i.add(new BigInteger("0"));

            // Continue process until num is 1; if it never reaches 1 (which theoretically shouldn't happen), an
            // infinite loop will occur
            while (!num.equals(new BigInteger("1"))) {
                content.append(num).append(", ");
                if (num.mod(new BigInteger("2")).equals(new BigInteger("0"))) {
                    // If the number is even, divide it by 2
                    num = num.divide(new BigInteger("2"));
                } else {
                    // If the number is odd, multiply it by 3 and add 1
                    num = num.multiply(new BigInteger("3"));
                    num = num.add(new BigInteger("1"));
                }
            }
            content.append(num).append("\n");
        }

        // Print results to file
        try {
            fw = new FileWriter(FileName);
            bw = new BufferedWriter(fw);
            bw.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Checkpoint for completion
        System.out.println("Program complete. Results printed to " + FileName);
    }
}
