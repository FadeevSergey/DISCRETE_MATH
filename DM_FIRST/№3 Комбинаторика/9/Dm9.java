import java.io.*;
import java.util.ArrayList;
public class Dm9 {
    static void gen(int z, int x, int y, String str, ArrayList<String> list) {
        if (x + y == 2 * z) {
            list.add(str);
            return;
        }
        if (x < z) {
            gen(z, x + 1, y, str + "(", list);
        }
        if (x > y) {
            gen(z, x, y + 1, str + ")", list);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("brackets.in"), "UTF-8"));
        int n = Integer.parseInt(reader.readLine());
        PrintWriter out = new PrintWriter("brackets.out", "UTF-8");   
        ArrayList<String> list = new ArrayList<>();
        gen(n, 0, 0, "", list);
        int size = list.size();
        for (int i = 0; i < size; ++i) {
            out.println(list.get(i));
        }
        reader.close();
        out.close();
    }
}