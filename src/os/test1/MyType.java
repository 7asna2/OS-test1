package os.test1;


import java.util.ArrayList;

 class MyType {
    private String name;
    private Directory dir;
    private String dateCreated; //when the file was created
    private folder parent;
    private MyType object;
    
    public MyType() {dir=new Directory();}
    
    public boolean setName(String name){    // boolean 3shan l a7otha f while sa3t l GUI 
        
        if (null!=this.parent)
        for(int i=0 ;i<this.parent.inFolder.size() ; i++){
            if (this.parent.inFolder.get(i).getName()== name ){
             System.out.println("no you cant name this ");
            return false;
            }
        }
        this.name=name;
        return true;
    }
    
    public String getName(){
        return name;
    }
    
    public void setDirectory(String name){
        //dir.directory.add(getParent().getDirectory()+name);
        //dir.myDirectory=getParent().getDirectory()+name;
        if(name.equals("PC")) ; // ah eftkrt 3shan l PC
        else
            this.dir=parent.getDirectory();
        this.dir.setDirectory(name);
    }
    
    public Directory getDirectory(){
        //return dir.directory.toString();    // de fekra 7elwa :D :D 
        //return dir.myDirectory;
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
    
    /*public String getDirectoryForBack(){
        return dir.directory.toString();
    }*/
    public void print (){
        System.out.println("Name:"+this.name+" dir: "+this.get_directory_string() );
    }
}
