//
//  main.cpp
//  04. 징검다리 건너기
//
//  Created by dohan on 2022/09/02.
//

#include <iostream>

using namespace std;

int N,K;
int small[20];
int big[20];
int dp[20];
int minE = 999999;

void dfs(int n, int curE, bool didJump){
    if(n == N){
        minE = min(minE, curE);
        return;
    }else if(n < N){
        if(didJump == false && n+3 <= N){
            dfs(n+3,curE+K, 1);
        }
        if(n+1 <= N){
            dfs(n+1,curE + small[n],didJump);
        }
        if(n+2 <= N){
            dfs(n+2,curE + big[n],didJump);
        }
    }

    
}



int main(void) {
    //input
    cin >> N;
    for(int i=1; i<N; i++){
        cin >> small[i] >>big[i];
    }
    cin >> K;
    
    //dfs
    dfs(1,0,0);
    
    
    cout << minE;
}
