/*
Если ты это читаешь, 
то прими мои извинения за тот говнокод, 
который ты увидешь виже.
Прости
*/
import java.util.*;
import java.lang.*;
import java.io.*;
public class Dm2C {
    static boolean mon(int[][] arr, String vec, int size) {
        int h = vec.length();
        int c;
        for (int i = 0; i < h - 1; ++i) {
            for (int j = i + 1; j < h; ++j) {
                c = 0;
                for (int m = 0; m < size; ++m) {
                    if (arr[i][m] > arr[j][m]) {
                        ++c;
                    }
                }
                if (c == 0) {
                    if (vec.charAt(i) == '1') {
                        if (vec.charAt(j) == '0') {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws  IOException{
        int[][] oneV = new int[][] {{0}, {1}};
        int[][] twoV = new int[][] {{0, 0}, {0, 1}, {1, 0}, {1, 1}};
        int[][] thrV = new int[][] {{0, 0, 0}, {0, 0, 1}, {0, 1, 0}, {0, 1, 1}, {1, 0, 0}, {1, 0, 1}, {1, 1, 0}, {1, 1, 1}};
        int[][] fourV = new int[][] {{0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 0}, {0, 0, 1, 1}, {0, 1, 0, 0}, {0, 1, 0, 1}, {0, 1, 1, 0}, {0, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 1}, {1, 0, 1, 0}, {1, 0, 1, 1}, {1, 1, 0, 0}, {1, 1, 0, 1}, {1, 1, 1, 0}, {1, 1, 1, 1}};
        int[][] fiveV = new int[][] {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 1}, {0, 0, 0, 1, 0}, {0, 0, 0, 1, 1}, {0, 0, 1, 0, 0}, {0, 0, 1, 0, 1}, {0, 0, 1, 1, 0}, {0, 0, 1, 1, 1}, {0, 1, 0, 0, 0}, {0, 1, 0, 0, 1}, {0, 1, 0, 1, 0}, {0, 1, 0, 1, 1}, {0, 1, 1, 0, 0}, {0, 1, 1, 0, 1}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 1}, {1, 0, 0, 0, 0}, {1, 0, 0, 0, 1}, {1, 0, 0, 1, 0}, {1, 0, 0, 1, 1}, {1, 0, 1, 0, 0}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 0}, {1, 0, 1, 1, 1}, {1, 1, 0, 0, 0}, {1, 1, 0, 0, 1}, {1, 1, 0, 1, 0}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 0}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}, {1, 1, 1, 1, 1}};
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] sArray = new int[n];
        String[] strArray = new String[n];
        int k = 0;
        for (int i = 0; i < n; ++i) { //записываем чиселки
            sArray[i] = input.nextInt();
            strArray[i] = input.next();
        }
        ////////// //////////////////////////////////////
        for (int i = 0; i < n; ++i) { // проверяем на сохр 0
            if (strArray[i].charAt(0) == '0') {
                ++k;
            } else {
                break;
            }
        }
        if (k == n) {
            System.out.print("NO");
            return;
        }
        k = 0;
        ////////////////////////////////////////////////
        for (int i = 0; i < n; ++i) { // проверяем на сохр 1
            if (strArray[i].charAt(strArray[i].length() - 1) == '1') {
                ++k;
            } else {
                break;
            }
        }
        if (k == n) {
            System.out.print("NO");
            return;
        }
        k = 0;
        ////////////////////////////////////////////////
        int u = 0;
        for (int i = 0; i < n; ++i) { //проверка на самодвойсвенность
            if (sArray[i] == 0) {
                ++u;
                break;
            }
            int len = strArray[i].length();
            while ((k < len / 2)) {
                if (strArray[i].charAt(k) == strArray[i].charAt(len - k - 1)) {
                    ++u;
                    break;
                }
                ++k;
            }
        }
        if (u == 0) {
            System.out.print("NO");
            return;
        }
        u = 0;
        k = 0;
        ////////////////////////////////////////////////
        for (int i = 0; i < n; ++i) { //проверка на линейность 
            int j = strArray[i].length();
            int[] tempAr = new int[j];
            int[] resvArray = new int[j];
            int tempSize = j;
            for (int x = 0; x < j; ++x) {
                tempAr[x] = Character.getNumericValue(strArray[i].charAt(x));
            }
            resvArray[0] = tempAr[0];
            j--;
            for (int w = 0; w < j; ++w) {
                --tempSize;
                for (int e = 0; e < tempSize; ++e) {
                    tempAr[e] = (tempAr[e] + tempAr[e + 1]) % 2;
                }
                resvArray[w + 1] = tempAr[0];
            }
            j++;
            for (int x = 0; x < j; ++x) {
                if (x != 0 && x != 1 && x != 2 && x != 4 && x != 8 && x != 16) {
                    if (resvArray[x] == 1) {
                        ++u;
                        break;
                    }
                }
            }
        }
        if (u == 0) {
            System.out.print("NO");
            return;
        }
        //////////////////////////////////////////////////
        for (int i = 0; i < n; ++i) { // проверка на монотонность
            if (sArray[i] == 2) {
                if (!mon(twoV, strArray[i], 2)) {
                    ++k;
                    break;
                }
            }
            if (sArray[i] == 3) {
                if (!mon(thrV, strArray[i], 3)) {
                    ++k;
                    break;
                }
            }
            if (sArray[i] == 1) {
                if (!mon(oneV, strArray[i], 1)) {
                    ++k;
                    break;
                }
            }
            if (sArray[i] == 4) {
                if (!mon(fourV, strArray[i], 4)) {
                    ++k;
                    break;
                }
            }
            if (sArray[i] == 5) {
                if (!mon(fiveV, strArray[i], 5)) {
                    ++k;
                    break;
                }
            }
        }
        if (k == 0) {
            System.out.print("NO");
        } else {
            System.out.print("YES");
        }   
    }
}