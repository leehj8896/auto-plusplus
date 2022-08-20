//
//  main.cpp
//  02.chess
//
//  Created by dohan on 2022/08/21.
//

#include <iostream>

using namespace std;

int M,N;
char board[51][51];
char W[8][8] = {
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'}
};
char B[8][8] = {
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'},
    {'B','W','B','W','B','W','B','W'},
    {'W','B','W','B','W','B','W','B'}
};


int compare(int r, int c){
    //r,c꼭짓점부터 8*8 검사해서 W,B중 최소 바꿔야할 칸 수 리턴
    int W_cnt = 0;
    int B_cnt = 0;

    for(int i=0; i<8; i++){
        for(int j=0; j<8; j++){
            if(W[i][j] != board[r+i][c+j] ) W_cnt++;
            if(B[i][j] != board[r+i][c+j] ) B_cnt++;
        }
    }
    
    return min(W_cnt,B_cnt);
}


int main(int argc, const char * argv[]) {
    int min = 100;
    
    
    cin >> N >> M ;
    for(int i=0; i<N; i++){
        for(int j=0; j<M; j++){
            cin >> board[i][j];
        }
    }
    
    //한칸씩 다 비교하기.
    for(int i=0; i<N-7; i++){
        for(int j=0; j<M-7; j++){
            int temp = compare(i,j);
            if(temp < min) min = temp;
        }
    }
    
    cout << min;
    
    return 0;
}
