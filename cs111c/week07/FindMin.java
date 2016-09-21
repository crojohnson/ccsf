/* Algorithm to find the minimum element in an array recursively with a 
 * single parameter per assignment restriction.
 * Better with an additional parameter for index.
 */

import java.util.Arrays;

public class FindMin {
    public static void main(String[] args){
        int[] arr = {85,17,8,15,16,18,94};
        System.out.println(findMin(arr));
    }
    
    public static int findMin(int[] nums) {
        // Base case: if there is one element in the array, return it.
        if (nums.length == 1) return nums[0];
    
        // Otherwise, get the smallest element from the array 
        // portion up to the last element, exclusive.
        int test = findMin(Arrays.copyOfRange(nums, 0, nums.length - 1));
    
        // Then return the smaller value of either the last element in 
        // the array or the smallest element from the rest of the array.
        if (test > nums[nums.length - 1]) return nums[nums.length - 1];
        else return test;
    }
    
    public static int findMin(int[] nums, int idx) {
        // Base case: idx reached the end of the array,
        // return the last element.
        if (idx == nums.length) return nums[nums.length - 1];
    
        // Test current element against the next index.
        int test = findMin(nums, idx + 1);
    
        // Return the smaller element from the above test.
        if (test > nums[idx]) return nums[idx];
        else return test;
    }
}
