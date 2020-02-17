//Fadeev Sergey. 2019
//Задача B. Поиск гамильтонова цикла в условиях теоремы Хватала
//https://neerc.ifmo.ru/~sta/2019-2020/2-discrete-math/20-lab-hamilton.pdf

//https://neerc.ifmo.ru/wiki/index.php?title=%D0%90%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC_%D0%BD%D0%B0%D1%85%D0%BE%D0%B6%D0%B4%D0%B5%D0%BD%D0%B8%D1%8F_%D0%93%D0%B0%D0%BC%D0%B8%D0%BB%D1%8C%D1%82%D0%BE%D0%BD%D0%BE%D0%B2%D0%B0_%D1%86%D0%B8%D0%BA%D0%BB%D0%B0_%D0%B2_%D1%83%D1%81%D0%BB%D0%BE%D0%B2%D0%B8%D1%8F%D1%85_%D1%82%D0%B5%D0%BE%D1%80%D0%B5%D0%BC_%D0%94%D0%B8%D1%80%D0%B0%D0%BA%D0%B0_%D0%B8_%D0%9E%D1%80%D0%B5
//https://neerc.ifmo.ru/wiki/index.php?title=%D0%A2%D0%B5%D0%BE%D1%80%D0%B5%D0%BC%D0%B0_%D0%9E%D1%80%D0%B5
//https://neerc.ifmo.ru/wiki/index.php?title=%D0%A2%D0%B5%D0%BE%D1%80%D0%B5%D0%BC%D0%B0_%D0%94%D0%B8%D1%80%D0%B0%D0%BA%D0%B0

import java.io.*;
import java.util.*;

public class B {
    private static List<ArrayList<Integer>> matrixEdges;
    private static List<Integer> nodes;

    static void scanEdges() throws IOException {
        matrixEdges = new ArrayList<>();
        nodes = new LinkedList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader (new FileInputStream("chvatal.in"), "UTF-8"))) {
            int n = Integer.parseInt(reader.readLine().split(" ")[0]);

            for(int i = 0; i < n; ++i) {
                nodes.add(i);
            }

            for(int i = 0; i < n; ++i) {
                matrixEdges.add(new ArrayList<>());
                String tempStringForSplit = reader.readLine().replace(" ", "");
                for(int j = 0; j < tempStringForSplit.length(); ++j) {

                    matrixEdges.get(i).add(tempStringForSplit.charAt(j) - 48);
                }
            }
        }
    }

    static boolean hasEdges(int first, int second) {
        int min, max;
        if(first < second) {
            min = first;
            max = second;
        } else {
            min = second;
            max = first;
        }

        return matrixEdges.get(max).get(min) != 0;
    }

    static void expandSubArray(int second) {
        int tempNode;
        for(int i = 1; i <= second / 2; ++i) {
            tempNode = nodes.get(i);
            nodes.set(i, nodes.get(second - i + 1));
            nodes.set(second - i + 1, tempNode);
        }
    }

    static void getSolution() {
        try {
            for (int i = 0, tempPosition; i < matrixEdges.size() * matrixEdges.size(); ++i) {
                if (!hasEdges(nodes.get(0), nodes.get(1))) {
                    tempPosition = 2;
                    while (tempPosition != nodes.size() - 1 &&
                            (!hasEdges(nodes.get(0), nodes.get(tempPosition))
                                    || !hasEdges(nodes.get(1), nodes.get(tempPosition + 1)))) {
                        ++tempPosition;
                    }
                    if(tempPosition == nodes.size() - 1) {
                        tempPosition = 2;
                        while(tempPosition != nodes.size() && !hasEdges(nodes.get(0), nodes.get(tempPosition))) {
                            tempPosition++;
                        }
                    }
                    expandSubArray(tempPosition);
                }
                nodes.add(nodes.remove(0));
            }
        } catch (Exception e) {
            while(true);
        }
    }

    static void printSolution() throws IOException {
        try (PrintWriter out = new PrintWriter("chvatal.out", "UTF-8")) {
            for(int i : nodes) {
                out.print((i + 1) + " ");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        scanEdges();
        getSolution();
        printSolution();
    }

}