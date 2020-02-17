#include <fstream>
#include <iomanip>
using namespace std;
int main()
{
    ifstream fin("exam.in");
    ofstream fout("exam.out");
    int k, n;
    fin >> k >> n;
    int x, y;
    long double sum = 0;
    for (int i = 0; i < k; ++i)
    {
        fin >> x >> y;
        sum += x * (y / 100.0);
    }
    fout << sum / n;
    fin.close();
    fout.close();
    return 0;
}
