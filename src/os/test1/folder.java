import java.util.ArrayList;

public class folder extends MyType{
    //File metaData = new File();//when the file was created
    ArrayList<MyType> inFolder = new ArrayList<>(); //chidren 
    
    public folder(){
    
    }
    public folder(String name) {
        super.setName(name);
    }
    
    public folder(folder parent,String name){
        super.setName(name);
        super.setParent(parent);
        super.setDirectory(name);
        super.getParent().inFolder.add(this);
        
    }
    
}
