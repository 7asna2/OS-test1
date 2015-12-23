public class vFile extends MyType{
    private String extension;
    //store metadata // store data
    
    public vFile() {}
    
    public vFile(folder parent,String name){
        super.setName(name);
        super.setParent(parent);
        super.setDirectory(name);
        super.getParent().inFolder.add(this);
    }   
}