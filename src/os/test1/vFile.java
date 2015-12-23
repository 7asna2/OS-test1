package os.test1;

public class vFile extends MyType{
    private String extension;
    //store metadata // store data
    
    public vFile() {}
    
    public vFile(folder parent,String name){
        super.setName(name);
        setParent(parent);
        setDirectory(name);
        parent.inFolder.add(this);
    }
   
}