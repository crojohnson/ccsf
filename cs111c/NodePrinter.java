public class NodePrinter {
    public static void main(String[] args){
        int[] init = {5,6,8,1,34,5,523};
        Node[] nodes = new Node[init.length];
        
        for (int i = 0; i < init.length; i++) {
            nodes[i] = new Node(init[i]);
        }
        
        for (int i = 0; i < init.length - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        
        //for (Node n : nodes) System.out.println(n.data);
        
        printData(nodes[0]);
    }
    
    // Print nodes recursively
    public static void printData(Node firstNode) {
        if (firstNode == null) return;  // base case, end of chain
        else {
            System.out.println(firstNode.data);
            printData(firstNode.next);
        }
    }
    
    static class Node {
        public Node next;
        public int data;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
