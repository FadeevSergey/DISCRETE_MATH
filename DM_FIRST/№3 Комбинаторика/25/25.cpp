#include <iostream>
#include <fstream>
using namespace std;

main()
{
    int num, siz;
    ifstream fin("nextchoose.in");
    ofstream fout("nextchoose.out");
    fin >> num;
    fin >> siz;


    int arr[siz];
    for (int i = 0; i < siz; ++i)
    {
        fin >> arr[i];
    }
    fin.close();
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
            if (arr[i] > num || arr[i] < 1)
            {
                fout << -1;
                return 0;
            }
        }
        for (int i = 0; i < siz; ++i)
        {
            fout << arr[i] << " ";
        }
        fout << "\n";
    fout.close();
}
