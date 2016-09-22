// Print a string backwards pre-
public static void printBackwards1(String s) {
    printBackwards1(s, s.length() - 1);
    System.out.println();
}
private static void printBackwards1(String s, int idx) {
    if (idx != -1) {
        System.out.print(s.substring(idx, idx + 1));
        printBackwards1(s, idx - 1);
    }
}

// Print a string backwards post-
public static void printBackwards2(String s) {
    printBackwards2(s, 0);
    System.out.println();
}
private static void printBackwards2(String s, int idx) {
    if (idx != s.length()) {
      printBackwards2(s, idx + 1);
      System.out.print(s.substring(idx, idx + 1));
    }
}

public static boolean isPalindrome(String s) {
    return isPalindrome(s, 0);
}
private static boolean isPalindrome(String s, int idx) {
    if (idx == s.length() / 2) return true;  // base case: we reached the middle of the string
    if (!s.substring(idx, idx + 1).equals(s.substring(s.length() - 1 - idx, s.length() - idx))) {
        return false;
    }
    return isPalindrome(s, idx + 1);
}

public static int populationSize(int startingPopulation, double increaseRate, int numberOfDays) {
    if (numberOfDays == 0) return startingPopulation;
    else {
        startingPopulation += (Integer) startingPopulation * increaseRate;
        return populationSize(startingPopulation, increaseRate, --numberOfDays);
    }
}

public static int findSmallest(int[] numbers) {
    return findSmallest(numbers, numbers[0], numbers.length - 1);
}
public static int findSmallest(int[] numbers, int smallest, int idx) {
    if (idx == 0) return smallest;
    else {
        if (numbers[idx] < smallest) smallest = numbers[idx];
        return findSmallest(numbers, smallest, --idx);
    }
}

public static int sumList(ListInterface<Integer> list) {
	return sumList(list, list.getLength(), 0);
}
private static int sumList(ListInterface<Integer> list, int idx, int total) {
    if (idx == 0) return total;
    else {
        total += list.getEntry(idx);
        return sumList(list, --idx, total);
    }
}

public static int countPositives(BagInterface<Integer> bag) {
	return countPositives(bag, new ArrayBag<Integer>(), 0);
}
private static int countPositives(BagInterface<Integer> bag, BagInterface<Integer> tempBag, int count) {
    if (bag.isEmpty()) return count;
    else {
        int item = bag.remove();
        if (item > 0) count++;
        tempBag.add(item);
        count = countPositives(bag, tempBag, count);
        bag.add(tempBag.remove());
        return count;
    }
}

// In ArrayBag.java :
/** Counts the number of times a given entry appears in this bag.
   @param anEntry  The entry to be counted.
   @return  The number of times anEntry appears in this bag. */
public int getFrequencyOf(T anEntry)
{
    return getFrequencyOf(0, numberOfEntries - 1, anEntry);
}
private int getFrequencyOf(int count, int size, T anEntry) {
    if (size == -1) return count;
    else {
        if (bag[size].equals(anEntry)) count++;
        return getFrequencyOf(count, --size, anEntry);
    }
}

// In LinkedBag.java :
// Locates a given entry within this bag.
// Returns a reference to the node containing the entry, if located,
// or null otherwise.    
private Node getReferenceTo(T anEntry) {
    return getReferenceTo(firstNode, anEntry);
}
private Node getReferenceTo(Node currentNode, T anEntry) {
    if (currentNode == null) return null;
    if (currentNode.data.equals(anEntry)) return currentNode;
    else return getReferenceTo(currentNode.next, anEntry);
}
