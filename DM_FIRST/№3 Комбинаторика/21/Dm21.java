import java.io.*;
public class Dm21 {
    public static void main(String[] args) throws IOException {
        String str;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("num2part.in"), "UTF-8"))) {
            str = reader.readLine();
        }
        int n = Integer.parseInt(str.split(" ")[0]);
        int k = Integer.parseInt(str.split(" ")[1]);
        int[] arr = new int[n];
        int size = 0;
        for (int i = 0; i < n; ++i) {
            arr[size] = 1;
            size++;
            
        }
        for (int i = 0; i < k; ++i) {
            arr[size - 1]--;
            arr[size - 2]++;
            if (arr[size - 2] > arr[size - 1]) {
                arr[size - 2] += arr[size - 1];
                size--;
            } else {
                while (arr[size - 2] * 2 <= arr[size - 1]) {
                    arr[size] = arr[size - 1] - arr[size - 2];
                    size++;
                    arr[size - 2] = arr[size - 3];
                }
            }
        }
        PrintWriter out = new PrintWriter("num2part.out", "UTF-8");
        out.print(arr[0]);
        for (int i = 1; i < size; ++i) {
            out.print("+" + arr[i]);
        }
        out.close();
    }
}