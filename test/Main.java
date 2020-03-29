
package tatjana.test;
import java.io.File;
import tatjana.tree.DirectoryTree;
import tatjana.tree.Node;
import static java.lang.System.out;


public class Main {
     public static void main(final String[] arguments)
    {
    
        DirectoryTree dt = new DirectoryTree("C:\\Users\\Korisnik\\Desktop");
        
        System.out.println("getMaxFileSize: " + dt.findMaxSizeFile().getFilename() +  " size: " + dt.findMaxSizeFile().getSize() / ( 1024*1024) + "MB");
        System.out.println("getMaxDirSize: " + dt.findMaxSizeDirectory().getFilename() + " size: "
        + dt.findMaxSizeDirectory().getSize() / (1024 * 1024) + "MB");
    }

    
}
