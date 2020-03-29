
package tatjana.tree;

import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;

class InvalidPathException extends Exception
{
    public InvalidPathException()
    {
        super("Invalid path"); //poziva konstruktor roditeljeske klase
    }
}
public class Node {
    String filename;
    long size=0;
    Node[] children = null;
    Node parent; //parent je referenca na Node
    File file;
    
    
    private Node(){ //pomocni konstruktor
        size=0; //ne pokazuje ni na sta
   
    }
    
    public Node(Node Parent, String filepath) throws InvalidPathException
            //throws FileNotFoundException, IOException
    {
        file = new File(filepath);
        if(!file.exists())
            throw new InvalidPathException();

        filename = file.getAbsolutePath();
        //System.out.println(file.getAbsolutePath());
        
        parent = Parent; //prvi put je ovdje null
       
        if(representsFile()==false) //ako je direktorijum
        {
            children = new Node[file.list().length]; //staticki alociran prazan niz za childrene
            createChildren();
            size = calculateSize(); //posmatrace ponovo da li se u tom dir nalaze novi dir ili samo fajlovi
        }
        //Node objekti koji predstavljaju datoteke tj. fajlove imaju praznu listu children
        else{
            children = new Node[0]; 
            size = file.length();
            }
    }
    
    public String getFilename() { //da bi mogli smoplja da se procitaju ovi atributi
        return filename;
    }

    public long getSize() {
        return size;
    }
    
    public long calculateSize()
    {
        long lenght = 0;
        
      if(representsFile()){ //ako je datoteka/fajl
            lenght += file.length();
            return lenght;
        }
        else{ //ako je direktorijum, proracunava sve fajlove u njemu
            for(Node n : children)
                lenght += n.calculateSize(); // ovdje on staje i ceka da se izvrsi poziiv i doda novi lenght na taj 
            this.size = lenght;
        }
        return lenght;
    
    }
    
    public void createChildren() throws InvalidPathException //ova metoda popunjava onaj gore niz childrena
    {
          
        if(representsFile()==false) //ako je dir kreirace children
        {
            File[] files;
            files = file.listFiles();
            for(int i = 0; i < files.length; i++){
                children[i] = new Node(this, files[i].getAbsolutePath()); //pretrazi sve i smjesti u niz u children//prave se Nodovi za sve childrene
                //if(children[i].file.isDirectory())
		//	System.out.println(children[i].file.getName() + ", folder");
                //else 
		//	System.out.println(children[i].file.getName() + ", size (bytes): " + children[i].file.length());
                
        }
    
       }
    }
      
    public boolean representsFile()
    {
        if(file.isDirectory())
            return false;
        else
            return true;
       //return !file.isDirectory(); //vraca true ako nije dir
    }
    
      public Node getMaxFileSize()
    {
        Node maximum = new Node();
       
        if(representsFile()){
            return this;
        }
        else{
            for(Node c: children){
                Node c_tmp = c.getMaxFileSize();
                //if(c.getMaxFileSize().size > maximum.size && c.getMaxFileSize().representsFile()) //dva poziva rekurzije
                if(c_tmp.size > maximum.size && c_tmp.representsFile())
                    maximum = c_tmp;
            }
        }
        return maximum;
    }
      
       public Node getMaxDirSize(Node root) {
       Node maximum = new Node(); //maximum je referenca na objekat klase Node, 
        if (representsFile()) {
            return parent;
        } else {
            Node c_tmp = null;
            for (Node c : children) {
                c_tmp = c.getMaxDirSize(root);
                if (c_tmp.size > maximum.size && c_tmp.representsFile() == false && c_tmp != root)
                    maximum = c_tmp;
            }
        }
        return maximum;
    }
    
    
}
