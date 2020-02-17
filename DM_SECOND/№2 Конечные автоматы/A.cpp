//Fadeev Sergey. 2019
//Задача A. Слово и ДКА
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/19-lab-automata.pdf
#include <fstream>
#include <iostream>

using namespace std;

int main()
{
#ifndef ONLINE_JUDGE
    freopen("problem1.in", "r", stdin);
    freopen("problem1.out", "w", stdout);
#endif
    ios::sync_with_stdio(0);
    cin.tie(0);
    string str;
    cin >> str;
    int size = str.length();
    int n, m, k;
    cin >> n >> m >> k;
    int array[27][n];
    int temp1, temp2;
    for(int i = 0; i < 27; i++)
    {
        for(int j = 0; j < n; j++)
        {
            array[i][j] = 0;
        }
    }
    for (int i = 0; i < k; i++)
    {
        cin >> temp1;
        array[0][temp1 - 1] = 1;
    }
    char tempChar;
    for(int i = 0; i < m; i++)
    {
        cin >> temp1 >> temp2 >> tempChar;
        array[(int)tempChar - 96][temp1 - 1] = temp2;
    }
    temp1 = 1;
    for(int i = 0; i < size; i++)
    {
        if(array[(int)str[i] - 96][temp1 - 1] != 0)
        {
            temp1 = array[(int)str[i] - 96][temp1 - 1];
        }
        else
        {
            cout << "Rejects";
            return 0;
        }
    }
    if(array[0][temp1 - 1] != 1)
    {
        cout << "Rejects";
    }
    else
    {
        cout << "Accepts";
    }
    return 0;
}