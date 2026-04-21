/**
 https://leetcode.com/problems/two-furthest-houses-with-different-colors/?envType=daily-question&envId=2026-04-20
 
To maximize the distance, you don’t need to check all pairs—just focus on the ends. The maximum distance will always involve either the first or the last house. So, fix the first house and scan from the right to find the farthest house with a different color, then fix the last house and scan from the left to do the same. Compute both distances and return the maximum.
*/
class Solution {
    public int maxDistance(int[] colors) {
        int left = 0, right = 0;
        int n = colors.length;
        int i = 0, j = n-1;

        while(colors[0] == colors[j]) j--;
        left = j;

        while(colors[n-1] == colors[i]) i++;
        right = (n-1) - i;

        return Math.max(left, right);
    }
}