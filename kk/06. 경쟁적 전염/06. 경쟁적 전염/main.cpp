//
//  main.cpp
//  07. 경쟁적 전염
//
//  Created by dohan on 2022/09/04.
//

#include <iostream>
#include <vector>
#include <algorithm>


using namespace std;


struct Virus {
    int n, r, c;
};
int N,K,S,x,y,cnt;
vector <Virus>  board;
int map[201][201];
int nnew[201][201];
int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};

bool cmp(Virus a, Virus b){
    return a.n < b.n;
}

void breeding(){
    int len = board.size();
    for(int i=0; i< len; i++){
        for(int k =0 ; k<4; k++){
            int nr = board[i].r + dr[k];
            int nc = board[i].c + dc[k];
            
            if(nr >= 0 && nc >= 0 && nr < N && nc < N && map[nr][nc] == 0){
                map[nr][nc] = board[i].n;
                board.push_back({board[i].n, nr, nc});
            }
        }
    }
    
    
}


int main(void) {
    cin >> N >> K;
    for(int i=0; i<N; i++){
        for(int j=0; j<N; j++){
            int v;
            cin >> v;
            if(v > 0) board.push_back({v,i,j});
            map[i][j] = v;
        }
    }
    cin >> S >> x >> y;
    x--; y--;
    //낮은순으로 정렬하고 시작
    sort(board.begin(), board.end(), cmp);
    
    while(cnt < S){
        cnt ++;
        breeding();
        if(map[x][y] != 0) break;
    }
    
    cout << map[x][y];
    
    
    return 0;
}
