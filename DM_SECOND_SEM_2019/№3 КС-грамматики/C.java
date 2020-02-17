//Fadeev Sergey. 2019
//Задача C. Бесполезные символы
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/04-lab-cf-grammar.pdf
import java.io.*;
import java.util.*;

public class C {
    private static char startChar;
    private static boolean[] eps = new boolean[26];
    private static Set<Character> set = new HashSet<>();
    private static Set<Character> kondSet = new TreeSet<>();
    private static List<ArrayList<String>> list  = new ArrayList<>();
    private static List<ArrayList<String>> list1 = new ArrayList<>();
    private static List<Boolean> dfs = new ArrayList<>();

    private static void dfs(char ch) {
        if(list.get(ch - 'A').size() != 0) {
            set.add(ch);
        }
        dfs.set((int)ch - 'A', true);
        for(int i = 0; i < list.get((int)ch - 'A').size(); i++) {
            for(int j = 0; j < list.get((int)ch - 'A').get(i).length(); j++) {
                if(list.get((int)ch - 'A').get(i).charAt(j) >= 'A' && list.get((int)ch - 'A').get(i).charAt(j) <= 'Z') {
                    if(!dfs.get(list.get((int)ch - 'A').get(i).charAt(j) - 'A')) {
                        dfs(list.get((int)ch - 'A').get(i).charAt(j));
                    }
                }
            }
        }
    }

    static void cin() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream("useless.in"), "UTF-8"))) {
            for (int i = 0; i < 26; i++) {
                list.add(new ArrayList<>());
                list1.add(new ArrayList<>());
                dfs.add(false);
            }

            String[] tempArray = (reader.readLine()).split(" ");
            int n = Integer.parseInt(tempArray[0]);
            startChar = tempArray[1].charAt(0);
            for (int i = 0; i < n; i++) {
                tempArray = reader.readLine().split(" ");
                if (tempArray.length == 2) {
                    eps[(int) (tempArray[0].charAt(0)) - 65] = true;
                    list.get(tempArray[0].charAt(0) - 'A').add("");
                    list1.get(tempArray[0].charAt(0) - 'A').add("");
                    kondSet.add(tempArray[0].charAt(0));
                }
                if (tempArray.length == 3) {
                    kondSet.add(tempArray[0].charAt(0));
                    StringBuilder strBuild = new StringBuilder();
                    for(int j = 0; j < tempArray[2].length(); j++) {
                        if(tempArray[2].charAt(j) >= 'A' && tempArray[2].charAt(j) <= 'Z') {
                            strBuild.append(tempArray[2].charAt(j));
                            kondSet.add(tempArray[2].charAt(j));
                        }
                    }
                    if(strBuild.length() == 0) {
                        eps[tempArray[0].charAt(0) - 'A'] = true;
                    }
                    list.get(tempArray[0].charAt(0) - 'A').add(strBuild.toString());
                    list1.get(tempArray[0].charAt(0) - 'A').add(strBuild.toString());
                }
            }
        }
    }

    static void remove() {
        boolean flag = true;
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

        for(int i = 0; i < list.size(); i++) {
            for(int j = 0, jj = 0; j < list.get(i).size(); j++, jj++) {
                if(list.get(i).get(j).length() != 0) {
                    list1.get(i).remove(jj);
                    jj--;
                }
            }
        }

        if(list.get(startChar - 'A').size() == 0) {
            kondSet.add(startChar);
        }

        list = list1;
    }
    private static void cout() throws IOException {
        try (PrintWriter out = new PrintWriter("useless.out", "UTF-8")) {
            for(char ch : set) {
                kondSet.remove(ch);
            }
            for(char ch : kondSet) {
                out.print(ch + " ");
            }
        }
    }

    private static String removeCharAt(String s, int pos) {
        return s.substring(0, pos) + s.substring(pos + 1);
    }

    public static void main(String[] args) throws IOException {
        cin();
        remove();
        dfs(startChar);
        cout();
    }
}