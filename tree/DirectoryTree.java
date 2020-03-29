
package tatjana.tree;


public class DirectoryTree {
    Node root;
    
    public DirectoryTree(String filepath){
        try{
            root = new Node(null, filepath);
        }
        catch(InvalidPathException ex){
             System.out.println("Exception catch");   
             System.out.println(ex.getMessage());
             System.exit(0);
        }
    }
    
    public Node findMaxSizeFile()
    {
        return root.getMaxFileSize();
    }

    public Node findMaxSizeDirectory()
    {
        return root.getMaxDirSize(root);
    }
    
}

