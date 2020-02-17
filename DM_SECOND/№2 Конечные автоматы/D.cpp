//Fadeev Sergey 2019
//Задача D. Число слов длины l в языке ДКА
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/19-lab-automata.pdf
#include <fstream>
#include <vector>
#define cin fin
const int mod = 1000000007;
using namespace std;

int n, m, k, l;
vector<vector<int>> arr; //arr[i][j] = k: из состояния j + 1 есть переход по i в состояние л
vector<vector<int>> dp;  //массив для ДПшки
vector<bool> term;       //массив с терминальными состояниями

void build()
{
    ifstream fin("problem4.in");

    cin >> n >> m >> k >> l;

    for (int i = 0; i < 26; i++)
    {
        vector<int> x;
        arr.push_back(x);
        for(int j = 0; j < n; j++)
        {
            arr[i].push_back(0);
        }
    }
    for(int i = 0; i < n; i++)
    {
        term.push_back(false);

        vector<int> x;
        dp.push_back(x);
        for(int j = 0; j <= l; j++)
        {
            dp[i].push_back(0);
        }
    }
    for(int i = 0; i < k; i++)
    {
        int tempT;
        cin >> tempT;
        term[tempT - 1] = true;
    }
    for(int i = 0; i < m; i++)
    {
        char tempC;
        int temps1, temps2;
        cin >> temps1 >> temps2 >> tempC;
        arr[(int)tempC - 97][temps1 - 1] = temps2;
    }
    fin.close();
}

void DP()
{
    for(int i = 0; i < n; i++)
    {
        if(term[i])
        {
            dp[i][0] = 1;
        }
    }
    for(int i = 1; i <= l; i++)
    {
        for(int j = 0; j < n; j++)
        {
            for(int o = 0; o < 26; o++)
            {
                if(arr[o][j] != 0)
                {
                    dp[j][i] += dp[arr[o][j] - 1][i - 1];
                    dp[j][i] %= mod;
                }
            }
        }
    }
}

void print()
{
    ofstream out("problem4.out");
    out << dp[0][l];
    out.close();
}

int main()
{
    build();
    DP();
    print();
    return 0;
}