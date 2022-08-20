#include <iostream>
using namespace std;
#define INF 1e9

int N;
int graph[16][16];
int dp[16][1<<16];

int getMinCost(int ci, int visited) {

    if (visited == (1 << N)-1) {
        if (graph[ci][0] == 0) {
            return INF;
        }
        return graph[ci][0];
    }

    if (dp[ci][visited] != 0) {
        return dp[ci][visited];
    }

    int result = INF;
    for (int ni=0; ni<N; ni++) {

        if (graph[ci][ni] == 0) {
            continue;
        }
        if (visited & (1 << ni)) {
            continue;
        }
        int nextMinCost = getMinCost(ni, visited | (1 << ni)) + graph[ci][ni];
        if (result > nextMinCost) {
            result = nextMinCost;
        }
    }
    dp[ci][visited] = result;
    return dp[ci][visited];
}

int main() {
    
    cin >> N;

    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            cin >> graph[i][j];
        }
    }
    cout << getMinCost(0, 1) << endl;

    return 0;
}