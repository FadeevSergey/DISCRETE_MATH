#include <algorithm>
#include <fstream>
#include <cfloat>
using namespace std;
int main()
{
    ifstream fin("markchain.in");
    ofstream fout("markchain.out");
    int n;
    fin >> n;
    __float128 array[n][n];
    double x;
    for (int i = 0; i < n; ++i)
    {
        for (int k = 0; k < n; ++k)
        {
            fin >> x;
            array[i][k] = (__float128)x;
        }
    }
    bool fl = false;
    __float128 tempD;
    __float128 array1[n][n];
    do
    {
        fl = false;
        for (int i = 0; i < n; ++i)
        {
            for (int k = 0; k < n; ++k)
            {
                tempD = 0;
                for (int j = 0; j < n; ++j)
                {
                    tempD += array[i][j] * array[j][k];
                }
                array1[i][k] = tempD;
            }
        }
        for (int i = 0; i < n; ++i)
        {
            if (abs((double)(array[i][i] - array1[i][i])) > 0.00001)
            {
                fl = true;
                break;
            }
        }
        for (int i = 0; i < n; ++i)
        {
            for (int k = 0; k < n; ++k)
            {
                array[i][k] = array1[i][k];
            }
        }
    } while(fl);
    for (int i = 0; i < n; ++i)
    {
        fout << (double)array[i][i] << "\n";
    }
    fin.close();
    fout.close();
    return 0;
}
