import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
public class Dm2A {
    public static void main(String[] args)throws IOException {
        int num;
        long[] pArray;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream("huffman.in"), StandardCharsets.UTF_8))) {
            num = Integer.parseInt(input.readLine());
            String[] pArrayS = input.readLine().split("\\p{javaWhitespace}+");
            pArray = new long[num];
            for (int i = 0; i < num; ++i) {
                pArray[i] = Integer.parseInt(pArrayS[i]);
            }
        }
        Arrays.sort(pArray);
        long res = 0;
        for (int i = 0; i < num - 1; ++i) {
            pArray[i + 1] = pArray[i] + pArray[i + 1];
            res += pArray[i + 1];
            Arrays.sort(pArray);
        }
        try (PrintWriter out = new PrintWriter("huffman.out", "UTF-8")) {
            out.print(res);
        }
    }
}
