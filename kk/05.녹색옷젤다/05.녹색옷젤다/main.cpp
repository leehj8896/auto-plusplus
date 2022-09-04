//
//  main.cpp
//  05.녹색옷젤다
//
//  Created by dohan on 2022/09/03.
//

#include <iostream>
#include <queue>

using namespace std;

int N,cnt;
int map[125][125];
int cost[125][125];

int dr[4] = {0,0,1,-1};
int dc[4] = {1,-1,0,0};


void bfs(){
    queue<pair<int,int>> q;
    
    q.push({0,0});
    cost[0][0] = map[0][0];
    
    while(!q.empty()){
        int r = q.front().first;
        int c = q.front().second;
        q.pop();
        
        for(int i=0; i<4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            
            if(nr >= 0 && nc >= 0 && nr < N && nc < N){
                if(cost[nr][nc] > cost[r][c] + map[nr][nc]){
                    cost[nr][nc] = cost[r][c] + map[nr][nc];
                    q.push({nr,nc});
                }
            }
        }
    }
}

int main(void) {
    
    while(1){
        cnt ++;
        cin >> N;
        if (N == 0) break;
        
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                cin >> map[i][j];
                cost[i][j] = 2000000000;
            }
        }
        
        bfs();
        
        cout << "Problem " << cnt << ": " << cost[N-1][N-1] << "\n";
        
        
    }
    
    
    return 0;
}
