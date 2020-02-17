import java.util.*;
import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class Dm2C {

    public static void main(String[] args) throws  IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("mtf.in"), StandardCharsets.UTF_8));
        String firstStr = in.readLine();
        in.close();
        System.out.print(firstStr + "\n");
        List<Character> lib = new ArrayList<Character>(); 
        char[] f = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 25; i > -1; --i) {
            lib.add(f[i]);
        }
        int size = firstStr.length();
        int pos = 100;
        char tempCh;
        PrintWriter out = new PrintWriter("mtf.out", "UTF-8");
        StringBuffer strBuffer = new StringBuffer(firstStr);
        for (int i = 0; i < size; ++i) {
            char temp = strBuffer.charAt(0);
            System.out.print(temp + " ");
            strBuffer.deleteCharAt(0);
            for (int k = 0; k < 26; ++k) {
                if (lib.get(k) == temp) {
                    System.out.print('|' + "\n");
                    pos = k;
                    break;
                }
            }
            
            tempCh = lib.get(pos);
            lib.add(tempCh);
            lib.remove(pos);
            
            pos = 26 - pos;
            if (i < size - 1) {
                out.print(pos + " ");
            } else {
                out.print(pos);
            }
        }
        out.close();
    }
}
