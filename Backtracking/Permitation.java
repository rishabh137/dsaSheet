// https://leetcode.com/problems/permutations/description/

// 46. Permutations

/*
In the swapping approach for permutations, the main idea is that at every recursion level, we fix one position and try every possible element at that position. The variable `ind` represents the current position to fix. We loop from `ind` to the end of the array, swap the current element with `nums[ind]`, and recursively solve for the next position using `ind + 1`. After recursion, we swap back to restore the original array state, which is called backtracking. When `ind == nums.length`, it means all positions are fixed and one complete permutation is formed, so we copy the array into the result list. The important mental model is: **everything before `ind` is already fixed, and we are deciding what should come at position `ind`.**
*/

// ------ OR -------

/*
Think of permutation generation like fixing one position at a time.  
At every recursion level, `ind` represents the current position that we want to fill. We try every possible number for that position by swapping. Suppose the array is `[1,2,3]` and `ind = 0`. We first place `1` at index `0` by swapping `(0,0)`, then recursively solve for the remaining positions. After that, we place `2` at index `0` by swapping `(1,0)`, then recurse again. Then we place `3` at index `0` by swapping `(2,0)`. This means every number gets a chance to become the first element. Once the first position is fixed, recursion moves to the next index and repeats the same process for the remaining part of the array. When `ind == nums.length`, it means all positions are fixed and one complete permutation is ready, so we store it in the answer.

The most important idea to remember is **swap → recurse → backtrack**.  
Swapping puts a new element at the current position. Recursive call explores all possibilities from that arrangement. After recursion finishes, we swap back to restore the original array before trying the next possibility. This restoring step is called backtracking. Without swapping back, the array would remain modified and future permutations would become incorrect. So the algorithm works by continuously making choices, exploring them deeply, and then undoing them to try another choice.
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