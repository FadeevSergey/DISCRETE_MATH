//Fadeev Sergey. 2019
//Задача D. НФХ
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/04-lab-cf-grammar.pdf

//http://neerc.ifmo.ru/wiki/index.php?title=%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%9A%D0%BE%D0%BA%D0%B0-%D0%AF
//%D0%BD%D0%B3%D0%B5%D1%80%D0%B0-%D0%9A%D0%B0%D1%81%D0%B0%D0%BC%D0%B8_%D1%80%D0%B0%D0%B7%D0%B1%D0%BE%D1%80%D0%B0_%D0%B3%D1%80
//%D0%B0%D0%BC%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B8_%D0%B2_%D0%9D%D0%A4%D0%A5 //algo

import java.io.*;
import java.util.*;

public class E {
    private static int mod = 1000000007;
    private static String str;
    private static char startChar;
    private static long dp[][][];
    private static int length;
    private static List<ArrayList<String>> list = new ArrayList<>();

    private static void scan() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream("nfc.in"), "UTF-8"))) {

            for (int i = 0; i < 26; i++) {
                list.add(new ArrayList<>());
            }

            String[] tempArray = (reader.readLine()).split(" ");
            int n = Integer.parseInt(tempArray[0]);
            startChar = tempArray[1].charAt(0);

            for (int i = 0; i < n; ++i) {
                tempArray = reader.readLine().split(" ");
                list.get(tempArray[0].charAt(0) - (int)'A').add(tempArray[2]);
            }

            str = reader.readLine().split(" ")[0];
            length = str.length();
            dp = new long[26][length][length];
        }
    }

    private static void dp() {
        int i = 0;
        for(ArrayList<String> tempArray : list) {
            for(String tempStr : tempArray) {
                if(tempStr.length() == 1) {
                    char tempChar = tempStr.charAt(0);
                    for(int j = 0; j < length; j++) {
                        if(str.charAt(j) == tempChar) {
                            dp[i][j][j] = 1;
                        }
                    }
                }
            }
            ++i;
        }

        for(i = 0; i < length; ++i) {                                  //diff length
            for(int j = 0; j < 26; ++j) {                              //diff rules
                for(int k = 0; k + i < length; ++k) {                  //diff start    of String
                    for(int u = k; u < k + i; ++u) {                   //diff diff end of String
                        for(int o = 0; o < list.get(j).size(); ++o) {  //diff result   of rules
                            if(list.get(j).get(o).length() == 2) {
                                char A = list.get(j).get(o).charAt(0);
                                char B = list.get(j).get(o).charAt(1);

                                dp[j][k][k + i] += ((dp[A - 'A'][k][u] % mod) * (dp[B - 'A'][u + 1][k + i] % mod)) % mod;
                                dp[j][k][k + i] %= mod;
                            }
                        }
                    }
                }
            }
        }
    }

    private static void print() throws IOException{
        try (PrintWriter out = new PrintWriter("nfc.out", "UTF-8")) {
            out.print(dp[startChar - 'A'][0][length - 1]);
        }
    }

    public static void main(String[] args) throws IOException {
        scan();
        dp();
        print();
    }
}