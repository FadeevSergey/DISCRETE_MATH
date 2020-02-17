#include <iostream>
#include <fstream>
using namespace std;

main()
{
    int num, siz;
    ifstream fin("choose.in");
    ofstream fout("choose.out");
    fin >> num;
    fin >> siz;
    fin.close();
    int arr[siz + 1];
    for (int i = 0; i < siz; ++i) {
        arr[i] = i + 1;
    }
    long long x = 1, y = 1, z = 1;
    for (int i = 1; i <= num; ++i)
    {
        x *= i;
    }
    for (int i = 1; i <= siz; ++i)
    {
        y *= i;
    }
    for (int i = 1; i <= num - siz; ++i)
    {
        z *= i;
    }
    long long ch = x / y;
    ch = ch / z;
    for (int i = 0; i < siz; ++i)
    {
        fout << arr[i] << " ";
    }
    fout << "\n";

    for (int j = 1; j < ch; ++j)
    {
        arr[siz] = num + 1;
        int pos;
        for (int i = siz; i > 0; --i)
        {
            if (arr[i - 1] <= arr[i] - 2)
            {
                pos = i - 1;
                break;
            }
        }
        arr[pos]++;
        for (int i = pos + 1; i < siz; ++i)
        {
            arr[i] = arr[i - 1] + 1;
        }
        for (int i = 0; i < siz; ++i)
        {
            fout << arr[i] << " ";
        }
        fout << "\n";
    }
    fout.close();
}
