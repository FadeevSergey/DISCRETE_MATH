import java.io.*;
import java.util.Set;
import java.util.HashSet;
public class Dm4 {
    public static void main(String[] args) throws IOException {
        int n;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("chaincode.in"), "UTF-8"))) {
            n = Integer.parseInt(reader.readLine());
            
        }
        Set<String> set= new HashSet<>();
        int vec = (int)Math.pow(2, n);
        String et = "";
        StringBuilder strBuild = new StringBuilder(et);
        for (int i = 0; i < n; ++i) {
            strBuild.append("0");
        }
        try (PrintWriter out = new PrintWriter("chaincode.out", "UTF-8")) {
            out.println(strBuild.toString() + "\n");
            set.add(strBuild.toString());
            for (int i = 1; i < vec; ++ i) {
                strBuild.deleteCharAt(0);
                strBuild.append("1");
                if (!set.contains(strBuild.toString())) {
                    out.println(strBuild.toString() + "\n");
                    set.add(strBuild.toString());
                    continue;
                }
                strBuild.deleteCharAt(n - 1);
                strBuild.append("0");
                if (!set.contains(strBuild.toString())) {
                    out.println(strBuild.toString() + "\n");
                    set.add(strBuild.toString());
                    continue;
                }
                break;
            }
        }
    }
}