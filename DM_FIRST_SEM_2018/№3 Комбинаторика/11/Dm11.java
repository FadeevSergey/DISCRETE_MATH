import java.io.*;
import java.util.TreeSet;
public class Dm11 {
    public static void main(String[] args) throws IOException {
        int n;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("subsets.in"), "UTF-8"))) {
            n = Integer.parseInt(reader.readLine());
        }
        int vec = (int)Math.pow(2, n);
        int[] vect = new int[n];
        TreeSet<String> set = new TreeSet<>();
        for (int i = 0; i < vec; ++i)
        {
            for (int j = n - 1; j > - 1; --j)
            {
                if (vect[j] == 0)
                {
                    vect[j] = 1;
                    break;
                }
                else
                {
                    vect[j] = 0;
                }
            }
            String str = "";
            for (int k = n - 1; k >= 0; --k) {
                if (vect[k] == 1) {
                    int t = n - k - 1;
                    str += t + " ";
                }
            }
            set.add(str);
        }
        try (PrintWriter out = new PrintWriter("subsets.out", "UTF-8")) {
            while (!set.isEmpty()) {
                String t = set.pollFirst();
                String[] array = t.split(" ");
                for (int i = 0 ; i < array.length; ++i) {
                    if (!"".equals(array[i])) {
                        out.print((Integer.parseInt(array[i]) + 1) + " ");
                    }
                }
                out.println();
            }
        }
    }
}