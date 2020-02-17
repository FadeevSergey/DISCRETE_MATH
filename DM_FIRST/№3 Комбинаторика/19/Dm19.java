import java.io.*;
//neerc thank you <3
public class Dm19 {
    public static void main(String[] args) throws IOException {
        String strBr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("num2brackets2.in"), "UTF-8"))) {
            strBr = reader.readLine();
        }
        String[] a = strBr.split(" ");
        long n = Long.parseLong(a[0]);
        long k = Long.parseLong(a[1]) + 1;
        long size = n;
        long[][] arrayOfDinamics = new long[(int)(2 * size + 1)][(int)(size + 2)];
        arrayOfDinamics[0][0] = 1;
        for (int i = 0; i < 2 * size; ++i) {
            for (int j = 0; j < size + 2; ++j) {
                if (j + 1 <= size + 1) {
                    arrayOfDinamics[i + 1][j + 1] += arrayOfDinamics[i][j];
                }
                if (j > 0) {
                    arrayOfDinamics[i + 1][j - 1] += arrayOfDinamics[i][j];
                }
            }
        }
        long d = 0;
        char[] stack = new char[(int)size * 2];
        int lengthOfArray = 0;
        StringBuilder ans = new StringBuilder();
        for (int i = (int)size * 2 - 1; i > -1; --i) {
            long cur;
            if (d + 1 <= n) {
                cur = arrayOfDinamics[i][(int)d + 1] * (long)Math.pow(2, (i - d - 1) / 2);
            }
            else {
                cur = 0;
            }
            if (cur >= k) {
                ans.append('(');
                stack[lengthOfArray++] = '(';
                ++d;
                continue;
            }
            k -= cur;
            if (lengthOfArray > 0 && stack[lengthOfArray - 1] == '(' && d - 1 > -1) {
                cur = arrayOfDinamics[i][(int)d - 1] * (long)Math.pow(2, (i - d + 1) / 2);
            }
            else {
                cur = 0;
            }
            if (cur >= k) {
                ans.append(')');
                --lengthOfArray;
                --d;
                continue;
            }
            k -= cur;
            if (d + 1 <= n) {
                cur = arrayOfDinamics[i][(int)d + 1] * (long)Math.pow(2, ((i - d - 1) / 2));
            }
            else {
                cur = 0;
            }
            if (cur >= k) {
                ans.append('[');
                stack[lengthOfArray++] = '[';
                ++d;
                continue;
            }
            k -= cur;
            ans.append(']');
            --lengthOfArray;
            --d;
        }
        try (PrintWriter out = new PrintWriter("num2brackets2.out", "UTF-8")) {
            out.print(ans.toString());
        }
    }    
}