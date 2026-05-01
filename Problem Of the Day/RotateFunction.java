// https://leetcode.com/problems/rotate-function/description/?envType=daily-question&envId=2026-05-01

/*
You are given an integer array nums of length n.

Assume arrk to be an array obtained by rotating nums by k positions clock-wise. We define the rotation function F on nums as follow:

F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1].
Return the maximum value of F(0), F(1), ..., F(n-1).

The test cases are generated so that the answer fits in a 32-bit integer.

 

Example 1:

Input: nums = [4,3,2,6]
Output: 26
Explanation:
F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
So the maximum value of F(0), F(1), F(2), F(3) is F(3) = 26.
Example 2:

Input: nums = [100]
Output: 0
*/

/*
When solving the rotate function, we first define F(0) = sum of i * nums[i] for all i from 0 to n-1. Now when the array is rotated once, every element except the last shifts one position to the right, so each of those elements increases its contribution by its own value. This gives a total increase equal to the sum of all elements except the last one, i.e. (sum - last element). At the same time, the last element moves from index (n-1) to index 0, so its contribution drops from (n-1) * last element to 0, resulting in a loss of (n-1) * last element. Combining both effects, we get:

F(1) = F(0) + (sum - last element) - (n-1) * last element
= F(0) + sum - n * last element

So in general, the relation becomes:
F(k) = F(k-1) + sum - n * nums[n-k]

Memory trick: Each rotation adds total sum (because all elements shift right) and subtracts n times the element that moves to the front.

*/

class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;
        int sum = 0, f = 0;

        for(int i = 0; i < n; i++){
            sum += nums[i];
            f += i * nums[i];
        }

        int max = f;

        for(int i = n-1; i > 0; i--){
            f = f + sum - n * nums[i];
            max = Math.max(max, f);
        }

        return max;
    }
}