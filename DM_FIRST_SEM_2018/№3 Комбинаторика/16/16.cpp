#include <iostream>
#include <fstream>
using namespace std;
long double fact(long double x)
{
    if (x == 0)
        return 1;
    else
        return x * fact(x - 1);
}
int main()
{
    ifstream fin("choose2num.in");
    ofstream fout("choose2num.out");
    int n, k;
    fin >> n >> k;
    int choose[n + 1];
    choose[0] = 0;
    for (int i = 1; i <= k; ++i)
    {
        fin >> choose[i];
    }
    fin.close();
    long double num = 0;
    long double temp = 0;
    for (int i = 1; i <= k; ++i)
    {
        for (int j = choose[i - 1] + 1; j < choose[i]; ++j)
        {
            num += fact(n - j) / fact(k - i) / fact(n - j - k + i);
        }
    }
    fout << (int)num;
    fout.close();
}
