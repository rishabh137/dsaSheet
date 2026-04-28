// https://leetcode.com/problems/palindrome-partitioning/

/*
Given a string s, partition s such that every substring of the partition is a palindrome. Return all possible palindrome partitioning of s. 

Example 1:

Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]
Example 2:

Input: s = "a"
Output: [["a"]]
*/

/*
Think of this problem as **cutting the string into pieces such that every piece is a palindrome**. You start from index `i = 0` and try every possible cut ending at `j` (i.e., substring `s[i…j]`). For each `j`, you first check: *is this substring a palindrome?* If yes, you **choose** it (add `s.substring(i, j+1)` to your list), then **recurse** from the next index `j + 1` to solve the remaining string. When you reach the end of the string (`i == s.length()`), it means you’ve formed a valid partition, so you add the current list to the result. After each recursive call, you **backtrack** by removing the last added substring so you can try the next possible cut. This “try all cuts + only continue if palindrome + backtrack” pattern ensures you explore all valid partitions without duplicates.

👉 Memory line: **“Fix a start index, try all cuts, pick only palindromes, recurse forward, then undo.”**

*/

package Recursion;
import java.util.*;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        findPartition(s, 0, result, temp);
        return result;
    }

    private void findPartition(String s, int i, List<List<String> result, List<String> temp){
        if(i == s.length()){
            result.add(new ArrayList<String>(temp));
            return;
        }

        for(int j = i; j < s.length(); j++){
            if(isPalindrome(s, i, j)){
                temp.add(i, j+1);
                findPartition(s, j+1, result, temp); 
                temp.remove(temp.size())-1;       
            }
        }
    }

    private boolean palindrome(String str, int start, int end){
        while(start < end){
            if(str.charAt(start) != str.charAt(end)) return false;
            start++;
            end--;
        }

        return true;
    }
}