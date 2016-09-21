public class DemoLoadFile
{
    public static void main(String[] args)
    {
        // "numbers.txt" has 5 lines with each
        // line number on corresponding line,
        // starting from 1.
        
        // Print lines 3-5, 1, 5, 5, 5, 1-5, 1-5, 1-5, 5
        String[] s = FileLoader.loadFile("numbers.txt", 
                           "3  -$, 1   -1,   5 -$ , $", 
                           "$ - ,  -  $,   , - , 5-99");
        for (String str : s) System.out.print(str + " ");
        
        // output:
        // 3 4 5 1 5 5 5 1 2 3 4 5 1 2 3 4 5 1 2 3 4 5 5
    }
}
