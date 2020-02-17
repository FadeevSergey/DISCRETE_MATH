//Fadeev Sergey 2019
//Задача G. Эквивалентность ДКА
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/19-lab-automata.pdf
#include <iostream>
#include <vector>

using namespace std;

int n1, m1, k1;
int n2, m2, k2;

vector<vector<pair<int, bool>>> arr1;
vector<vector<pair<int, bool>>> arr11;
vector<bool> term1;
vector<bool> xuu1;

vector<vector<pair<int, bool>>> arr2;
vector<vector<pair<int, bool>>> arr21;
vector<bool> term2;
vector<bool> xuu2;

bool result = true;

void dfs1(int top)
{
    if(xuu1[top - 1])
    {
        return;
    }
    xuu1[top - 1] = true;
    for(int i = 0; i < 26; i++)
    {
        if(arr11[i][top - 1].first != 0)
        {
            dfs1(arr11[i][top - 1].first);
        }
    }
}

void dfs2(int top)
{
    if(xuu2[top - 1])
    {
        return;
    }
    xuu2[top - 1] = true;
    for(int i = 0; i < 26; i++)
    {
        if(arr21[i][top - 1].first != 0)
        {
            dfs2(arr21[i][top - 1].first);
        }
    }
}

void check(int top1, int top2)
{
    if(term1[top1 - 1] ^ term2[top2 - 1])
    {
        result = false;
        return;
    }

    for(int i = 0; i < 26; i++)
    {
        if(arr1[i][top1 - 1].first == 0 && arr2[i][top2 - 1].first == 0)
        {
            continue;
        }

        if(arr1[i][top1 - 1].first == 0 && arr2[i][top2 - 1].first != 0)
        {
            if(xuu2[arr2[i][top2 - 1].first - 1])
            {
                result = false;
                return;
            }
        }

        if(arr1[i][top1 - 1].first != 0 && arr2[i][top2 - 1].first == 0)
        {
            if(xuu1[arr1[i][top1 - 1].first - 1])
            {
                result = false;
                return;
            }
        }

//        if(arr1[i][top1 - 1].first != 0 && arr2[i][top2 - 1].first != 0)
//        {
//            if (xuu1[arr1[i][top1 - 1].first - 1] ^ xuu2[arr2[i][top2 - 1].first - 1])
//            {
//                result = false;
//                return;
//            }
//        }

        if(arr1[i][top1 - 1].first != 0 && arr2[i][top2 - 1].first != 0)
        {
            if(arr1[i][top1 - 1].second && arr2[i][top2 - 1].second)
            {
                continue;
            }
            arr1[i][top1 - 1].second = true;
            arr2[i][top2 - 1].second = true;
            check(arr1[i][top1 - 1].first, arr2[i][top2 - 1].first);
        }
    }
}

int main()
{
#ifndef ONLINE_JUDGE
    freopen("equivalence.in", "r", stdin);
    freopen("equivalence.out", "w", stdout);
#endif
    cin >> n1 >> m1 >> k1;
    for (int i = 0; i < 26; i++)
    {
        vector<pair<int, bool>> x;
        arr1.push_back(x);
        arr11.push_back(x);
        for(int j = 0; j < n1; j++)
        {
            pair<int, bool> z = {0, false};
            arr1[i].push_back(z);
            arr11[i].push_back(z);
        }
    }
    for(int j = 0; j < n1; j++)
    {
        term1.push_back(false);
        xuu1.push_back(false);
    }
    for(int i = 0; i < k1; i++)
    {
        int tempT;
        cin >> tempT;
        term1[tempT - 1] = true;
    }
    for(int i = 0; i < m1; i++)
    {
        char tempC;
        int temps1, temps2;
        cin >> temps1 >> temps2 >> tempC;
        arr1[(int)tempC - 97][temps1 - 1].first = temps2;
        arr11[(int)tempC - 97][temps2 - 1].first = temps1;
    }

    cin >> n2 >> m2 >> k2;

    for (int i = 0; i < 26; i++)
    {
        vector<pair<int, bool>> x;
        arr2.push_back(x);
        arr21.push_back(x);
        for(int j = 0; j < n2; j++)
        {
            pair<int, bool> z = {0, false};
            arr2[i].push_back(z);
            arr21[i].push_back(z);

        }
    }
    for(int j = 0; j < n2; j++)
    {
        term2.push_back(false);
        xuu2.push_back(false);
    }

    for(int i = 0; i < k2; i++)
    {
        int tempT;
        cin >> tempT;
        term2[tempT - 1] = true;
    }
    for(int i = 0; i < m2; i++)
    {
        char tempC;
        int temps1, temps2;
        cin >> temps1 >> temps2 >> tempC;
        arr2[(int)tempC - 97][temps1 - 1].first = temps2;
        arr21[(int)tempC - 97][temps2 - 1].first = temps1;
    }
    for(int i = 0; i < n1; i++)
    {
        if(term1[i])
        {
            dfs1(i + 1);
        }
    }

    for(int i = 0; i < n2; i++)
    {
        if(term2[i])
        {
            dfs2(i + 1);
        }
    }

    check(1, 1);
    cout << (result ? "YES" : "NO");
}