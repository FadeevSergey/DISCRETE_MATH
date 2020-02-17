//Fadeev Sergey. 2019
//Задача B. Слово и НКА
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/19-lab-automata.pdf
#include <fstream>
#include <iostream>
#include <vector>
using namespace std;

int main()
{
#ifndef ONLINE_JUDGE
    freopen("problem2.in", "r", stdin);
    freopen("problem2.out", "w", stdout);
#endif
    ios::sync_with_stdio(0);
    cin.tie(0);
    string str;
    cin >> str;
    int size = str.length();
    int n, m, k;
    cin >> n >> m >> k;
    vector<vector<pair<int, char>>> array;
    for(int i = 0; i < n; i++)
    {
        vector<pair<int, char>> z;
        array.push_back(z);
    }
    for(int i = 0; i < n; i++)
    {
        pair<int, char> z = {0, '@'};
        array[i].push_back(z);
    }
    int temp1, temp2;
    for (int i = 0; i < k; i++)
    {
        cin >> temp1;
        pair<int, char> x = {-1, '$'};
        array[temp1 - 1][0] = x;
    }
    char tempChar;
    for(int i = 0; i < m; i++)
    {
        cin >> temp1 >> temp2 >> tempChar;
        pair<int, char> x = {temp2, tempChar};
        array[temp1 - 1].push_back(x);
    }
    bool dp[n][size + 1];
    for(int i = 0; i < n; i++)
    {
        for(int j = 0; j < size + 1; j++)
        {
            dp[i][j] = false;
        }
    }
    dp[0][0] = true;
    for(int i = 0; i < array[0].size(); i++)
    {
        if(array[0][i + 1].second == str[0])
        {
            dp[array[0][i + 1].first - 1][1] = true;
        }
    }
    for(int i = 1; i < size; i++)
    {
        for(int j = 0; j < n; j++)
        {
            if(dp[j][i])
            {
                for(int y = 1; y < array[j].size(); y++)
                {
                    if(array[j][y].second == str[i])
                    {
                        dp[array[j][y].first - 1][i + 1] = true;
                    }
                }
            }
        }
    }
    for(int i = 0; i < n; i++)
    {
        if(dp[i][size])
        {
            if(array[i][0].second == '$')
            {
                cout << "Accepts";
                return 0;
            }
        }
    }
    cout << "Rejects";
    return 0;
}