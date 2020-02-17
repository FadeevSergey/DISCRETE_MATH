#include <iostream>
#include <cmath>
#include <fstream>
using namespace std;

main()
{
    ifstream fin("telemetry.in");
    int n;
    int k;
    fin >> n;
    fin >> k;
    fin.close();
    int vec = pow(k, n);
    int vect[vec][n]; //массив, который выведем
    int arr[2 * k]; //симметричный массив, из него будем брать чиселки
    for (int i = 0; i < k; ++i)
    {
        arr[i] = i;
        arr[2 * k - 1 - i] = i;

    }
    ///////////////////////////
    int y = k;
    for (int o = n - 1; o > -1; --o)
    {
        for (int j = 0; j < y; ++j)
        {
            for (int h = 0; h < vec / y; ++h)
            {
                vect[j * (vec / y) + h][o] = arr[(j) % (2 * k)];
            }
        }
        y *= k;
    }
    ///////////////////////////
    ofstream fout("telemetry.out");
    for (int i = 0; i < vec; ++i)
    {
        for (int o = 0; o < n; ++o)
        {
            fout << vect[i][o];
        }
        fout << "\n";
    }
    fout.close();
}
