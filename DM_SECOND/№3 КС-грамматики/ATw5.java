//Memory limit exceeded, test 5
//Fadeev Sergey. 2019
//Задача A. Автоматная грамматика
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/04-lab-cf-grammar.pdf
import java.util.*;
import java.io.*;
public class ATm5 {
    static boolean result;
    static List<ArrayList<Character>> list1;
    static List<ArrayList<String>> list2;
    static char startChar;
    static void check(String str, char ch)
    {
        if(str.length() == 1)
        {
            for(int i = 0; i < list1.get((int)ch - 65).size(); i++)
            {
                if(list1.get((int)ch - 65).get(i) == str.charAt(0))
                {
                    result = true;
                    return;
                }
            }
            return;
        }
        else
        {
            for(int i = 0; i < list2.get((int)ch - 65).size(); i++)
            {
                if(list2.get((int)ch - 65).get(i).charAt(0) == str.charAt(0))
                {
                    check(str.substring(1), list2.get((int)ch - 65).get(i).charAt(1));
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        String str;
        list1 = new ArrayList<>(26);
        list2 = new ArrayList<>(26);
        for(int i = 0; i < 26; i++)
        {
            list1.add(new ArrayList<>());
            list2.add(new ArrayList<>());
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream("automaton.in"), "UTF-8"))) {
            str = reader.readLine();
            String[] tempArray = str.split(" ");
            int n = Integer.parseInt(tempArray[0]);
            startChar = tempArray[1].charAt(0);
            for(int i = 0; i < n; i++) {
                str = reader.readLine();
                tempArray = str.split(" ");
                if(tempArray[2].length() == 2)
                {
                    list2.get((int)tempArray[0].charAt(0) - 65).add(tempArray[2]);
                }
                else
                {
                    list1.get((int)tempArray[0].charAt(0) - 65).add(tempArray[2].charAt(0));
                }
            }
            int cur = 0;

            for(int i = 0; i < 26; i++)
            {
                cur += list1.get(i).size();
            }
            int m = Integer.parseInt(reader.readLine());
            try (PrintWriter out = new PrintWriter("automaton.out", "UTF-8")) {
                for(int i = 0; i < m; i++)
                {
                    if(cur == 0)
                    {
                        out.println("no");
                        continue;
                    }
                    result = false;
                    str = reader.readLine();
                    check(str, startChar);
                    out.println(result ? "yes" : "no");
                }
            }
        }
    }
}