import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class Dm20 {
    public static void main(String[] args) throws IOException {
        String strBr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("brackets2num2.in"), "UTF-8"))) {
            strBr = reader.readLine();
        }
        int n = strBr.length() / 2;
        char[] chArr = strBr.toCharArray();
        long num = 0;
        int depth = 0;
        long[][] d = new long[2 * n + 1][n + 2];
        d[0][0] = 1;
        for (int i = 0; i < 2 * n; ++i) {
            for (int j = 0; j < n + 2; ++j) {
                if (j + 1 <= n + 1) {
                    d[i + 1][j + 1] += d[i][j];
                }
                if (j > 0) {
                    d[i + 1][j - 1] += d[i][j];
                }
            }
        }
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < 2 * n; ++i) {
            if (chArr[i] == '(') {
                list.add(chArr[i]);
                depth++;
                continue;
            }
            if (chArr[i] == ')') {
                if (depth + 1 >= 0 && (2 * n - i - 1) >= 2) { //for '('
                    num += d[2 * n - i - 1][depth + 1] * Math.pow(2, ((2 * n - i - 1 - (depth + 1)) / 2));
                }
                if (!list.isEmpty() && list.get(list.size() - 1) == '(') {
                    list.remove(list.size() - 1);
                } else {
                    list.add(chArr[i]);
                }
                depth--;
                continue;
            }
            if (chArr[i] == '[') {
                if (depth + 1 >= 0) { //for '('
                    num += d[2 * n - i - 1][depth + 1] * Math.pow(2, ((2 * n - i - 1 - (depth + 1)) / 2));
                }
                if (depth - 1 >= 0 && list.size() > 0 && list.get(list.size() - 1) == '(') { //for ')'
                    num += d[2 * n - i - 1][depth - 1] * Math.pow(2, ((2 * n - i - 1 - (depth - 1)) / 2));
                }
                list.add(chArr[i]);
                depth++;
                continue;
            }
            if (chArr[i] == ']') {
                if (depth + 1 >= 0 && (2 * n - i - 1) >= 2) { //for '['
                    num += d[2 * n - i - 1][depth + 1] * Math.pow(2, ((2 * n - i - 1 - (depth + 1)) / 2));
                }
                if (depth - 1 >= 0 && list.get(list.size() - 1) == '(') { //for ')'
                    num += d[2 * n - i - 1][depth - 1] * Math.pow(2, ((2 * n - i - 1 - (depth - 1)) / 2));
                }
                if (depth + 1 >= 0 && (2 * n - i - 1) >= 2) { //for '('
                    num += d[2 * n - i - 1][depth + 1] * Math.pow(2, ((2 * n - i - 1 - (depth + 1)) / 2));
                }
                depth--;
                if (!list.isEmpty() && list.get(list.size() - 1) == '[') {
                    list.remove(list.size() - 1);
                } else {
                    list.add(chArr[i]);
                }
            }
        }
        try (PrintWriter out = new PrintWriter("brackets2num2.out", "UTF-8")) {
            out.print(num);
        }
    }  
}