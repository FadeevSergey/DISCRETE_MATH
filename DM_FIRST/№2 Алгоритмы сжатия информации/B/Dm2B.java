import java.util.*;
import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class Dm2B {

    public static void main(String[] args) throws  IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("bwt.in"), StandardCharsets.UTF_8));
        String firstStr = in.readLine();
        int size = firstStr.length();
        String[] strArray = new String[size];
        for (int i = 0; i < size; ++i) {
            StringBuffer strBuffer = new StringBuffer(firstStr);
            String tempStr = firstStr.substring(0, i);
            strBuffer.delete(0, i);
            strBuffer.append(tempStr);
            strArray[i] = strBuffer.toString();
        }
        in.close();
        Arrays.sort(strArray);
        PrintWriter out = new PrintWriter("bwt.out", "UTF-8");
        for (int i = 0; i < size; ++i) {
            out.print(strArray[i].charAt(size - 1));
        }
        out.close();
    }
}
