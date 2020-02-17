//Fadeev Sergey 2019
//Задача C. Количество слов в языке
//https://neerc.ifmo.ru/~sta/2018-2019/1-discrete-math/19-lab-automata.pdf
#define cin fin

#include <fstream>
#include <vector>

const int mod = 1000000007;

using namespace std;

int n, m, k;
int result = 0;
vector<vector<int>> arr;
vector<vector<int>> arrB;
vector<bool> term;
vector<char> check;
vector<bool> forTerm;

void build()
{
    ifstream fin("problem3.in");
    cin >> n >> m >> k;
    for (int i = 0; i < 26; i++)
    {
        vector<int> x;
        arr.push_back(x);
        arrB.push_back(x);
        for(int j = 0; j < n; j++)
        {
            arr[i].push_back(0);
            arrB[i].push_back(0);
        }
    }

    for(int j = 0; j < n; j++)
    {
        term.push_back(false);
        forTerm.push_back(false);
        check.push_back('w');
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
        arrB[(int)tempC - 97][temps2 - 1] = temps1;
    }
    fin.close();
}

void dfsForTerm(int top)
{
    if(forTerm[top - 1])
    {
        return;
    }
    forTerm[top - 1] = true;
    for(int i = 0; i < 26; i++)
    {
        if(arrB[i][top - 1] != 0)
        {
            dfsForTerm(arrB[i][top - 1]);
        }
    }
}

void dfsForLoop(int top)
{
    ofstream out("problem3.out");
    if(check[top - 1] == 'b')
    {
        return;
    }
    if(check[top - 1] == 'g')
    {
        if(forTerm[top - 1])
        {
            out << -1;
            out.close();
            exit (0);
        } else
        {
            for(int i = 0; i < 26; i++)
            {
                arr[i][top - 1] = 0;
            }
            return;
        }
    }
    check[top - 1] = 'g';
    for(int i = 0; i < 26; i++)
    {
        if(arr[i][top - 1] != 0)
        {
            dfsForLoop(arr[i][top - 1]);
        }
    }
    check[top - 1] = 'b';
}

void dfsForResult(int top)
{
    if(term[top - 1])
    {
        result++;
        result %= mod;
    }
    for(int i = 0; i < 26; i++)
    {
        if(arr[i][top - 1] != 0)
        {
            dfsForResult(arr[i][top - 1]);
        }
    }
}


void print()
{
    ofstream out("problem3.out");
    out << result;
    out.close();
}
int main()
{
    build();
    for(int i = 0; i < n; i++)
    {
        if(term[i])
        {
            dfsForTerm(i + 1);
        }
    }
    dfsForLoop(1);
    dfsForResult(1);
    print();
}