//Fadeev Sergey. 2019
//Задача A. Автоматная грамматика
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/04-lab-cf-grammar.pdf
import java.util.*;
import java.io.*;
public class A {
    public static void main(String[] args) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream("automaton.in"), "UTF-8"))) {
            char startChar;
            String str;
            List<ArrayList<ArrayList<Character>>> array = new ArrayList<>();
            for(int i = 0; i < 26; i++) {
                array.add(new ArrayList<>());
                for(int j = 0; j < 27; j++) {
                    array.get(i).add(new ArrayList<>());
                }
            }
            str = reader.readLine();
            String[] tempArray = str.split(" ");
            int n = Integer.parseInt(tempArray[0]);
            startChar = tempArray[1].charAt(0);
            for(int i = 0; i < n; i++) {
                str = reader.readLine();
                tempArray = str.split(" ");
                if(tempArray[2].length() == 2) {
                    array.get((int)tempArray[0].charAt(0) - 65).get(tempArray[2].charAt(1) - 65).add(tempArray[2].charAt(0));
                } else {
                    array.get((int)tempArray[0].charAt(0) - 65).get(26).add(tempArray[2].charAt(0));
                }
            }
            int m = Integer.parseInt(reader.readLine());
            try (PrintWriter out = new PrintWriter("automaton.out", "UTF-8")) {
                for (int i = 0; i < m; i++) {
                    str = reader.readLine();
                    if (str.length() == 1) {
                        boolean flag = false;
                        for(int j = 0; j < array.get(startChar - 65).get(26).size(); j++) {
                            if(array.get(startChar - 65).get(26).get(j) == str.charAt(0)) {
                                flag = true;
                                break;
                            }
                        }
                        out.println(flag ? "yes" : "no");
                        continue;
                    }
                    boolean dp[][] = new boolean[27][str.length() + 1];
                    dp[startChar - 65][0] = true;
                    for (int j = 0; j < 26; j++) {
                        for (int k = 0; k < array.get(startChar - 65).get(j).size(); k++) {
                            if (array.get(startChar - 65).get(j).get(k) == str.charAt(0)) {
                                dp[j][1] = true;
                                break;
                            }
                        }
                    }
                    for (int j = 1; j < str.length(); j++) {
                        for (int k = 0; k < 26; k++) {
                            if (dp[k][j]) {
                                for (int y = 0; y <= 26; y++) {
                                    for (int o = 0; o < array.get(k).get(y).size(); o++) {
                                        if (array.get(k).get(y).get(o) == str.charAt(j)) {
                                            dp[y][j + 1] = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    out.println(dp[26][str.length()] ? "yes" : "no");
                }
            }
        }
    }
}
