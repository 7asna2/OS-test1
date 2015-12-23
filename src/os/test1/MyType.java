  public class MyType {
    private String name;
    Directory dir;
    private String dateCreated; //when the file was created
    private folder parent;
    private MyType object;
    
    public MyType() {
        dir=new Directory();
    }
    
    public boolean setName(String name){
        if (null!=this.parent)
        for(int i=0 ;i<this.parent.inFolder.size() ; i++){
            if (this.parent.inFolder.get(i).getName().equals(name) ){
                if(this.parent.inFolder.get(i) instanceof folder && this instanceof folder
                    || this.parent.inFolder.get(i) instanceof vFile && this instanceof vFile){
                    System.out.println("sorry, you can't name it like this, this name is already used");
                    return false;}
            }
        }
        this.name=name;
        return true;
    }
    
    public String getName(){
        return name;
    }
    
    public void setDirectory(String name){
        if(name.equals("PC")) this.dir.setDirectory(name);
        else{
            this.dir=new Directory();
            for(int i=0 ; i<parent.dir.directory.size() ; i++){
                this.dir.setDirectory(parent.dir.directory.get(i));
            }
        this.dir.setDirectory(name);
        }
        
    }
    
    public Directory getDirectory(){
        return dir;
    }
    
    public String get_directory_string (){
        return dir.toString();
    }
    
    public void setParent(folder parent){
        this.parent=parent;
    }
    
    public folder getParent(){
        return parent;
    }
    
    public void print (){
        System.out.println("Name:"+this.name+" dir: "+this.get_directory_string() );
    }
}
