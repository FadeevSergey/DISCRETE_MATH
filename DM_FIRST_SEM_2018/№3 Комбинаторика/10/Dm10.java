import java.io.*;
public class Dm10 {
    public static void main(String[] args) throws IOException {
        int n;
        int[] arrey;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("partition.in"), "UTF-8"))) {
            n = Integer.parseInt(reader.readLine());
            arrey = new int[n];
            for (int i = 0; i < n; ++i) {
                arrey[i] = 1;
            }
        }
        try (PrintWriter out = new PrintWriter("partition.out", "UTF-8")) {
            out.print(arrey[0]);
            for (int i = 1; i < n; ++i) {
                out.print("+" + arrey[i]);
            }
            out.println();
            int size = n;
            while (size != 1) {
                arrey[size - 1]--;
                arrey[size - 2]++;
                if (arrey[size - 2] > arrey[size - 1]) {
                    arrey[size - 2] += arrey[size - 1];
                    size--;
                } else {
                    while (arrey[size - 2] * 2 <= arrey[size - 1]) {
                        arrey[size] = arrey[size - 1] - arrey[size - 2];
                        size++;
                        arrey[size - 2] = arrey[size - 3];
                    }
                }
                out.print(arrey[0]);
                for (int i = 1; i < size; ++i) {
                    out.print("+" + arrey[i]);
                }
                out.println();
            }
        }
    }
}