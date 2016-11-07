//  Author:   Greg Gorlen / Abbas Moghtanei
//  Date  :   8/25/16
//  Homework assignment : n/a
//  Objective : This program contains a set of wrappers for basic functions
//****************************************************************

package cs211s;

import java.io.*;
import java.util.Properties;
import java.util.Scanner;
import java.util.ArrayList;

public class Wrappers
{
    //*****************************displayArray()*******************/
    public static void displayArray(String s[])
    {
        for(String t : s) System.out.println(t);
    }
    
    //********************************system()**********************/
    public static String[] system(String cmd)
    {
        ArrayList<String> al = new ArrayList<>();
        String shell = "/bin/bash", option = "-c";
        if(System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1)
        {
           shell = "cmd"; option="/C";
        }
        String ar[] = {shell, option, cmd};
        
        try
        {
           Process p = (new ProcessBuilder(ar)).redirectErrorStream(true).start();
           Scanner sc = new Scanner(p.getInputStream());
           while(sc.hasNext()) al.add(sc.nextLine());
           sc.close();
        }catch(Exception e){System.err.println("commanmd error");}
        return(al.toArray(new String[al.size()]));
    }   
    
    /*******************************mkWrapper()*********************/
    public static void mkWrapper()
    {
        String s = Thread.currentThread().getStackTrace()[1].getClassName();
        String wrapperName = s;
        File f = new File(wrapperName);
        if(!f.exists())
        {
            try
            {
               PrintWriter pw = new PrintWriter(f);
               pw.println("#!/bin/bash");
               pw.println("export PATH=$PATH:.");
               pw.println("export CLASSPATH=.:$CLASSPATH");
               pw.println("java $(basename $0) ${1+\"$@\"}");
               pw.close();
            }catch(FileNotFoundException e){System.err.println(e);}
        }
        try
        { 
           Process p;
           p = new ProcessBuilder("chmod", "+x", f.getAbsolutePath()).start();
        }catch(IOException e){System.err.println(e);}
    }

    /*******************************println()***********************/
    public static void println(Object ... o)   // variable number of arguments
    {
        if (o.length == 0)
        {
            System.out.println();
        }
        else
        {
            System.out.println("" + o[0]);
        }
    }
    
    /*******************************print()**************************/
    public static void print(Object ... o)   // variable number of arguments
    {
        if (o.length == 0)
        {
            System.out.print("");
        }
        else
        {
            System.out.print("" + o[0]);
        }
    }
    
    /*******************************die()****************************/
    public static void die(String ... errmsg)
    {
        if (errmsg.length != 0)
        {
            System.err.println(errmsg[0]);
            System.exit(1);
        }
    }

    /*******************************progName()***********************/
    public static String progName()
    {
        return System.getProperty("sun.java.command").split(" ")[0];
        
        // alternate method:  return Thread.currentThread().getStackTrace()[1].getClassName();
    }

    /*******************************rand()***************************/
    public static int rand(int a, int b) 
    {
        return (int) ((b - a + 1) * Math.random() + a);
    }
    
    /*******************************debug()**************************/
    public static void debug(Object o, boolean ... b)  // boolean for toggling logging
    {
        if (System.getProperty("DEBUG") != null)  // check for existence of property
        {
            if (b.length == 0)
            {
                System.out.println("" + o); // don't debug to .err.println!
            }
            else
            {
                if (b[0]) 
                {
                    String fname = System.getProperty("sun.java.command").split(" ")[0] + ".dbg";
                    
                    File f = new File(fname);
                    
                    try 
                    {
                        // boolean argument true is necessary for appending to a file:
                        PrintWriter pw = new PrintWriter(new FileOutputStream(f, true));   
                        
                        pw.println("" + o);
                        pw.flush(); // flush the buffer, meaning wait a certain amount of time to clear the memory
                        pw.close();
                    } catch (java.io.FileNotFoundException e) {
                        System.err.println(e);
                    }
                }
            }
        }
    }

    /*******************************showJars()***********************/
    public static void showJars() 
    {    
        String str = System.getProperty("sun.boot.class.path");
        //String str = System.getProperty("sun.boot.class.path", "my.jar");
        // not static factory method!!  first arg: all jars if exists, second arg: default
        
        String[] jars = str.split(File.pathSeparator);
        //String[] jars = str.split(":"); // only works on unix, linux, mac!!    
        
        for (String jar : jars) 
        {
            System.out.println(jar);
        }
    }
    
    /*******************************showProperties()*****************/
    public static void showProperties() {
        Properties p = System.getProperties();
        p.list(System.out); 
        // or: println(p); since toString() is overridden from Object
    }
    
    
    /*******************************gets()**************************/
    public static String gets(String ... prompt) 
    {
        String str;
        Console con = System.console();  // notice: no new keyword
        
        if (con != null)
        {
            if (prompt.length != 0)
            {
                str = con.readLine(prompt[0]);
            }
            else
            {
                str = con.readLine();
            }
            return str;
        }
        else
        {
            Scanner sc = new Scanner(System.in);
            if (prompt.length != 0)
            {
                System.out.print(prompt[0]);
            }
            str = sc.nextLine();
            return str; 
        }
    }
}