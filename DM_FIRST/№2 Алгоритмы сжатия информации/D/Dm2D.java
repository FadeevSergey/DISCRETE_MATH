import java.util.*;
import java.lang.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
public class Dm2D {
    static boolean libHasStr(String str, List<String> lib) {
        for (int i = 0; i < lib.size(); ++i)
            if (lib.get(i).equals(str)) {
                return true;
            }
        return false;
    }
    static int libPosStr(String str, List<String> lib) {
        for (int i = 0; i < lib.size(); ++i)
            if (lib.get(i).equals(str)) {
                return i;
            }
        return -1;
    }
    public static void main(String[] args) throws  IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("lzw.in"), StandardCharsets.UTF_8));
        String firstStr = in.readLine();
        in.close();
        List<String> lib = new ArrayList<String>(); 
        String[] f = new String[] {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
        for (int i = 0; i < 26; ++i) {
            lib.add(f[i]);
        }
        int size = firstStr.length();
        String buf = "";
        char tempCh = '.';
        PrintWriter out = new PrintWriter("lzw.out", "UTF-8");
        StringBuffer strBuffer = new StringBuffer(firstStr);
        int pos = 2;
        for (int i = 0; i < size; ++i) {
            tempCh = strBuffer.charAt(0);
            strBuffer.deleteCharAt(0);
            buf += tempCh;
            if (libHasStr(buf, lib)) {
                pos = libPosStr(buf, lib); 
            } else {  
                out.print(pos + " ");
                lib.add(buf);
                buf = "";
                buf += tempCh;
                pos = libPosStr(buf, lib);
            }
        }
        out.print(libPosStr(buf, lib) + " ");
        out.close();
    }
}
