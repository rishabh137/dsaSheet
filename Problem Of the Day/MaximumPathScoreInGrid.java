// 3742. Maximum Path Score in a Grid

/*
You are given an m x n grid where each cell contains one of the values 0, 1, or 2. You are also given an integer k.

You start from the top-left corner (0, 0) and want to reach the bottom-right corner (m - 1, n - 1) by moving only right or down.

Each cell contributes a specific score and incurs an associated cost, according to their cell values:

0: adds 0 to your score and costs 0.
1: adds 1 to your score and costs 1.
2: adds 2 to your score and costs 1. ​​​​​​​
Return the maximum score achievable without exceeding a total cost of k, or -1 if no valid path exists.

Note: If you reach the last cell but the total cost exceeds k, the path is invalid.

 

Example 1:

Input: grid = [[0, 1],[2, 0]], k = 1

Output: 2

Explanation:​​​​​​​

The optimal path is:

Cell	grid[i][j]	Score	Total
Score	Cost	Total
Cost
(0, 0)	0	0	0	0	0
(1, 0)	2	2	2	1	1
(1, 1)	0	0	2	0	1
Thus, the maximum possible score is 2.

Example 2:

Input: grid = [[0, 1],[1, 2]], k = 1

Output: -1

Explanation:

There is no path that reaches cell (1, 1)​​​​​​​ without exceeding cost k. Thus, the answer is -1.


*/


/*
This problem is a grid-based DP where each state is defined by position (i, j) and the current cost used so far. From each cell, we try moving right or down, and we only proceed if the cost does not exceed k. If a path becomes invalid, we return -1. At each step, we take the maximum score from the valid paths and add the current cell value. To avoid recomputation, we use a 3D DP array dp[i][j][cost] which stores the best possible score from that state. This reduces the complexity from exponential to polynomial and ensures we efficiently compute the maximum path score under the given cost constraint.
*/

class Solution {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        Integer[][][] dp = new Integer[m][n][k+1];
        return findMaxPath(grid, k, 0, 0, 0, m, n, dp);
    }

    private int findMaxPath(int[][] grid, int k, int i, int j, int cost, int m, int n, Integer[][][]dp){
        if(i >= m || j >= n) return -1;

        int newCost = cost + (grid[i][j] > 0 ? 1 : 0);

        if(newCost > k) return -1;

        if(i == m-1 && j == n-1) return grid[i][j];

        if(dp[i][j][cost] != null) return dp[i][j][cost];

        int right = findMaxPath(grid, k, i, j+1, newCost, m, n, dp);
        int down = findMaxPath(grid, k, i+1, j, newCost, m, n, dp);

        if(right == -1 && down == -1){
            return dp[i][j][cost] =-1;
        }

        return dp[i][j][cost] = Math.max(right, down) + grid[i][j];
    }
}