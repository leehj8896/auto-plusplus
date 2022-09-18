//
//  main.cpp
//  09.행렬 곱셈 순서
//
//  Created by dohan on 2022/09/18.
//

#include <iostream>


using namespace std;

int N;
int matrix[501][2]; //행렬 r,c값
int dp[501][501];


int main(void) {
    cin >> N;
    for(int i=1; i<=N; i++){
        cin >> matrix[i][0] >> matrix[i][1];
    }
    
   
    
    for(int i=1; i<N; i++){
        for(int j=1; i+j <= N; j++){
            dp[j][i+j] = 1000000000;
            for(int k= j; k<= i+j; k++){
                dp[j][i+j] = min(dp[j][i+j], dp[j][k] + dp[k+1][i+j] +
                                 matrix[j][0] *  matrix[k][1] * matrix[i+j][1]);
            }
        }
    }
    
   
    cout << dp[1][N];
}
