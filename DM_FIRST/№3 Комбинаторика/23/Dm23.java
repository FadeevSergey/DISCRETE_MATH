import java.io.*;
public class Dm23 {
    public static void main(String[] args) throws IOException {
        String firstStr;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("nextvector.in"), "UTF-8"))) {
            String[] frstt = reader.readLine().split(" ");
            firstStr = frstt[0];
        }
        int length = firstStr.length();
        int[] vect1 = new int[length];
        int[] vect2 = new int[length];
        for (int i = 0; i < length; ++i) {
            vect1[i] = (int)firstStr.charAt(i) - 48;
            int temmmm = vect1[i];
            vect2[i] = (int)(temmmm);
        }
        boolean f = true;
        boolean s = true;
        int n = length - 1;
        while ( n >= 0 && vect1[n] != 1) {
            vect1[n] = 1;
            n--;
        }
        if (n == -1)  {
            f = false;
        } else {
            vect1[n] = 0;
        }
        n = length - 1;
        while ( n >= 0 && vect2[n] != 0) {
            vect2[n] = 0;
            n--;
        }
        if (n == -1) {
            s = false;
        } else {
            vect2[n] = 1;
        }
        ////////////////
        try (PrintWriter out = new PrintWriter("nextvector.out", "UTF-8")) {
            if (f) {
                for (int i = 0; i < length; ++i) {
                    out.print(vect1[i]);
                }
                out.println();
            } else {
                out.println("-");
            }
            if (s) {
                for (int i = 0; i < length; ++i) {
                    out.print(vect2[i]);
                }
                out.println();
            } else {
                out.println("-");
            }
        }
    }
}