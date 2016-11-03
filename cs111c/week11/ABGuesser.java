/*The extra credit is described in Chapter 21, Project 6: Write 
an A/B guessing game. Read the textbook description.

The game uses a hashtable to keep track of various "last four 
guess" combinations and associates those with a count of the next 
guess that followed.

For example, the hashtable is used to keep track of this kind of 
information: whenever the last four guesses were "ABBA," the next 
guess was A 1 times and B 3 times, so I (the computer) will guess B. 
Whenever the last four guesses were "BBAB," the next guess was A 2 
times and B 0 times, so I (the computer) will guess A.

In other words, the key is a four-character String and the value 
is an object that describes an A and B count.

I have provided a driver program that contains partial code with 
placeholders for you to fill in the remaining code. Note that you 
do not have to use this solution- you can start from scratch or 
make modifications to what I have provided if you prefer. 

To submit, upload a zip of all your files.

Note: This week's extra credit is more extensive as usual. It is 
more like a lab than a homework. Because of this, it is both worth 
more points and also there is an extended deadline.
*/

import static java.lang.System.*;
import java.util.Scanner;
import java.util.HashMap;

public class ABGuesser {
    private HashMap<String, AB> sequences;
    
    /* play a full game */
    public static void play() {
        Scanner kbd = new Scanner(System.in);
        ABGuesser guesser = new ABGuesser();
        
        // init game variables
        String guesses = "";
        char guess = ' ';
        int correct = 0;
        int incorrect = 0;
            
        while (true) { // main game loop

            out.println("Choose A or B, and I will guess your choice.");
            out.println("Press Return when you are ready.");
            kbd.nextLine();
            
            // get the computer's guess for this turn
            guess = guesser.getGuess(guesses);
            
            if (guess != 'A' && guess != 'B') {
                throw new IllegalStateException();
            }
            
            String response = " ";
            while (response.toLowerCase().charAt(0) != 'n' &&
                   response.toLowerCase().charAt(0) != 'y' &&
                   response.toLowerCase().charAt(0) != 'q') {
                out.print("I guess that you chose " 
                          + guess + "; am I right? ");
                response = kbd.next();
            }
            
            switch (response.toLowerCase().charAt(0)) {
                case 'q' : System.exit(0);
                case 'y' : correct++;  break;
                case 'n' : 
                    // computer was incorrect; adjust guess
                    guess = guess == 'A' ? 'B' : 'A'; 
                    incorrect++;
            }
            
            if (guesses.length() > 3) {
                // update sequence memory
                guesser.addSequence(guesses, guess);
                
                // add current guess or actual response to guesses
                guesses = guesses.substring(guesses.length() - 3) 
                                      + Character.toString(guess);
            }
            else { // sequence hasn't reached length 4 yet
                guesses += Character.toString(guess);
            }

            out.println("Score: " + correct + " correct, " 
                                + incorrect + " incorrect");
        }
    }
    
    private ABGuesser() {
        sequences = new HashMap<>();
    }
    
    // returns most common choice if parameter in memory or a random choice
    private char getGuess(String guesses) {
        char guess = getBest(guesses);
        return guess == ' ' ? randAB() : guess;
    }
    
    // writes a sequence/choice pair to memory
    private void addSequence(String sequence, char choice) {
        if (sequences.get(sequence) == null) {
            sequences.put(sequence, new AB());
        }

        AB temp = new AB();
        if (choice == 'A') { // increment A
            temp.a = ++sequences.get(sequence).a;
            temp.b = sequences.get(sequence).b;
        }
        else if (choice == 'B') { // increment B
            temp.a = sequences.get(sequence).a;
            temp.b = ++sequences.get(sequence).b;
        }
        else {
            throw new IllegalStateException();
        }
        sequences.put(sequence, temp);
    }
    
    /* returns the most common choice if sequence 
       has been seen, or a blank char if unseen */
    private char getBest(String sequence) {
        
        /*// print memory debug
        out.println("The seq: " + sequence);
        for (String k : sequences.keySet()) {
            out.println(k + " " + sequences.get(k).a + " " + sequences.get(k).b );
        }*/
        
        if (sequences.containsKey(sequence)) {
            return sequences.get(sequence).a > 
                   sequences.get(sequence).b ? 'A' : 'B';
        }
        return ' ';
    }
    
    // returns a random choice
    private char randAB() {
        return Math.random() >= .5 ? 'A' : 'B';
    }
    
    // represents a count of A and B choices
    private class AB {
        private int a;
        private int b;
        
        private AB() {
            a = 0;
            b = 0;
        }
    }
    
    // driver
    public static void main(String[] args) {
        ABGuesser.play();
    }
}
