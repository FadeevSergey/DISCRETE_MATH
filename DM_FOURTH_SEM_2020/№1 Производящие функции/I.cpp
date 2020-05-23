#include <iostream>
#include <vector>

using namespace std;

const long long mod = 104857601L;
long long k, n;

void chPos(vector<long long>& pos, vector<long long>& second) {
    for (long long i = 0; i <= k; i++) {
        if(i % 2 == 0) {
            pos[i] = second[i];
        } else {
            pos[i] = (-second[i] + mod) % mod;
        }
    }
}

void chRes(vector<long long>& pos, vector<long long>& second, vector<long long>& result) {
    for (long long i = 0; i <= 2 * k; i += 2) {
        long long el = 0;
        for (long long j = 0; j <= i; j++) {
            long long cofA = 0;
            if(j <= k) {
                cofA = (long long) second[j];
            }
            long long cofB = 0;
            if(i - j <= k) {
                cofB = (long long) pos[i - j];
            }
            el = (el + cofA * cofB + mod) % mod;
        }

        result[i / 2] = el;
    }
    for (long long i = 0; i <= k; i++) {
        second[i] = result[i];
    }
}

void chFirst(vector<long long>& first) {
    long long temp = 0;
    for (long long i = 0; i < 2 * k; i++) {
        if (n % 2 == i % 2) {
            first[temp] = first[i];
            temp++;
        }
    }
    n = n / 2;
}

void initFirst(vector<long long>& first, vector<long long>& second) {
    for (long long i = k; i < 2 * k; i++) {
        first[i] = 0;
        for (long long j = 0; j < k; j++) {
            first[i] = (first[i] - second[j + 1] * first[i - j - 1]) % mod;
            while (first[i] < 0) {
                first[i] += mod;
            }
        }
    }
}
void solve(vector<long long>& first, vector<long long>& second) {

    vector<long long> result(k + 1, 0);
    vector<long long> pos(k + 1, 0);

    while (k <= n) {
        initFirst(first, second);
        chPos(pos, second);
        chRes(pos, second, result);
        chFirst(first);
    }
    cout << first[n];
}

int main() {
    cin >> k;
    cin >> n;
    n--;

    std::vector<long long> first(k * 2, 0);
    std::vector<long long> second(k + 1, 0);


    for(long long i = 0; i < k; ++i) {
        long long temp;
        cin >> temp;
        first[i] = temp;
    }

    second[0] = 1;

    for(long long i = 0; i < k; i++) {
        long long temp;
        cin >> temp;
        second[i + 1] = (-temp + mod) % mod;
    }

    solve(first, second);
}