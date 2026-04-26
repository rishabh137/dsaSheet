// https://leetcode.com/problems/combination-sum/

/*
Given an array of distinct integers candidates and a target integer target, return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.

The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.

The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

 

Example 1:

Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.
Example 2:

Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
Example 3:

Input: candidates = [2], target = 1
Output: []

*/

package Recursion;

import java.util.ArrayList;

class Solution {
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        
        findCombinationSum(candidates, target, 0, 0, result, temp);
        return result;
    }

    private void findCombinationSum(int[] candidates, int target, int i, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> temp){
        if(i == candidates.length || sum > target) return;

        if(sum == target){
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        sum += candidates[i];
        temp.add(candidates[i]);
        findCombinationSum(candidates, target, i, sum, result, temp);
        temp.remove(temp.size()-1);
        sum -= candidates[i];
        findCombinationSum(candidates, target, i+1, sum, result, temp);
    }
}