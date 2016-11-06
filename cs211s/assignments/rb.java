/* srm.java
 * Author : Greg Gorlen
 * Date :   11/4/16
 * Homework assignment : 4
 * Objective : This program performs a safe removal of files
 *
 * Valid arg options:
 * -d : empty recycle bin
 * -s : show contents of recycle bin
 * -r [filenames] : restores files from the recycle bin
 */
 
import static cs211s.Wrappers.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class rb 
{
    public static void main(String[] args) 
    {
        mkWrapper();
        
        final String ARG_OPTS = "dsr";
        int c, i = 0;
        ArrayList<Character> choices = new ArrayList<>();
        GetOpt g = new GetOpt(args, ARG_OPTS);
        
        g.opterr(false); // Suppress display error messages 

        // Determine arg choices
        while ((c = g.getopt()) != -1)
        {
            switch(c)
            {
                case 'd': case 's': case 'r': 
                    choices.add((char)c); break;
                case '?': 
                    die("Usage: java srm [-d] [-s] " + 
                        "[-r] filenames to restore");
            }
        }
        
        // Perform requested actions
        if (choices.contains('s')) // Show files in recycle bin
        {
            displayArray(system("jar tf .RecycleBin.jar"));
        }
        
        if (choices.contains('r')) // Restore files from recycle bin
        {
            // Get filename(s) from args and convert them to string
            String[] restoreFiles = g.getarg();
            StringBuilder sb = new StringBuilder(restoreFiles.length);
            for (String r : restoreFiles) sb.append(" " + r);

            // Extract files from recycle bin
            displayArray(system("jar xf .RecycleBin.jar" + sb.toString()));
            
            // Remove restored files from bin
            displayArray(system("7z d -tzip .RecycleBin.jar"));
        }
        
        if (choices.contains('d')) // Delete recycle bin
        {
            displayArray(system("jar -0c .RecycleBin.jar"));
        }
    }
}


//


/*

String x[] = system("jar f RecycleBin");

for (String str : x) /
{
    for (String k : args) {
        if (k.equals(args[0]);
    }
}

srm abcd xyz mlu

File f = new File(args[0]);

if (f.exists()) ...

*/