import java.io.*;
public class Dm15 {
    public static double fact(int x)
    {
        if (x == 0)
            return 1;
        else
            return x * fact(x - 1);
    }
    public static void main(String[] args) throws IOException {
        String str;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("num2choose.in"), "UTF-8"))) {
            str = reader.readLine();
        }
        String[] a = str.split(" ");
        int n, k;
        double m;
        n = Integer.parseInt(a[0]);
        k = Integer.parseInt(a[1]);
        m = Double.parseDouble(a[2]);
        try (PrintWriter out = new PrintWriter("num2choose.out", "UTF-8")) {
            int[] choose = new int[k];
            int pos = 0;
            int next = 1;

            while (k > 0)
            {
                if (m < (fact(n - 1)/fact(k - 1))/fact(n - k))
                {
                    choose[pos] = next;
                    pos++;
                    k--;
                }
                else
                {
                    m -= (fact(n - 1)/fact(k - 1))/fact(n - k);
                }
                n--;
                next++;
            }
            for (int i = 0; i < pos; ++i) {
                out.print(choose[i] + " ");
            }
        }
    }
}