//Fadeev Sergey. 2020
//Задача A. Планирование заданий
//https://neerc.ifmo.ru/~sta/2019-2020/2-discrete-math/12-lab-matroids.pdf

#include <iostream>
#include <vector>
#include <set>
using namespace std;

int main() {
    freopen("schedule.in", "r", stdin);
    freopen("schedule.out", "w", stdout);
    int n;
    cin >> n;

    set<pair<int, int>> input;

    long long sumOfW = 0;
    for (int i = 0; i < n; i++) {
        int d, w;
        cin >> d >> w;
        sumOfW += w;
        input.insert(make_pair(d, w));
    }

    set<pair<int, int>> resultSet;
    int tempTime = 0;
    for(auto i : input)
    {
        resultSet.insert(make_pair(i.second, i.first));
        if(i.first > tempTime)
        {
            tempTime++;
        }
        else
        {
            resultSet.erase(resultSet.begin());
        }
    }
    long long resultW = 0;

    for(auto i : resultSet)
    {
        resultW += i.first;
    }
    cout << sumOfW - resultW;

    return 0;
}