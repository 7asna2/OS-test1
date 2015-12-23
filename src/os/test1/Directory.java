import java.util.ArrayList;
public class Directory {
    ArrayList<String> directory ;
    public Directory() {
        directory = new ArrayList<>();
    }
    
    public void setDirectory(String name){
        directory.add(name);
    }
    
    public String getDirectory(){
        return directory.toString();
    }
    
    @Override
    public String toString (){
        String s="";
        for(int i=0 ; i<directory.size() ; i++)
            s+=directory.get(i)+"\\";
        return s;
    }
    
}
