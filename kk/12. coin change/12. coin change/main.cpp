//
//  main.cpp
//  12. coin change
//
//  Created by dohan on 2022/10/22.
//

#include <iostream>
#include <vector>


using namespace std;


class Solution {
public:
    int coinChange(vector<int>& coins, int amount) {
        if (amount == 0) return 0;
        
        vector<int> dp(amount+1, 10001);
        dp[0] = 0;

        for (int value =1; value <=amount ; ++value)
            for (int i = 0; i< coins.size(); ++i)
                if (value - coins[i] >= 0)
                    dp[value] = min(dp[value], dp[value - coins[i]] +1);
            
        if (dp[amount] == 10001)
            return -1;
        return dp[amount];
    }
};

int main(void) {
    return 0;
}
