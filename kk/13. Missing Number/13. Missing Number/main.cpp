//
//  main.cpp
//  13. Missing Number
//
//  Created by dohan on 2022/10/23.
//

#include <iostream>

class Solution {
public:
    int missingNumber(vector<int>& nums) {
        int n = nums.size();
        int total = (n * (n+1))/2;
        int sum = 0;
        for(auto i: nums){
            sum += i;
        }
        return total - sum;
    }
};
