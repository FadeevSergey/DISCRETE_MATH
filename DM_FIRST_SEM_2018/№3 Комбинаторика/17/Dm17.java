import java.io.*;
public class Dm17 {
    public static void main(String[] args) throws IOException {
        String strBr;
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("num2brackets.in"), "UTF-8"))) {
            strBr = reader.readLine();
        }
        String[] a = strBr.split(" ");
        long n = Long.parseLong(a[0]);
        long k = Long.parseLong(a[1]);
        long size = n;
        String s = "";
        long d = 0;
        long[][] array = new long[(int)(2 * size + 1)][(int)(size + 2)];
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
        for (int i = 0; i < 2 * n; ++i) {
            if (array[(int)(2 * n - i - 1)][(int)(d + 1)] > k) {
                s += "(";
                d++;
            } else {
                k -= array[(int)(2 * n - i - 1)][(int)(d + 1)];
                s += ")";
                d--;
            }
        } 
        try (PrintWriter out = new PrintWriter("num2brackets.out", "UTF-8")) {
            out.print(s);
        }
    }    
}