#include <fstream>
#include <iomanip>
using namespace std;

int main()
{
    ifstream fin("shooter.in");
    ofstream fout("shooter.out");
    int n, m, k;
    fin >> n >> m >> k;
    double array[n];
    double sum = 0.0;
    double pow;
    for (int i = 0; i < n; ++i)
    {
        fin >> array[i];
        pow = 1.0;
        for (int k = 0; k < m; ++k)
        {
            pow *= (1 - array[i]);
        }
        sum += pow;
    }
    double ver = 1.0;
    for (int i = 0; i < m; ++i)
    {
        ver *= (1.0 - array[k - 1]);
    }
    fout << fixed << setprecision(15) << ver / sum;
}
