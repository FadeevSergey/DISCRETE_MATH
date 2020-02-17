#include <iostream>
#include <cmath>
#include <fstream>
using namespace std;

main()
{
    ifstream fin("allvectors.in");
    int num;
    fin >> num;
    fin.close();
    int vec = pow(2, num);
    int vect[num];
    for (int i = 0; i < num; ++i)
    {
        vect[i] = 0;
    }
    ofstream fout("allvectors.out");
    for (int i = 0; i < vec; ++i)
    {
        for (int k = 0; k < num; ++k)
        {
            fout << vect[k];
        }
        fout << "\n";
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
    fout.close();
}
