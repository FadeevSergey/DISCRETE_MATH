#include <iostream>
#include <fstream>
using namespace std;

main() {
    int n;
    ifstream fin("nextmultiperm.in");
    ofstream fout("nextmultiperm.out");
    fin >> n;
    int arr[n];
    for (int i = 0; i < n; ++i) {
        fin >> arr[i];
    }
    int i = n - 2;
    while (i >= 0 && arr[i] >= arr[i + 1])
    {
        i--;
    }
    if (i >= 0)
    {
        int j = i + 1;
        while (j < n -1 && arr[j + 1] > arr[i])
        {
            j++;
        }
        swap(arr[i], arr[j]);
        int l = i + 1;
        int r = n - 1;
        for (int j = l, w = n - 1; j < ((l + r) + 1) / 2; ++j, --w)
        {
            swap(arr[j], arr[w]);
        }
        for (int k = 0; k < n; ++k)
        {
            fout << arr[k] << " ";
        }
    } else {
        for (int k = 0; k < n; ++k)
        {
            fout << 0 << " ";
        }
    }
    fout.close();
}
