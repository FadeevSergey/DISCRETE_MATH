#include <iostream>
#include <cmath>
#include <fstream>
using namespace std;

main()
{
    ifstream fin("gray.in");
    int num;
    fin >> num;
    fin.close();
    int vec = pow(2, num);
    int vect[vec][num];
    int cur;
    int f = 0, s = 1;
    int kol = 1;
    for (int i = num - 1; i > -1; --i)
    {
        cur = 0;
        while (cur != vec)
        {
            for (int j = 0; j < kol; ++j)
            {
                vect[cur][i] = f;
                cur++;
            }
            for (int j = 0; j < kol; ++j)
            {
                vect[cur][i] = s;
                cur++;
            }
            swap(f, s);
        }
        kol *= 2;
    }
    ofstream fout("gray.out");
    for (int i = 0; i < vec; ++i)
    {
        for (int k = 0; k < num; ++k)
        {
            fout << vect[i][k];
        }
        fout << "\n";
        }
    fout.close();
}
