//
//  main.cpp
//  07.벽 부수고 이동하기
//
//  Created by dohan on 2022/09/04.
//

#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <queue>

using namespace std;


int N, M;
int map[1001][1001];
int visit[1001][1001][2]; // 1: 안부숨 0: 부숨
int dr[4] = {0,0,-1,1};
int dc[4] = {1,-1,0,0};


int bfs() {
    queue<pair<pair<int, int>, int>> q;     // 1: 안부숨 0: 부숨
    q.push({{0,0},1});
    visit[0][0][1] = 1;
    
    
    while(!q.empty()){
        
        int r = q.front().first.first;
        int c = q.front().first.second;
        int breaked = q.front().second;
        q.pop();
        
        //목적지 도착하면 리턴
        if(r ==  N-1 && c == M-1) return visit[r][c][breaked];
        
        for(int i=0; i<4 ; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if(nr >= 0 && nc >= 0 && nr < N && nc < M && !visit[nr][nc][breaked]){
                if(map[nr][nc] == 0){
                    visit[nr][nc][breaked] = visit[r][c][breaked]+1;
                    q.push({{nr,nc},breaked});
                }else if(breaked == 1){
                    visit[nr][nc][0] = visit[r][c][1] + 1;
                    q.push({{nr,nc},0});
                }
            }
        }
        
    }
    return  -1;
    
}

int main(void) {
    //input
    cin >> N>> M;
    for(int i=0; i<N; i++){
        string a;
        cin >> a;
        for(int j=0; j<M; j++){
            map[i][j] = a[j] - '0';
        }
    }
    
    //bfs
    cout << bfs();
    
    return 0;
}
