// 90. Subsets II
// Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

// The solution set must not contain duplicate subsets. Return the solution in any order.

// Example 1:

// Input: nums = [1,2,2]
// Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
// Example 2:

// Input: nums = [0]
// Output: [[],[0]]
 

package Recursion;
import java.util.*;

class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        findSubset(nums, 0, temp, result);
        return result;
    }

    private void findSubset(int[] nums, int i, List<Integer> temp, List<List<Integer>> result){
        if(i == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }

        temp.add(nums[i]);
        findSubset(nums, i+1, temp, result);
        temp.remove(temp.size()-1);

        while(i+1 < nums.length && nums[i] == nums[i+1]) i++;
        findSubset(nums, i+1, temp, result);
    }
}