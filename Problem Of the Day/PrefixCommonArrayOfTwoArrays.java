// https://leetcode.com/problems/find-the-prefix-common-array-of-two-arrays/description/?envType=daily-question&envId=2026-05-20

/*
2657. Find the Prefix Common Array of Two Arrays

You are given two 0-indexed integer permutations A and B of length n.

A prefix common array of A and B is an array C such that C[i] is equal to the count of numbers that are present at or before the index i in both A and B.

Return the prefix common array of A and B.

A sequence of n integers is called a permutation if it contains all integers from 1 to n exactly once.

 

Example 1:

Input: A = [1,3,2,4], B = [3,1,2,4]
Output: [0,2,3,4]
Explanation: At i = 0: no number is common, so C[0] = 0.
At i = 1: 1 and 3 are common in A and B, so C[1] = 2.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
At i = 3: 1, 2, 3, and 4 are common in A and B, so C[3] = 4.
Example 2:

Input: A = [2,3,1], B = [3,1,2]
Output: [0,1,3]
Explanation: At i = 0: no number is common, so C[0] = 0.
At i = 1: only 3 is common in A and B, so C[1] = 1.
At i = 2: 1, 2, and 3 are common in A and B, so C[2] = 3.
*/

/*
The most important observation in this problem is that both `A` and `B` are permutations, meaning every number from `1` to `n` appears exactly once in each array. We process both arrays from left to right together, building prefixes step by step. At every index `i`, we include `A[i]` and `B[i]` into the current prefix. Now the question becomes: how do we know when a number has appeared in both prefixes? Instead of repeatedly comparing prefixes, we use a frequency array. The frequency array stores how many times a number has been seen overall while traversing both arrays together. When we see a number for the first time, its frequency becomes `1`, which means it has appeared in only one array so far. When we later encounter the same number in the other array, its frequency becomes `2`. That exact moment tells us the number is now present in both prefixes, so it has become a common element. Therefore, whenever `freq[x] == 2`, we increase the `common` counter.

For every index `i`, we first process `A[i]`, then `B[i]`. After updating frequencies and checking whether any number became common, we store the current `common` count into `result[i]`. The beautiful part of this approach is that we never explicitly compare prefixes or search for common elements again and again. Every number is processed only once from each array, making the solution efficient. A good way to remember this approach is: “A number becomes common exactly when I see it for the second time overall.” Since permutations guarantee at most two occurrences of any number (one in `A` and one in `B`), frequency `2` directly means the number now exists in both prefixes.

*/

class Solution {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;

        int[] freq = new int[n + 1];
        int[] result = new int[n];

        int common = 0;

        for(int i = 0; i < n; i++) {

            freq[A[i]]++;

            if(freq[A[i]] == 2)
                common++;

            freq[B[i]]++;

            if(freq[B[i]] == 2)
                common++;

            result[i] = common;
        }

        return result;
    }
}