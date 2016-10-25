/* Write a recursive method to return a list of all indices where a target appears. 
The data is sorted and could contain duplicates. The method header is: 
public ArrayList<Integer> findIndicesRecursiveSorted(Comparable[] array, Comparable target) */

import java.util.ArrayList;

public class PracticeQ4 {
    public static void main(String[] args) {
        Comparable[] sortedArr = { 1, 4, 9, 12, 13, 15, 18, 20, 21, 22, 23, 28 };
        ArrayList<Integer> out = findIndicesRecursiveSorted(sortedArr, 28);
        for (Integer i : out) System.out.print(i + " ");
        System.out.println();
    }
    
    public static ArrayList<Integer> findIndicesRecursiveSorted
                        (Comparable[] array, Comparable target) {
        return findIndicesRecursiveSorted(array, target, new ArrayList<>(), 0);
    }

    public static ArrayList<Integer> findIndicesRecursiveSorted
                        (Comparable[] array, Comparable target, 
                        ArrayList<Integer> result, int idx) {
        if (idx >= array.length || array[idx].compareTo(target) > 0) {
            return result;
        }
        else if (array[idx].equals(target)) {
            result.add(idx);
        }
        return findIndicesRecursiveSorted(array, target, result, ++idx);
    }
}
