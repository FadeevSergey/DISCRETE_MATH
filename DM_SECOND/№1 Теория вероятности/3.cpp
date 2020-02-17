#include <fstream>
using namespace std;
int main()
{
    ifstream fin("lottery.in");
    ofstream fout("lottery.out");
    int n, m;
    fin >> n >> m;
    int array[m][2];
    for (int i = 0; i < m; ++i)
    {
        fin >> array[i][0] >> array[i][1];
    }
    double arWithVer[m];
    double resultE = 0.0;
    arWithVer[0] = (1.0 / array[0][0]) * ((array[1][0] - 1.0) / array[1][0]);
    resultE +=  arWithVer[0] * array[0][1];
    for (int i = 1; i < m; ++i)
    {
        arWithVer[i] = arWithVer[i - 1] / (array[i][0] - 1.0);
        if (i != m - 1)
        {
            arWithVer[i] *= (array[i + 1][0] - 1.0) / array[i + 1][0];
        }
        resultE += arWithVer[i] * array[i][1];
    }
    fout << n - resultE;
    fin.close();
    fout.close();
    return 0;
}
