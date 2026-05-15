// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/?envType=daily-question&envId=2026-05-15

/*
153. Find Minimum in Rotated Sorted Array
Suppose an array of length n sorted in ascending order is rotated between 1 and n times. For example, the array nums = [0,1,2,4,5,6,7] might become:

[4,5,6,7,0,1,2] if it was rotated 4 times.
[0,1,2,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums of unique elements, return the minimum element of this array.

You must write an algorithm that runs in O(log n) time.
*/


/**
In a rotated sorted array, one half is always properly sorted and the other half contains the rotation point where the minimum exists. Use binary search and compare `nums[mid]` with `nums[end]`. If `nums[mid] > nums[end]`, it means mid lies in the larger left sorted portion, so the minimum must be on the right side → move `start = mid + 1`. Otherwise, the minimum is either at `mid` or on the left side → move `end = mid`. Continue until `start == end`; that index contains the minimum element.
*/

class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;

        while(start < end){
            int mid = start + (end - start) / 2;

            if(nums[mid] > nums[end]){
                start = mid + 1;
            }else{
                end = mid;
            }
        }

        return nums[start];
    }
}