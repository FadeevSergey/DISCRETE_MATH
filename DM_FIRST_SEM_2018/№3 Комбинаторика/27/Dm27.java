import java.io.*;
public class Dm27 {
    public static void main(String[] args) throws IOException {
        char[] array;
        int length;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("nextbrackets.in"), "UTF-8"))) {
            array = reader.readLine().toCharArray();
            length = array.length;
        }
        int close = 0;
        int open = 0;
        try (PrintWriter out = new PrintWriter("nextbrackets.out", "UTF-8")) {
            for (int i = length - 1; i >= 0; --i) {
                if (array[i] == '(') {
                    open++;
                    if (close > open) {
                        break;
                    }
                } else {
                    close++;
                }
            }
            length = length - close - open;
            if (length == 0) {
                out.print("-");
                out.close();
                return;
            } else {
                array[length] = ')';
                length++;
                for (int j = 0; j < open; ++j) {
                    array[length] = '(';
                    length++;
                }
                for (int j = 0; j < close - 1; ++j) {
                    array[length] = ')';
                    length++;
                }
            }
            for (int i = 0; i < length; ++i) {
                out.print(array[i]);
            }
        }
    }
}