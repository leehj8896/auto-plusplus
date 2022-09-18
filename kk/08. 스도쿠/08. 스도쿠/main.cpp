//
//  main.cpp
//  08. 스도쿠
//
//  Created by dohan on 2022/09/11.
//

#include <iostream>
#include <string.h>
#include <vector>

using namespace std;

int board[9][9];
int cnt;
bool solved;
vector<pair<int,int>> zeros;


//범위..
pair<int,int> range(int i){
    if(i>=0 && i<3){
        return {0,3};
    }else if(i>=3 && i<6){
        return {3,6};
    }else
        return{6,9};
}


bool check(int r, int c, int n){
  
    pair<int, int> rr = range(r);
    pair<int, int> cr = range(c);

    //3x3 칸안에 중복있는지
    for(int i= rr.first; i<rr.second; i++){
        for(int j= cr.first; j<cr.second; j++){
            if(i == r && j == c) continue;
            if(board[i][j] == board[r][c]) return false;
        }
    }

    //가로줄 세로줄 중복
    for(int i=0; i<9; i++){
        if(board[i][c] == n && i != r ) return false;
        if(board[r][i] == n && i != c ) return false;
    }


    return true;
}

void solve(int n){
    if( n == cnt ){
        //다풀었으면. 플래그 세우고 리턴
        solved = true;
        return;
    }
    
    int r = zeros[n].first;
    int c = zeros[n].second;
    
    for(int i=1; i<=9; i++){
        board[r][c] = i;
        if(check(r,c,i)){
            solve(n+1);
        }
        
        if(solved) return;
    }
    
    board[r][c] = 0;
    return;
    
    
}

int main(void) {
    
    //input
    for(int i=0; i<9; i++){
        for(int j=0; j<9; j++){
            scanf("%1d", &board[i][j]);
            if(board[i][j] == 0){
                zeros.push_back({i,j});
                cnt++;
            }
        }
    }
    
    solve(0);
    
    for(int i=0; i<9; i++){
        for(int j=0; j<9; j++){
            cout << board[i][j] ;
        }
        cout << "\n";
    }
    
    
    return 0;
}
