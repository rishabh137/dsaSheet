// https://leetcode.com/problems/minimum-operations-to-make-a-uni-value-grid/description/?envType=daily-question&envId=2026-04-28

/*
2033. Minimum Operations to Make a Uni-Value Grid

You are given a 2D integer grid of size m x n and an integer x. In one operation, you can add x to or subtract x from any element in the grid.

A uni-value grid is a grid where all the elements of it are equal.

Return the minimum number of operations to make the grid uni-value. If it is not possible, return -1.

Example 1:


Input: grid = [[2,4],[6,8]], x = 2
Output: 4
Explanation: We can make every element equal to 4 by doing the following: 
- Add x to 2 once.
- Subtract x from 6 once.
- Subtract x from 8 twice.
A total of 4 operations were used.
*/

/*
Think of this problem as **making all numbers equal using steps of size `x`**. First, you check if it’s even possible: for any two numbers to become equal by adding/subtracting `x`, they must have the **same remainder when divided by `x`**. That’s why you store `mod = grid[0][0] % x` and verify every element matches it—otherwise return `-1`. After that, the positions of elements don’t matter, only their values, so you **flatten the 2D grid into a 1D array**. Then you sort the array and choose the **median** as the target value, because the median minimizes the total number of moves (this is a standard property when minimizing absolute differences). Finally, for each number, you compute how many operations it takes to reach the median: `|num - median| / x`, and sum it up.

👉 Memory line: **“Same remainder → flatten → sort → move all to median using steps of x.”**

*/

package Recursion;
import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int mod = grid[0][0] % x;
    int[] nums = new int[m*n];
        int k = 0, count = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] % x != mod) return -1;
                nums[k++] = grid[i][j];
            }
        }

        Arrays.sort(nums);
        int mid = nums.length / 2;

        for(int i = 0; i < nums.length; i++){
            count += Math.abs(nums[i] - nums[mid]) / x;
        }

        return count;
    }
}