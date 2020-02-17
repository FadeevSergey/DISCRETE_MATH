import java.io.*;
public class Dm22 {
    public static void main(String[] args) throws IOException {   
        String strBr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("part2num.in"), "UTF-8"))) {
            strBr = reader.readLine();
        }
        String[] strArr = strBr.split("\\+");
        int size = strArr.length;
        int n = 0;
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = Integer.parseInt(strArr[i]);
            n += arr[i];
        }
        int[][] d = new int[n + 3][n + 3];
        for (int i = 0; i <= n; ++i) {
            d[i][i] = 1;
        }
        for (int i = 2; i <= n + 2; ++i) {
            for (int j = i - 1; j >= 1; --j) {
                d[i][j] = d[i][j + 1] + d[i - j][j];
            }
        }
        int result = 0;
        int lastEl = 0;
        int curSum = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = lastEl; j < arr[i]; ++j) {
                result +=d[n - curSum - j][j];
            }
            curSum +=arr[i];
            lastEl = arr[i];
        }
        try (PrintWriter out = new PrintWriter("part2num.out", "UTF-8")) {
            out.print(result);
        }
    }
}