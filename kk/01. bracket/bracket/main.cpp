//
//  main.cpp
//  bracket
//
//  Created by dohan on 2022/08/20.
//

#include <iostream>
#include <stack>
#include <string>

using namespace std;



int main(void) {
    
    int T;
    cin >> T;
    
    for(int i=0; i<T; i++){
        string b;
        stack<int> s;
        bool f = false;
        cin >> b;
        
        for(auto i : b){
            if(s.empty()){
                if(i == ')') {
                    f = true;
                    continue;
                }
                s.push(i);
            }else if(i == '('){
                s.push(i);
            }else{
                s.pop();
            }
        }
        
        
        if( !s.empty() || f == true){
            cout << "NO\n";
        }else{
            cout << "YES\n";
        }
        
    }
    
    return 0;
    
    
}
