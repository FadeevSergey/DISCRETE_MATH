import java.util.*;
import java.lang.*;
import java.io.*;
public class Dm1B {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<List<Integer>> arr = new ArrayList<>();
        int n = input.nextInt();
        int k = input.nextInt();
        int[] vec = new int[n];
        for (int i = 0; i < n; ++i) {
            vec[i] = -1;
        }
        for (int i = 0; i < k; ++i) {
            List<Integer> tempL = new ArrayList<>();
            arr.add(tempL);
            for (int j = 0; j < n; ++j) {
                arr.get(i).add(input.nextInt());
            }
        }
        int num = 0;
        int numK = 0;
        int pos = -1;
        ////////////////////////////////////////////////////////////////////////
        while (!arr.isEmpty()) {  
            for (int i = 0; i < k; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (arr.get(i).get(j) != -1 && vec[j] != -1) {
                        if (arr.get(i).get(j) == 1) {
                            if (vec[j] == 0) {
                                arr.get(i).set(j, -1);
                            } else {
                                arr.remove(i);
                                i--;
                                k--;
                            }
                        } else {
                            if (vec[j] == 1) {
                                arr.get(i).set(j, -1);
                            } else {
                                arr.remove(i);
                                i--;
                                k--;
                            }
                        }
                    }
                }
            }
            ////////////////////////////////////////////////////////////////////
            for (int i = 0; i < k; ++i) {
                for (int j = 0; j < n; ++j) {
                    num = 0;
                    if (arr.get(i).get(j) == -1) {
                        num++;
                    } else {
                         pos = j;
                    }
                }
                if (num == n - 1) {
                    if (arr.get(i).get(pos) == 0) {
                        if (vec[pos] == -1){
                            vec[pos] = 0;
                        } else {  
                            if (vec[pos] == 1) {
                                System.out.print("YES");
                                return;
                            }
                        }
                    } else {
                        if (vec[pos] == -1){
                            vec[pos] = 1;
                        } else {  
                            if (vec[pos] == 0) {
                                System.out.print("YES");
                                return;
                            }
                        }
                    }
                    arr.remove(i);
                    i--;
                    k--;
                } else {
                    numK++;
                }
            }
            if (numK == k) {
                System.out.print("NO");
                return;
            }
        }
        System.out.print("YES");
    }  
}
