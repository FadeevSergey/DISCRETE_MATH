//Fadeev Sergey 2019
//Задача F. Изоморфизм ДКА
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/19-lab-automata.pdf
#include <iostream>
#include <vector>

using namespace std;

int n1, m1, k1;
int n2, m2, k2;

vector<vector<pair<int, bool>>> arr1;
vector<bool> term1;

vector<vector<pair<int, bool>>> arr2;
vector<bool> term2;

bool result = true;

void check(int top1, int top2)
{
    if(term1[top1 - 1] ^ term2[top2 - 1])
    {
        result = false;
        return;
    }

    for(int i = 0; i < 26; i++)
    {
        if(arr1[i][top1 - 1].first == 0 &&  arr2[i][top2 - 1].first != 0)
        {
            result = false;
            return;
        }
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

void build()
{
#ifndef ONLINE_JUDGE
    freopen("isomorphism.in", "r", stdin);
#endif
    cin >> n1 >> m1 >> k1;
    for (int i = 0; i < 26; i++)
    {
        vector<pair<int, bool>> x;
        arr1.push_back(x);
        for(int j = 0; j < n1; j++)
        {
            pair<int, bool> z = {0, false};
            arr1[i].push_back(z);
        }
    }
    for(int j = 0; j < n1; j++)
    {
        term1.push_back(false);
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
    }

    cin >> n2 >> m2 >> k2;

    for (int i = 0; i < 26; i++)
    {
        vector<pair<int, bool>> x;
        arr2.push_back(x);
        for(int j = 0; j < n2; j++)
        {
            pair<int, bool> z = {0, false};
            arr2[i].push_back(z);

        }
    }
    for(int j = 0; j < n2; j++)
    {
        term2.push_back(false);
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
    }
}

void out()
{
#ifndef ONLINE_JUDGE
    freopen("isomorphism.out", "w", stdout);
#endif
    cout << (result ? "YES" : "NO");
}

int main()
{
    build();
    check(1, 1);
    out();
    return 0;
}