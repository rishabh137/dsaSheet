// https://leetcode.com/problems/combination-sum-ii/

/*
40. Combination Sum II

    Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.

    Each number in candidates may only be used once in the combination.

    Note: The solution set must not contain duplicate combinations.

    

    Example 1:

    Input: candidates = [10,1,2,7,6,1,5], target = 8
    Output: 
    [
    [1,1,6],
    [1,2,5],
    [1,7],
    [2,6]
    ]
    Example 2:

    Input: candidates = [2,5,2,1,2], target = 5
    Output: 
    [
    [1,2,2],
    [5]
    ]
*/

/*
    Think of this problem not as “pick or not pick” but as “choose the next number” at every step. You first sort the array so that duplicates come together. Then at each recursion level, you run a loop from the current index and try each number as the next element of your combination. After picking a number, you move forward (j + 1) so it can’t be reused. The key trick is: if two adjacent numbers are the same, only use the first one at that level (if (j > i && arr[j] == arr[j-1]) continue;) — this prevents duplicate combinations. Also, since the array is sorted, if a number becomes larger than the remaining target, you can stop the loop early (break). So overall, the loop helps you systematically try all valid next choices, skip duplicates cleanly, and avoid unnecessary work.
*/

package Recursion;
import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        
        findCombinationSum(candidates, target, 0, result, temp);
        return result;
    }

    private void findCombinationSum(int[] candidates, int target, int i, List<List<Integer>> result, List<Integer> temp){
        if(target == 0){
            result.add(new ArrayList<Integer>(temp));
            return;
        }

        for(int j = i; j < candidates.length; j++){
            if(j > i && candidates[j] == candidates[j-1]) continue;

            if (candidates[j] > target) break;
            
            temp.add(candidates[j]);
            findCombinationSum(candidates, target - candidates[j], j+1, result, temp);
            temp.remove(temp.size()-1);
        }
    }
}