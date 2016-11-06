class NewThread implements Runnable
{
    String name;
    Thread t;
    
    NewThread(String tname)
    {
        name = tname;
        t = new Thread(this, name);
        t.start();
    }

    public void run()
    {
        System.out.println("do stuff");
    }
}
