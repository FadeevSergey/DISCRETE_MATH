import java.io.*;
public class Dm18 {
    public static void main(String[] args) throws IOException {
        String strBr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("brackets2num.in"), "UTF-8"))) {
            strBr = reader.readLine();
            
        }
        int size = strBr.length() / 2;
        long num = 0;
        int d = 0;
        long[][] array = new long[2 * size + 1][size + 2];
        array[0][0] = 1;
        for (int i = 0; i < 2 * size; ++i) {
            for (int j = 0; j < size + 2; ++j) {
                if (j + 1 <= size + 1) {
                    array[i + 1][j + 1] += array[i][j];
                }
                if (j > 0) {
                    array[i + 1][j - 1] += array[i][j];
                }
            }
        }
        for (int i = 0; i < 2 * size; ++i) {
            if (strBr.charAt(i) == '(') {
                d++;
            } else {
                num += array[2 * size - i - 1][d + 1];
                d--;
            }
        }
        try (PrintWriter out = new PrintWriter("brackets2num.out", "UTF-8")) {
            out.print(num);
        }
    }    
}