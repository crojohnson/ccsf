import static cs211s.Wrappers.*;

public class WrappersTest
{
    
    public static void main(String[] args)
    {
        print("HI");
        print("\n");
        println("HI");
        
        println(progName());

        for (int i = 0; i < 10; i++) {
            println("" + rand(1, 100));
        }
        
        debug("Debug test #1");
        debug("Debug test #1", true); // dumps to NameOfProgram.dbg
        showProperties();
        
        showJars();

        String name = gets("Your name please: ");
        String age = gets("How old are you, " + name + "? ");
        println("Hi, " + name + ", age " + age);
        
        die("testing death...");
    }
}