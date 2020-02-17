//Fadeev Sergey. 2020
//Задача B. Уничтожение графа
//https://neerc.ifmo.ru/~sta/2019-2020/2-discrete-math/12-lab-matroids.pdf

#include <iostream>
#include <vector>
#include <set>
#include <map>
#include <algorithm>
using namespace std;


int main()
{
    freopen("destroy.in", "r", stdin);
    freopen("destroy.out", "w", stdout);
    ios_base::sync_with_stdio(false);
    long long n, m, s;
    cin >> n >> m >> s;

    vector<long long> snm(n);
    for(long long i = 0; i < n; i++)
    {
        snm[i] = i;
    }
    vector<pair<long long, pair<long long, long long>>> edges;
    vector<pair<long long, pair<long long, pair<long long, long long>>>> negEdges;
    for(long long i = 0; i < m; i++)
    {
        long long in, out, w;
        cin >> in >> out >> w;
        in--; out--;
        if(in == out) { continue; }
        edges.emplace_back(make_pair(-w, make_pair(in, out)));
        negEdges.emplace_back(make_pair(w, make_pair(i, make_pair(in, out))));
    }
    sort(edges.begin(), edges.end());
    sort(negEdges.begin(), negEdges.end());
    long long cur = 0;
    set<pair<long long, long long>> MST;
    for(auto i : edges)
    {
        if(cur == n - 1) { break; }
        long long fC = 0, sC = 0;
        long long val1 = snm[i.second.first];

        while(val1 != snm[val1])
        {
            fC++;
            val1 = snm[val1];
        }

        long long val2 = snm[i.second.second];

        while(val2 != snm[val2])
        {
            sC++;
            val2 = snm[val2];
        }

        if(val1 != val2)
        {
            MST.insert(i.second);
            if(sC < fC)
            {
                snm[val2] = val1;
                val2 = snm[i.second.second];
                while(val2 != snm[val2])
                {
                    val2 = snm[val2];
                    if(val2 != snm[val2])
                    {
                        break;
                    }
                    snm[val2] = val1;
                }
            }
            else
            {
                snm[val1] = val2;
                val1 = snm[i.second.first];
                while(val1 != snm[val1])
                {
                    val1 = snm[val1];
                    if(val1 != snm[val1])
                    {
                        break;
                    }
                    snm[val1] = val2;
                }
            }
            cur++;
        }
    }
    set<long long> result;

    for(long long i = 0; i < m; i++)
    {
        if(MST.find(negEdges[i].second.second) == MST.end())
        {
            if(negEdges[i].first <= s)
            {
                s -= negEdges[i].first;
                result.insert(negEdges[i].second.first + 1);
            }
            else
            {
                break;
            }
        }
    }

    cout << result.size() << "\n";
    for(long long i : result)
    {
        cout << i << " ";
    }
    return 0;
}