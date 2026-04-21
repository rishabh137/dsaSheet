/**
https://leetcode.com/problems/assign-cookies/description/

Sort both the greed array and the cookie sizes, then use two pointers to try matching them from smallest to smallest. Always try to satisfy the least greedy child first using the smallest available cookie. If the current cookie is big enough for the current child, assign it and move both pointers forward; otherwise, skip that cookie and try a bigger one. This ensures you don’t waste larger cookies on smaller greed and maximizes the total number of satisfied children.
*/

package Greedy;
import java.util.Arrays;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0, content = 0;

        while(i < g.length && j < s.length){
            if(g[i] <= s[j]){
                content++;
                i++;
                j++;
            }else{
                j++;
            }
        }

        return content;
    }
}