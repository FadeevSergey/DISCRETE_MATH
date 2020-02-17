#include <iostream>
#include <cmath>
#include <fstream>
using namespace std;

main()
{
    ifstream fin("vectors.in");
    int num;
    fin >> num;
    fin.close();
    int vec = pow(2, num);
    int res[vec][num];
    int vect[num];
    int cur = 0;
    bool m = true;
    for (int i = 0; i < num; ++i)
    {
        vect[i] = 0;
    }
    ofstream fout("vectors.out");
    for (int i = 0; i < vec; ++i)
    {
        m = true;
        for (int k = 1; k < num; ++k)
        {
            if (vect[k - 1] == vect[k] && vect[k] == 1)
            {
                m = false;
                break;
            }
        }
        if (m)
        {
            for (int k = 0; k < num; ++k)
            {
                res[cur][k] = vect[k];
            }
            cur++;
        }
        for (int j = num - 1; j > - 1; --j)
        {
            if (vect[j] == 0)
            {
                vect[j] = 1;
                break;
            }
            else
            {
                vect[j] = 0;
            }
        }
    }
    fout << cur << "\n";
    for (int k = 0; k < cur; ++k)
    {
        for (int j = 0; j < num; ++j)
        {
            fout << res[k][j];
        }
        fout << "\n";
    }
    fout.close();
}
