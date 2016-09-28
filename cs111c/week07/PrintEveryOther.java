/* Write a recursive method to print every other element in an array, 
 * starting with the element in position 0. 
 * The method header is: public void printEveryOther(int[] nums) */

public class PrintEveryOther {
    public static void main(String[] args) {
        int[] arr = {844,43423,19,190,318,12,78,99};
        printEveryOther(arr);
    }
    
    public static void printEveryOther(int[] nums) {
        printEveryOther(nums, 0);
    }

    public static void printEveryOther(int[] nums, int idx) {
        if (idx >= nums.length) return;
        else {
            System.out.println(nums[idx]);
            printEveryOther(nums, idx + 2);
        }
    }
}
