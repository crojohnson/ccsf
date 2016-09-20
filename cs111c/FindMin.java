/* Algorithm to find the minimum element in an array recursively with a 
 * single parameter.  Would be faster with an additional parameter for index.
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
    
        /* Otherwise, get the smallest element from the array 
         * portion up to the last element, exclusive. */
        int test = findMin(Arrays.copyOfRange(nums, 0, nums.length - 1));
    
        /* Then return the smaller value of either the last element in 
         * the array or the smallest element from the rest of the array. */
        if (test > nums[nums.length - 1]) return nums[nums.length - 1];
        else return test;
    }
}
