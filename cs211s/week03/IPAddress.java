//  Author:   Greg Gorlen
//  Date  :   9/2/16
//  Homework assignment : 1
//  Objective : This program generates IP addresses 
//              and offers related utilities.

import static cs211s.Wrappers.*;
import java.util.Arrays;

public class IPAddress
{
    private static final int IPV4_QUADRANTS = 4;
    private static final int IP_LOWER_BOUND = 0;
    private static final int IP_UPPER_BOUND = 255;
    
    private IPAddress() { }
    
    /*******************************getIPAddressInstance()***********/
    public static IPAddress getIPAddressInstance()
    {
        return new IPAddress();
    }
    
    /*******************************gen()****************************/
    public String[] gen(int n)
    {
        String[] output = new String[n];
        
        for (int i = 0; i < n; i++)
        {
            output[i] = makeIP(IPV4_QUADRANTS);
        }
        
        assert output.length == n;
        return output;
    }
    
    /*******************************uGen()***************************/
    public String[] uGen(int n)
    {
        String[] output = new String[n];
        
        for (int i = 0; i < n;)
        {
            String candidate = makeIP(IPV4_QUADRANTS);
             
            /* TODO : The following block is very slow,
             * switch to HashSet when Collections become 
             * available for assignments.
             */ 
            if (!Arrays.asList(output).contains(candidate))
            {
                output[i++] = candidate;
            }
        }
        
        assert output.length == n;
        return output;
    }
    
    /*******************************head()***************************/
    public void head(String[] s, int n)
    {
        for (int i = 0; i < n && i < s.length; i++)
        {
            println(s[i]);
        }
    }
    
    /*******************************tail()***************************/
    public void tail(String[] s, int n)
    {
        for (int i = s.length - 1; i >= s.length - n && i >= 0; i--)
        {
            println(s[i]);
        }
    }
    
    /*******************************sort()***************************/
    public String[] sort(String[] s)
    {
        Arrays.sort(s, new java.util.Comparator<String>() 
        {
            @Override
            public int compare(String s1, String s2)
            {
                String[] arr1 = leftZeroPad(s1, 3, ".");
                String[] arr2 = leftZeroPad(s2, 3, ".");
                    
                for (int i = 0; i < arr1.length; i++)
                {
                    int comparison = Integer.parseInt(arr1[i]) - 
                                     Integer.parseInt(arr2[i]);
                
                    if (comparison != 0)
                    {
                        assert !Arrays.deepEquals(arr1, arr2);
                        return comparison;
                    }
                }
                   
                assert Arrays.deepEquals(arr1, arr2);
                return 0;
            }
        });
        
        return s;
    }
    
    /*******************************makeIP()*************************/
    private static String makeIP(int numQuadrants)
    {
        String address = "";
        
        for (int i = 0; i < numQuadrants; i++)
        {
            address += rand(IP_LOWER_BOUND, IP_UPPER_BOUND) + ".";
        }
        
        // Return without trailing period
        return address.substring(0, address.length() - 1);
    }
    
    /*******************************leftZeroPad()********************/
    private static String[] leftZeroPad(String s, int padSize, 
                                        String delimiter)
    {
        String[] arr = s.split("\\" + delimiter);

        for (int i = 0; i < arr.length; i++)
        {
            while (arr[i].length() < padSize)
            {
                arr[i] = "0" + arr[i];
            }
        }
           
        return arr;
    }
}
