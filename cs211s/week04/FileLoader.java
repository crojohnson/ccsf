//  Author:   Greg Gorlen
//  Date  :   9/8/16
//  Homework assignment : 2
//  Objective : This program reads a file with optional 
//              specification parameter for line and range.

import static cs211s.Wrappers.*;
import java.util.*;
import java.io.*;

public class FileLoader
{    
    private static ArrayList<String> input;
    private static List output;

    private FileLoader() { }
    
    /*******************************loadFile()**********************/
    public static String[] loadFile(String filename, String ... range)
    {
        getInput(filename);

        if (range.length == 0)
        {
            String[] arr = new String[input.size()];
            return input.toArray(arr);
        }
        else {
            output = new ArrayList<>();
            
            // Outer loop to handle variable range args
            for (int i = 0; i < range.length; i++) 
            {
                // Commas delimit each range specification
                String[] currentRangeSet = range[i].split(",");

                // Attempt parsing each range
                for (int j = 0; j < currentRangeSet.length; j++)
                {
                    parseRange(currentRangeSet[j].replaceAll("\\s",""));
                }
            }
            
            String[] arr = new String[output.size()];
            return (String[]) output.toArray(arr);
        }
    }
    
    /*******************************parseRange()********************/
    private static void parseRange(String s)
    {
        verify(s);  // Terminates program if input String is invalid
        
        int from = 0;
        int to = 0;
        
        // Handle digit-only
        if (!s.contains("-") && digit(s))
        {
            int location = Integer.parseInt(s);
            from = location;
            to = location;
        }
        // Handle full range
        else if (s.equals("-") || s.equals("")) {
            from = 1;
            to = input.size();
        }
        else   // Handle various dash ranges
        {
            if (s.indexOf("-") == 0)  // Dash at beginning
            {
                from = 1;
            }
            else if (s.indexOf("-") == s.length() - 1) // Dash at end
            {
                String candidate = s.replaceAll("-", "");
                if (digit(candidate))
                {
                    from = Integer.parseInt(candidate);
                }
                to = input.size();
            }
            else   // Dash in middle
            {
                String[] range = s.split("-");
                if (digit(range[0])) 
                {
                    from = Integer.parseInt(range[0]);
                }
                if (range.length == 2 && digit(range[1]))
                {
                    to = Integer.parseInt(range[1]);
                }
            }
        }        
            
        // Handle dollar signs
        if (s.indexOf("$") == 0)
        {
            from = input.size();
        }
        if (s.indexOf("$") == s.length() - 1)
        {
            to = input.size();
        }
        addOutput(from, to, s);
    }
    
    /*******************************addOutput()*********************/
    private static void addOutput(int from, int to, String s)
    {
        // Constrain bounds
        if (from < 1)
        {
            die("Invalid range argument: " + s + 
            "\nRange cannot be smaller than 1.");
        }
        if (to > input.size())
        {
            to = input.size();
        }
        
        if (from > to) 
        {
            die("Invalid range argument: " + s 
            + "\nRange start cannot exceed range end.");
        }
        else 
        {
            output.addAll(input.subList(from - 1, to));
        }
    }
    
    /*******************************verify()************************/
    private static void verify(String s)
    {
        int dollarCount = 0;
        int dashCount = 0;
           
        for (int i = 0; i < s.length(); i++) 
        {
            char c = s.charAt(i);

            if (!Character.isDigit(c) &&
                !Character.isWhitespace(c) &&
                !(c == '$') && !(c == '-'))
            {
                die("Invalid range argument: " + s + 
                "\nRange character " + c + " not recognized.");
            }
            
            if (c == '$')
            {
                if (++dollarCount > 1)
                {
                    die("Invalid range argument: " + s + 
                    "\nOnly one dollar sign allowed " +
                    "per range specification.");
                }
            }
            else if (c == '-')
            {
                if (++dashCount > 1)
                {
                    die("Invalid range argument: " + s +  
                    "\nOnly one dash sign allowed " +
                    "per range specification.");
                }
            }
        }
    }
    
    /*******************************digit()*************************/
    private static boolean digit(String s)  
    {
        try  
        {
            Integer.parseInt(s);
        } catch(NumberFormatException nfe)
        {
            return false;
        }  
        return true;  
    }
    
    /*******************************getInput()**********************/
    private static void getInput(String filename)
    {
        input = new ArrayList<>();
        File f = new File(filename);

        if (f.isDirectory())
        {
            die("File " + f.getAbsolutePath() + 
            " cannot be a directory.");
        }
        if (!f.canRead())
        {
            die("Cannot read file at " + f.getAbsolutePath() + ".");
        }
        if (!f.isFile())
        {
            die(f.getAbsolutePath() + " is not a file.");
        }
        
        try 
        {
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                input.add(sc.nextLine());
            }
            sc.close();
            
        } catch (Exception ex) 
        {
            die(ex.toString());
        }
    }
}
