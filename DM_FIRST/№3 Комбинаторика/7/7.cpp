#include <iostream>
#include <fstream>
using namespace std;

main()
{
    int n;
    ifstream fin("permutations.in");
    ofstream fout("permutations.out");
    fin >> n;
    fin.close();
    int arr[n];
    for (int i = 0; i < n; ++i) {
        arr[i] = i + 1;
    }
    for (int i = 0; i < n; ++i)
    {
        fout << arr[i] << " ";
    }
    fout << "\n";
    int siz = 1;
    for (int i = 1; i <= n; ++i)
    {
        siz *= i;
    }
    /////////////////////////////
    for (int j = 1; j < siz; ++j)
    {
        int gr = n - 1;
        for (int i = n - 1; i > 0; --i) {
            if (arr[i - 1] < arr[i]) {
                gr = i - 1;
                break;
            }
        }
        int minn = arr[gr+1];
        int ps = gr + 1;
        for (int i = n - 1; i > gr; --i)
        {
            if (arr[i] < minn && arr[i] > arr[gr])
            {
               ps = i;
               break;
            }
        }
        swap(arr[gr], arr[ps]);
        for (int i = gr + 1, k = n - 1; i <= (n - (n - gr) / 2) - 1; ++i, --k) {
            int temp = arr[i];
            arr[i] = arr[k];
            arr[k] = temp;
        }

        for (int i = 0; i < n; ++i) {
            fout << arr[i] << " ";
        }
        fout << "\n";
    }
        fout.close();
}
