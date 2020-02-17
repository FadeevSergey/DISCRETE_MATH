//Fadeev Sergey. 2019
//Задача C. Интерактивная восточная сказка
//https://neerc.ifmo.ru/~sta/2019-2020/2-discrete-math/20-lab-hamilton.pdf

#include <iostream>
#include <cmath>
#include <algorithm>
#include <vector>

using namespace std;

int n;
vector<int> arr;

void read()
{
    cin >> n;
    for(int i = 1; i <= n; i++)
    {
        arr.push_back(i);
    }
}

void write()
{
    cout << 0 << " ";
    for(int i : arr)
    {
        cout << i << " ";
    }
}

bool jafarSad(int first, int second)
{
    cout << 1 << " " << second << " " << first << "\n";
    cout.flush();
    string answer;
    cin >> answer;
    return answer[0] == 'Y';
}

void merge(int left, int right)
{
    const int middle = (left + right) / 2;
    vector<int> tempArray(right - left);

    int first = 0;
    int second = 0;

    while (left + first < middle && middle + second < right)
    {
        if (jafarSad(arr[left + first], arr[middle + second]))
        {
            tempArray[first + second] = arr[middle + second];
            second++;
        }
        else
        {
            tempArray[first + second] = arr[left + first];
            first++;
        }
    }

    while (left + first < middle)
    {
        tempArray[first + second] = arr[left + first];
        first++;
    }

    while (middle + second < right)
    {
        tempArray[first + second] = arr[middle + second];
        second++;
    }

    for(int i = 0; i < first + second; i++)
    {
        arr[i + left] = tempArray[i];
    }
}

void mergeSort(int left, int right)
{
    if (left + 1 >= right) { return; }

    int middle = (left + right) / 2;

    mergeSort(left, middle);
    mergeSort(middle, right);
    merge(left, right);
}

int main()
{
    read();
    mergeSort(0, n);
    write();
}
