//read it http://neerc.ifmo.ru/wiki/index.php?title=Получение_номера_по_объекту
#include <iostream>
#include <fstream>
using namespace std;
long long fact(int x)
{
    if (x == 0)
        return 1;
    else
        return x * fact(x - 1);
}
int main()
{
    ifstream fin("perm2num.in");
    ofstream fout("perm2num.out");
    int n;
    fin >> n;
    long long p[n + 1];
    long long a[n + 1];
    bool was[n + 1];
    for (int i = 1; i <= n; ++i)
    {
        fin >> a[i];
        p[i] = fact(i);
        was[i] = false;

    }
    fin.close();
    long long num = 0;
    for (int i = 1; i <= n; ++i)
    {
        for (int j = 1; j < a[i]; ++j)
        {
            if (!was[j])
            {
                num += p[n - i];
            }
        }
        was[a[i]] = true;
    }
    fout << num;
    fout.close();


}
