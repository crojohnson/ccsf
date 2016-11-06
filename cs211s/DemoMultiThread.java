import static cs211s.Wrappers.*;

class DemoMultiThread
{
    public static void main(String[] args) 
    {
        NewThread nt1 = new NewThread("gold");
        NewThread nt2 = new NewThread("silver");
        NewThread nt3 = new NewThread("copper");
        println("gold is alive: " + nt1.t.isAlive());
        println("silver is alive: " + nt2.t.isAlive());
        println("copper is alive: " + nt3.t.isAlive());
        
        try
        {
            nt1.t.join();
            nt2.t.join();
            nt3.t.join();
        } catch (InterruptedException ie) { }  

    }
}