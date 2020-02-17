#include <iostream>
#include <fstream>
using namespace std;

main() {
    int n;
    ifstream fin("nextperm.in");
    ofstream fout("nextperm.out");
    fin >> n;
    int a[n];
    int b[n];
    for (int i = 0; i < n; ++i) {
        fin >> a[i];
        b[i] = a[i];
    }
    
    bool s = true;
        for (int i = n - 2; i >= 0; --i)
        {
            if (a[i] > a[i + 1])
            {
                int maxx = i + 1;
                for (int j = i + 1; j < n; ++j)
                {
                    if (a[j] > a[maxx] && a[j] < a[i])
                    {
                        maxx = j;
                    }
                }
                swap(a[i], a[maxx]);
                int l = i + 1;
                int r = n - 1;
                for (int j = l, w = n - 1; j < ((l + r) + 1) / 2; ++j, --w)
                {
                    swap(a[j], a[w]);
                }
                for (int k = 0; k < n; ++k)
                {
                    fout << a[k] << " ";
                }
                fout << "\n";
                s = false;
                break;
            }
        }
        if (s) {
            for (int k = 0; k < n; ++k)
            {
                 fout << 0 << " ";
            }
            fout << "\n";
        }
        s = true;
        for (int i = n - 2; i >= 0; --i)
        {
            if (b[i] < b[i + 1])
            {
                int minn = i + 1;
                for (int j = i + 1; j < n; ++j)
                {
                    if (b[j] < b[minn] && b[j] > b[i])
                    {
                        minn = j;
                    }
                }
                swap(b[i], b[minn]);
                int l = i + 1;
                int r = n - 1;
                for (int j = l, w = n - 1; j < ((l + r) + 1) / 2; ++j, --w)
                {
                    swap(b[j], b[w]);
                }
                for (int k = 0; k < n; ++k)
                {
                    fout << b[k] << " ";
                }
                fout << "\n";
                s = false;
                break;
            }
        }
        if (s)
        {
            for (int k = 0; k < n; ++k)
            {
                fout << 0 << " ";
            }
        }
    fout.close();
}


