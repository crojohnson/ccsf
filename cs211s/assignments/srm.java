/* srm.java
 * Author : Greg Gorlen
 * Date :   11/4/16
 * Homework assignment : 4
 * Objective : This program performs a safe removal of files
 */

import static cs211s.Wrappers.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class srm
{
    private static final String RB_PATH = "$HOME/.RecycleBin.jar";
    
    /*******************************main()**************************/
    public static void main(String[] args)
    {
        mkWrapper();

        if (args.length == 0) // No filenames provided
        {
            die("Usage: java srm filenames(s), "
                + "directory or directories to recycle\n"
                + "Warning: this program will overwrite "
                + "files already in the bin sharing the "
                + "same name and directory.");
        }

        // Get working directory
        String dir = system("pwd")[0] + "/";

        StringBuilder sb = new StringBuilder(args.length);
        String action = "c"; /* Adjusts jar parameter for updating,
                                default is create jar */

        // Handle command line parameters for files to recycle
        for (String a : args) sb.append(" " + dir + a);

        /* Check to see if recycle bin exists and
           update it with new files if so */
        File f = new File(RB_PATH);
        if(f.exists()) action = "u";

        // Execute shell command to copy files to the recycle bin jar
        displayArray(system("jar " + action
              + "Mf " + RB_PATH + sb.toString()));

        // Remove the original files
        displayArray(system("rm" + sb.toString()));
    }
}