//Fadeev Sergey. 2019
//Задача B. ε-продукции
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/04-lab-cf-grammar.pdf
import java.io.*;
import java.util.*;

public class B {
    private static List<ArrayList<String>> list = new ArrayList<>();
    private static int n;
    private static boolean flag = true;
    private static boolean[] eps = new boolean[26];

    private static String removeCharAt(String s, int position) {
        return s.substring(0, position) + s.substring(position + 1);
    }

    private static void cin() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream("epsilon.in"), "UTF-8"))) {
            for (int i = 0; i < 26; i++) {
                list.add(new ArrayList<>());
            }
            String tempString = reader.readLine();
            String[] tempArray = tempString.split(" ");
            n = Integer.parseInt(tempArray[0]);
            for (int i = 0; i < n; i++) {
                tempArray = reader.readLine().split(" ");
                if (tempArray.length == 2) {
                    eps[(int) (tempArray[0].charAt(0)) - 'A'] = true;
                } else {
                    list.get((int) tempArray[0].charAt(0) - 'A').add(tempArray[2]);
                }
            }
        }
    }

    private static void replaceEps() {
        while(flag) {
            flag = false;
            for(int i = 0; i < list.size(); i++) {
                for(int j = 0; j < list.get(i).size(); j++) {
                    for(int k = 0; k < list.get(i).get(j).length(); k++) {
                        if(list.get(i).get(j).charAt(k) >= 'A' && list.get(i).get(j).charAt(k) <= 'Z') {
                            if(eps[list.get(i).get(j).charAt(k) - 'A']) {
                                list.get(i).set(j, removeCharAt(list.get(i).get(j), k));
                                flag = true;
                                k--;
                                if(list.get(i).get(j).length() == 0) {
                                    eps[i] = true;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void cout() throws FileNotFoundException, UnsupportedEncodingException {
        try (PrintWriter out = new PrintWriter("epsilon.out", "UTF-8")) {
            for(int i = 0; i < 26; i++) {
                if(eps[i]) {
                    out.print((char)(i + 'A')  + " ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        cin();
        replaceEps();
        cout();
    }
}