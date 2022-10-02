#include <string>
#include <vector>
#include <map>
#include <sstream>
#include <iostream>

using namespace std;

map <string, pair<string,int> > car;
vector<int> fee;

int calcFee(int a){
    if(a <= fee[0]) return fee[1];
    else{
        a -= fee[0];
        return ( 1 + (a-1)/fee[2] ) * fee[3]  + fee[1];
    }
    return 0 ;
}


int calcTime(string a, string b){
    int ah = stoi(a.substr(0,2));
    int am = stoi(a.substr(3));
    int bh = stoi(b.substr(0,2));
    int bm = stoi(b.substr(3));
    
    return ( bh*60 + bm ) - ( ah*60 + am );
}


vector<int> solution(vector<int> fees, vector<string> records) {
    vector<int> answer;
    vector<string> record;
    fee = fees;

    
    for(int i=0; i<records.size(); i++){
        stringstream ss(records[i]);
        string t;
        record.clear();
        while(getline(ss,t,' ')){
            record.push_back(t);     //[0] hh:mm    [1] 번호   [2]IN/OUT
        }
        
        if( car[record[1]].first == "" ){
            car[record[1]].first = record[0];
        }else{
            car[record[1]].second += calcTime(car[record[1]].first , record[0]);
            car[record[1]].first = "";
        }
    }
    
    //요금계산
    map <string, pair<string,int> > :: iterator i;
    for(i = car.begin(); i!= car.end(); i++){
        
        
        if(i->second.first != ""){
            i->second.second += calcTime(i->second.first, "23:59");
        }
        
        cout << i->first <<" "<< i->second.first <<"\n";
        cout << i->second.second << "\n\n";
        
        answer.push_back(calcFee(i->second.second));
        
    }
    
    
    
    return answer;
}


