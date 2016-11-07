/* rb.java
 * Author : Greg Gorlen
 * Date :   11/4/16
 * Homework assignment : 4
 * Objective : Utility to manage a recycle bin made by srm
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
    private static final String RB_PATH = "$HOME/.RecycleBin.jar";
    private static GetOpt g;

    public static void main(String[] args)
    {
        mkWrapper();

        final String ARG_OPTS = "dsr";
        int c, i = 0;
        ArrayList<Character> choices = new ArrayList<>();
        g = new GetOpt(args, ARG_OPTS);

        g.opterr(false); // Suppress display error messages

        // Determine arg choices
        while ((c = g.getopt()) != -1)
        {
            switch(c)
            {
                case '?':
                    die("Usage: java srm [-d] [-s] " +
                        "[-r] filenames to restore");
                case 'd': case 's': case 'r':
                    choices.add((char)c);
            }
        }

        // Get working directory
        String dir = system("pwd")[0].substring(1) + "/";

        // Perform requested actions
        if (choices.contains('s')) // Show files in recycle bin
        {
            displayArray(system("jar tf " + RB_PATH));
        }

        if (choices.contains('r')) // Restore files from recycle bin
        {
            restoreFiles(dir);
        }

        if (choices.contains('d')) // Delete recycle bin
        {
            newBin();
        }
    }

    private static void restoreFiles(String dir)
    {
        // Get filename(s) from args and convert them to string
        String[] restoreFiles = g.getarg();
        StringBuilder sb = new StringBuilder(restoreFiles.length);

        if (!(restoreFiles[0].length() < 1)) // Filenames were provided
        {
            for (String r : restoreFiles) sb.append(" " + dir + r);
            String filesToRestore = sb.toString();
            println("jar xvf " + RB_PATH + filesToRestore);

            // Extract files from recycle bin
            displayArray(system("jar xvf " + RB_PATH + filesToRestore));

            // Move files to current directory from their extraction location
            displayArray(system("mv " + filesToRestore + " ."));

            // Delete temporary restore directory
            displayArray(system("rm -r " + filesToRestore.split("/")[0]));

            // Remove restored files from bin
            if (system("which zip").length > 0)
            {
                displayArray(system("zip -d " + RB_PATH + filesToRestore));
            }
            else
            {
                // Find files to keep and make a new JAR of them
                StringBuilder newJarContents = new StringBuilder();
                for (String r : system("jar tf " + RB_PATH))
                {
                    if (!filesToRestore.contains(r))
                    {
                    newJarContents.append(" " + r);
                    }
                }
                String contents = newJarContents.toString();

                if (contents.length() > 0)
                {
                    displayArray(system("jar xf " + RB_PATH + contents));
                    displayArray(system("jar cMf " + RB_PATH + contents));
                    displayArray(system("rm -r " + contents));
                }
                else
                {
                    newBin();
                }
            }
        }
        else // filenames were not provided
        {
            String[] files = system("jar xvf " + RB_PATH);
            for (String r : files) sb.append(" " + dir + r);
            String filesToRestore = sb.toString();

            println("mv " + filesToRestore + " .");
            // Move files to current directory from their extraction location
            system("mv " + filesToRestore + " .");

            // Delete temporary restore directory
            system("rm -r " + filesToRestore.split("/")[0]);
            newBin();
        }
    }

    private static void newBin()
    {
        system("mkdir META-INF");
        system("jar cMf " + RB_PATH + " META-INF");
        system("zip -d " + RB_PATH + " META-INF");
        system("rm -r META-INF");
    }
}
