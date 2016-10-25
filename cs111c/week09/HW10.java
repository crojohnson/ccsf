import java.util.ArrayList;

public class HW10 {
    public static void main(String[] args) {
        Comparable[] arr = { 3, 5, 8, 9, 12, 14, 18, 19, 21, 23, 24, 26 };
        
        ArrayList<Integer> out = findIndicesUnsorted(arr, 24);
        //ArrayList<Integer> out = findIndicesUnsorted(arr, 13);
        //ArrayList<Integer> out = searchRecursiveLinearSorted(arr, 18);
        //ArrayList<Integer> out = searchRecursiveLinearSorted(arr, 17);
        //ArrayList<Integer> out = searchRecursiveLinearFromLast(arr, 3);
        //ArrayList<Integer> out = searchRecursiveLinearFromLast(arr, 2);
        
        for (Object o : out) System.out.print(o + " ");
        System.out.println();
    }
    
    /* Returns an ArrayList of all indexes containing a target Object on an unsorted array */
    public static ArrayList<Integer> findIndicesUnsorted(Object[] array, Object target) {
        return findIndicesUnsorted(array, target, new ArrayList<>(), 0);
    }

    private static ArrayList<Integer> findIndicesUnsorted(Object[] array, 
                            Object target, ArrayList<Integer> result, int idx) {
        if (idx >= array.length) return result;
        else if (array[idx].equals(target)) result.add(idx);
        return findIndicesUnsorted(array, target, result, ++idx);
    }

    /* Performs a linear search on a sorted list */
    public static boolean searchRecursiveLinearSorted(Comparable[] array, Comparable target) {
        return searchRecursiveLinearSorted(array, target, 0);
    }
    
    private static boolean searchRecursiveLinearSorted(Comparable[] array, Comparable target, int idx) {
        if (idx >= array.length || array[idx].compareTo(target) > 0) return false;
        else if (array[idx].equals(target)) return true;
        else return searchRecursiveLinearSorted(array, target, ++idx);
    }
    
    /* Performs a linear search starting with the last element */
    public static boolean searchRecursiveLinearFromLast(Object[] array, Object target) {
        return searchRecursiveLinearFromLast(array, target, array.length - 1);
    }
    
    private static boolean searchRecursiveLinearFromLast(Object[] array, Object target, int idx) {
        if (idx < 0) return false;
        else if (array[idx].equals(target)) return true;
        else return searchRecursiveLinearFromLast(array, target, --idx);
    }
}
