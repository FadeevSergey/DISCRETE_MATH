//k-����� ������� ������.
//fact(i) = i!
//perm[] - ������� ������������
//was[] - ������������ �� �� ��� ��� ����� � ������������
//alreadyWas -  ������� ���� ��� ��������� ������ �������������� � ������� �������
//cur -  ���� ������� � ������� j ��������, �� �� ����� ����� curFree ����� ���� ��������� ��������� � 1 �� j
//�������� ��������� http://neerc.ifmo.ru/wiki/index.php?title=���������_�������_��_������

#include <iostream>
#include <fstream>
using namespace std;

long long fact(int n)
{
    if (n == 0)
        return 1;
    else
        return n * fact(n - 1);
}

main()
{
    ifstream fin("num2perm.in");
    ofstream fout("num2perm.out");
    long long n, k;
    fin >> n >> k;
    fin.close();
    bool was[n + 1];
    for (int i = 1; i <= n; ++i)
    {
        was[i] = false;
    }
    long long perm[n + 1];
    for (int i = 0; i <= n; ++i)
    {
        perm[i] = 0;
    }
    long long alreadyWas;
    long long cur = 0;
    for (int i = 1; i <= n; ++i)
    {
        alreadyWas = k / (fact(n - i));
        k %= fact(n - i);
        cur = 0;
        for (int j = 1; j <= n; ++j)
        {
            if (!was[j])
            {
                cur++;
                if(cur == alreadyWas + 1)
                {
                    perm[i] = j;
                    was[j] = true;
                }
            }
        }
    }
    for (int i = 1; i <= n; ++i)
    {
        fout << perm[i] << " ";
    }
    fout.close();
}

