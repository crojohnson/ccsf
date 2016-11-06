import java.util.ArrayList;

public class GenericStack<E>
{
    private ArrayList<E> al = new ArrayList<E>(); //
    
    // not all methods in a generic class should be generic
    public int getSize() { return al.size(); }
    
    // returns last element
    public E peek() { return al.get(getSize() - 1); }
    
    // add element to end of arraylist
    public void push(E o) { al.add(o); }
    
    // removes and returns last element
    public E pop() 
    { 
        if (!isEmpty()) return al.remove(getSize() - 1); 
        return null;
    }
    
    public boolean isEmpty() { return al.isEmpty(); }
    
    public static void main(String[] args)
    {
        GenericStack<String> gs = new GenericStack<>();
        gs.push("good");
        gs.push("bad");
        gs.push("nice");
        
        GenericStack<Integer> gs1 = new GenericStack<>();
        gs1.push(1);
        gs1.push(5);
        gs1.push(9);
        
        System.out.println(gs.pop());
        System.out.println(gs.pop());
        System.out.println(gs.pop());

        System.out.println(gs1.pop());
        System.out.println(gs1.pop());
        System.out.println(gs1.pop());
        
        GenericStack gs2 = new GenericStack<>(); // automatically gives Object
        GenericStack<Object> gs3 = new GenericStack<>(); // same as above!
    }
}