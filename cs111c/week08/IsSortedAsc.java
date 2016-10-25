public class IsSortedAsc {
    public static boolean isSortedAscending(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) return false;
        }
        return true;
    }
    
    public static boolean isSortedAscending(Node firstInChain) {
        Node currentNode = firstInChain;
        while (currentNode != null && currentNode.next != null) {
            if (currentNode.data.compareTo(currentNode.next.data) > 0) {
                return false;
            }
            currentNode = currentNode.next;
        }
        return true;
    }
    
    // driver
    public static void main(String[] args) {
        String[] arr = {"3", "2", "2"};
        
        //System.out.println(isSortedAscending(arr));
        
        Node[] nodes = new Node[arr.length];
        
        for (int i = 0; i < arr.length; i++) {
            nodes[i] = new Node(arr[i]);
        }
 
        for (int i = 0; i < arr.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        
        System.out.println(isSortedAscending(nodes[0]));
    }
    
    private static class Node<T extends Comparable<T>> {
        private T data;
        private Node<T> next;
        private Node(T data) { this.data = data; }
    }
}
