// https://leetcode.com/problems/permutations/description/

// 46. Permutations

/*
In the swapping approach for permutations, the main idea is that at every recursion level, we fix one position and try every possible element at that position. The variable `ind` represents the current position to fix. We loop from `ind` to the end of the array, swap the current element with `nums[ind]`, and recursively solve for the next position using `ind + 1`. After recursion, we swap back to restore the original array state, which is called backtracking. When `ind == nums.length`, it means all positions are fixed and one complete permutation is formed, so we copy the array into the result list. The important mental model is: **everything before `ind` is already fixed, and we are deciding what should come at position `ind`.**

*/


package Backtracking;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        generatePermutaion(nums, 0, result);
        return result;
    }

    private void generatePermutaion(int[] nums, int ind, List<List<Integer>> result){
        if(ind == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int i = 0; i < nums.length; i++){
                temp.add(nums[i]);
            }

            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = ind; i < nums.length; i++){
            swap(i, ind, nums);
            generatePermutaion(nums, ind+1, result);
            swap(i, ind, nums);
        }
    }

    private void swap(int i, int ind, int[] nums){
        int temp = nums[i];
        nums[i] = nums[ind];
        nums[ind] = temp;
    }
}