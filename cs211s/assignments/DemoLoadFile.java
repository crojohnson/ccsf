public class DemoLoadFile
{
    public static void main(String[] args)
    {
        // "numbers.txt" has 5 lines with each
        // line number on corresponding line,
        // starting from 1.
        
        // Print lines 3-5, 1, 5, 5, 5, 1-5, 5, 1-5, 4-5, 1-5
        String[] s = FileLoader.loadFile("numbers.txt", 
                           "3  -$, 1   -1,   5 -$ , $", 
                           "$ - , -5, 5- , - , 4-99, -$");
        for (String str : s) System.out.print(str + " ");
        
        // output:
        // 3 4 5 1 5 5 5 1 2 3 4 5 5 1 2 3 4 5 4 5 1 2 3 4 5
    }
}
        
        
        
        
        /*
        Scanner s = new Scanner(System.in);
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "1,5,7,12"));           // specify lines to be loaded, no line 0
        
        s.nextLine();
        
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "7-310"));              // range specification
        
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "7-310, 6, 120-180"));  // range specification
        
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "7-310, 6, 120-"));     // dash is all the way to end
        
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "7-310, 6, 6, 6, 6"));  // load 6 multiple times
        
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "1-"));                 // entire file
        
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "-38"));                // from beginning to line 38
        
        s.nextLine();
        p("\n----------------------------\n");
        
        p(FileLoader.loadFile("numbers.txt", "1-, 3, 7,7,3,100,6-12")); // totally OK 
        
        s.nextLine();
        p("\n----------------------------\n");
      
        //p(FileLoader.loadFile("numbers.txt", "$, $, $"));                  // last line 3 times!

        //s.nextLine();
        //p("\n----------------------------\n");
        
        //p(FileLoader.loadFile("numbers.txt", "300-"));                  // entire file
        
        //s.nextLine();
        //p("\n------\n");
        
        
        // p(FileLoader.loadFile("cs211s"));
        // "File C:\Users\greg\Documents\java\CS211S-FALL-2016\week04\cs211s cannot be a directory."

        //p(FileLoader.loadFile("not a file"));
        // "Cannot read file at C:\Users\greg\Documents\java\CS211S-FALL-2016\week04\not a file."
    }
    public static void p(String s)
    {
        System.out.println(s); 
    }

    public static void p(String[] s)
    {
        for (String str : s) System.out.println(str);
    }
}
*/