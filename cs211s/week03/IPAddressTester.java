//  Author:   Greg Gorlen
//  Date  :   9/2/16
//  Homework assignment : 1
//  Objective : This program tests IPAddress.class

import static cs211s.Wrappers.*;

public class IPAddressTester {
    public static void main(String[] args) {
        IPAddress ip = IPAddress.getIPAddressInstance();

        println("\n-gen-speed-test------");
        Profiler p = new Profiler();
        ip.gen(25000);
        println(p.usedTime() * .001 + " seconds.");
        
        println("\n-uGen-speed-test-----");
        p = new Profiler();
        String[] ips = ip.uGen(25000);
        println(p.usedTime() * .001 + " seconds.");

        println("\n-head-test-----------");
        ip.head(ips, 15);

        println("\n-tail-test-----------");
        ip.tail(ips, 15);

        println("\n-sort-speed-test-----");
        p = new Profiler();
        String[] ipsSorted = ip.sort(ips);
        println(p.usedTime() * .001 + " seconds.");
        
        println("\n-sorted-head-test----");
        ip.head(ipsSorted, 15);

        println("\n-sorted-tail-test----");
        ip.tail(ipsSorted, 15);
    }   
}
