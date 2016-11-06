
import java.lang.reflect.Constructor;
import java.util.Date;

public class ReflectionPlay {
    
    public static void main(String[] args) throws Exception {
        /*Class c1 = Class.forName("java.lang.String");
        System.out.println(c1);
        Class c2 = Class.forName("[I");
        System.out.println(c2.getName());
        */
        //Class c3 = Class.forName("[[Ljava.awt.Button");

        
        Class c = Date.class;
        Class c1 = c.getSuperclass(); // <--you can keep applying this to get all superclasses up to object
        System.out.println(c.getName());
        Constructor[] con = c.getConstructors();  //<--gets constructors
        for (Constructor cc : con) System.out.println(cc);
    }
}