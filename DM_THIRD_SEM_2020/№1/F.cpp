#include <iostream>
#include <vector>

using namespace std;

long long k, m;
long long mod = 1000000007LL;
vector<long long> nodes;

void read() {
    cin >> k >> m;
    for(int i = 0; i <= m; i++) {nodes.push_back(0);}
    long long temp;
    for (long long i = 0; i < k; i++) {
        cin >> temp;
        nodes[temp] = 1;
    }
}

void solve() {
    vector<long long> res(m + 1, 1);
    vector<long long> pref(m + 1);
    pref[0] = 1;

    long long v;
    for (long long i = 0; i < m; i++) {
        v = 0;
        for (long long j = 0; j <= i; j++) {
            if (nodes[j + 1] != 0){
                v += pref[i - j] % mod;
            }
        }

        res[i + 1] = v % mod;

        for (long long j = 0; j <= i + 1; j++) {
            pref[i + 1] += res[j] * res[i - j + 1];
            pref[i + 1] %= mod;
        }

        cout << res[i + 1] << " ";
    }
}

int main() {
    read();
    solve();
}