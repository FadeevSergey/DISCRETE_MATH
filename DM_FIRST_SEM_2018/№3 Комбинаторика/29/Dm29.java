import java.io.*;
import java.util.*;

public class Dm29 {
    public static void main(String[] args) throws IOException {
        String str;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("nextpartition.in"), "UTF-8"))) {
            str = reader.readLine();
        }
        int posOfE = 1;
        StringBuilder strBuffer = new StringBuilder(str);
        for (int i = 0; i < str.length(); ++i) {
            if (strBuffer.charAt(i) == '=') {
                posOfE = i;
                break;
            }
        }
        int num = Integer.parseInt(strBuffer.substring(0, posOfE));
        String[] arrayStr = strBuffer.delete(0, posOfE + 1).toString().split("\\+");
        List<Integer> array = new ArrayList<>();
        for (String tempString : arrayStr) {
            array.add(Integer.parseInt(tempString));
        }
        try (PrintWriter out = new PrintWriter("nextpartition.out", "UTF-8")) {
            if (array.size() == 1) {
                out.print("No solution");
                out.close();
                return;
            }
            array.set(array.size() - 1, array.get(array.size() - 1) - 1);
            array.set(array.size() - 2, array.get(array.size() - 2) + 1);
            
            if (array.get(array.size() - 2) > array.get(array.size() - 1)) {
                array.set(array.size() - 2, array.get(array.size() - 1) + array.get(array.size() - 2));
                array.remove(array.size() - 1);
            } else {
                while (array.get(array.size() - 2) * 2 <= array.get(array.size() - 1)) {
                    array.add(array.get(array.size() - 1) - array.get(array.size() - 2));
                    array.set(array.size() - 2, array.get(array.size() - 3));
                }
            }
            
            out.print(num + "=" + array.get(0));
            for (int i = 1; i < array.size(); ++i) {
                out.print("+" + array.get(i));
            }
        }
    }
}