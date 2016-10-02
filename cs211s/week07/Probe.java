/* Probe.java
 * Author : Greg Gorlen
 * Date :   10/2/16
 * Homework assignment : 3
 * Objective : This progrm displays information about a Java class
 *
 * Valid arg options:
 * -a : display all
 * -c : display constructors
 * -C : display constants
 * -i : display interfaces
 * -m : display methods
 * -v : display variables
 */
 
import static cs211s.Wrappers.*;
import java.lang.reflect.*;
import java.util.ArrayList;

public class Probe
{
    public static void main(String[] args) 
    {
        final String ARG_OPTS = "acCmvi";
        int c, i = 0;
        ArrayList<Character> choices = new ArrayList<>();
        GetOpt g = new GetOpt(args, ARG_OPTS);
        
        g.opterr(false);  // suppress display error messages 

        // Determine arg choices
        while ((c = g.getopt()) != -1)
        {
            switch(c)
            {
                case 'a': case 'c': case 'C': 
                case 'i': case 'm': case 'v':
                    choices.add((char)c); break;
                case '?': 
                    die("Usage: java Probe [-a] [-c] " + 
                        "[-C] [-i] [-m] [-v] ClassName");
            }
        }
        
        // Collect class name arguments
        String[] classes = g.getarg();    // get filename(s)

        // Verify args were provided
        if (classes[0] == "") die("No class file provided");
        if (choices.size() == 0) die("No options provided");

        // Print specified class contents
        boolean all = choices.contains('a');
        for (String name : classes)
        {
            try
            {
                name = name.replaceAll(".class", "");
                Class cls = Class.forName(name);
                println("Compiled from \"" + name + ".java\"");
                String mods = Modifier.toString(cls.getModifiers());
                if (!mods.contains("interface"))
                {
                    println(mods + " class " + name + " {");
                }
                else {   // this class is an interface
                    println(mods + " " + name + " {");
                }

                if (all || choices.contains('v')) showVariables(cls);
                if (all || choices.contains('C')) showConstants(cls);
                if (all || choices.contains('c')) showConstructors(cls);
                if (all || choices.contains('m')) showMethods(cls);
                if (all || choices.contains('i')) showInterfaces(cls);
                println("}");

            } catch (ClassNotFoundException e)
            {
                System.err.println("Error: class not found: " + name);
            }
        }
    }
    
    /*******************************showConstants()*****************/
    public static void showConstants(Class c)
    {
        Field[] fields = c.getDeclaredFields();
        for (Field f : fields)
        {
            try
            {
                if (Modifier.isFinal(f.getModifiers()))
                {
                    f.setAccessible(true);
                    println(("  " + f + " = " + f.get(null))
                        .replaceAll(c.getName() + ".", "") + ";");
                }
            } catch (Exception e) { }
        }
    }
    
    /*******************************showConstructors()**************/
    public static void showConstructors(Class c)
    {
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor con : constructors)
        {
            println("  " + con + ";");
        }
    }    
    
    /*******************************showInterfaces()****************/
    public static void showInterfaces(Class c)
    {
        Class[] interfaces = c.getInterfaces();
        for (Class i : interfaces)
        {
            println("  " + i + ";");
        }
    }

    /*******************************showMethods()*******************/
    public static void showMethods(Class c)
    {
        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods)
        {
            println(("  " + m)
                .replaceAll(c.getName() + ".", "") + ";");
        }
    }
    
    /*******************************showVariables()*****************/
    public static void showVariables(Class c)
    {
        Field[] fields = c.getFields();
        for (Field f : fields)
        {
            if (!Modifier.isFinal(f.getModifiers()))
                println(("  " + f)
                    .replaceAll(c.getName() + ".", "") + ";");
        }
    }
}
