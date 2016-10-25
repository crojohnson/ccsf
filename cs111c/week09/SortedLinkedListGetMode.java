public class SortedLinkedListGetMode {
    public static void main(String[] args) {
        SortedLinkedList<Integer> sll = new SortedLinkedList<>();
        
        sll.add(4);
        sll.add(2);
        sll.add(14);
        sll.add(14);
        sll.add(14);
        sll.add(14);
        sll.add(11);
        sll.add(11);
        sll.add(11);
        sll.add(11);
        sll.add(2);
        sll.add(2);
        sll.add(2);
        
        sll.display();
        
        System.out.println(getMode(sll));
    }
    
    /* Returns the mode of a SortedLinkedList */
    public static int getMode(SortedListInterface<Integer> sList) {
        if (sList.isEmpty()) throw new IllegalArgumentException();
        int best = sList.getEntry(1);
        int bestStreak = 1;
        int current = sList.getEntry(1);
        int currentStreak = 1;
        
        for (int i = 2; i <= sList.getLength(); i++) {
            int test = sList.getEntry(i);
            
            if (test == current) {
                currentStreak++;
                
                if (currentStreak > bestStreak) {
                    best = current;
                    bestStreak = currentStreak;
                }
            }
            else {
                current = test;
                currentStreak = 1;
            }
        }
        return best;
    }
}
