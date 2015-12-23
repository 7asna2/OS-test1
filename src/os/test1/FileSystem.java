package os.test1;

public class FileSystem {
    //private vFile newFile;    //leh ?
    private static folder currentFolder;
    private MyType tempFolder ;      //for copy 
    private MyType onClickObject;
    Directory currentDirectory;
    static folder root;
    
    public FileSystem(){
        root =new folder();
        root.setParent(null);
        root.setName("PC");
        currentFolder=root;
        //currentObject=root;
        root.setDirectory("PC");
        currentDirectory=root.getDirectory();
    }
    public void setOnClick (folder f){
        onClickObject=f;
    }
    public void create(String fileOrFolder,String name){
        switch (fileOrFolder) {
            case "File":
                    vFile newFile =new vFile(currentFolder,name);
                break;
            case "Folder":
                   folder newFolder=new folder(currentFolder,name);
                   break;
        }
    }
    
    public void open(){
        if(onClickObject instanceof folder){
            currentFolder=(folder)onClickObject;
        }
        currentDirectory=currentFolder.getDirectory();
        //currentObject=onClickObject; //4ayfa en m4 liha lazma
    }
    
    public void back(){
        //if(currentObject instanceof folder)
            if(currentFolder==root) return; //validation
            currentFolder=currentFolder.getParent();
        //}
        //currentDirectory.directory.remove(currentObject.getName());  //tb w leh ?!
        //currentObject=currentObject.getParent();
        currentDirectory=currentFolder.getDirectory();
    }
    
    public void onClick(MyType myObject){
        onClickObject=myObject;
    }
    
    public void delete(){
        currentFolder.inFolder.remove(onClickObject);
        //currentFolder.getDirectory().remove(onClickObject.getName());
    }
    
    public void copy(){
        tempFolder=onClickObject;
    }
    
    public MyType move(){      // l complete idea msh wad7a blnsbali 
        delete();
        return onClickObject;
    }
    
    public void paste(){ 
        if (tempFolder==null) return;
        tempFolder.setParent(currentFolder);    // el awl h3delo 3shan yd5ol mkano 
        tempFolder.setDirectory(tempFolder.getName());
        currentFolder.inFolder.add(tempFolder);
        //currentFolder.getDirectory().add(onClickObject.getName());
        tempFolder=null;
    }
    
    public void rename(String newName){
        if(newName.equals("PC")) printErrorMsg(); //validation  el mfroud kman y3ml check fe 7aga bnfs l esm wla la2 ????
        else onClickObject.setName(newName);
    }
    // mshoftsh l sorts 
    public void sortOfType(){  
        for (int i = 0; i < currentFolder.inFolder.size(); i++) {
            MyType holded=currentFolder.inFolder.get(i);
            if(holded instanceof vFile){
                currentFolder.inFolder.add(holded);
                currentFolder.inFolder.remove(holded);
            }
        }
    }
    
    public void sortOfName(){
        //sortOfType();
        for (int i = 0; i < currentFolder.inFolder.size(); i++) {
            MyType holded=currentFolder.inFolder.get(i);
            
        }
    }
    
       public static void main(String[] args) {
//        Cache c=new Cache ("FIFO");
//        Process p1=new Process("1");
//        Process p2=new Process("2");
//        Process p3=new Process("3");
//        Process p4=new Process("4");
//        Process p5=new Process("5");
//        p1.setPriority(2);
//        c.readRequest(p3);
//        c.readRequest(p2);
//        c.readRequest(p1);
//        c.readRequest(p3);
//        c.readRequest(p4);
//        c.readRequest(p1);
//        c.readRequest(p1);
//        c.readRequest(p5);
//        c.printinfo();
//__________________________________________________________________________________    
    FileSystem filll = new FileSystem();
           System.out.println(root.get_directory_string());
    filll.view();
    filll.create("File","Ahmed");
    filll.view();
    System.out.println(root.get_directory_string());
    filll.create("Folder", "A");
    filll.view();
    filll.create("Folder", "A");
    filll.create("Folder", "b");
    filll.create("Folder", "f");
    filll.create("Folder", "k");
    filll.view();
    filll.setOnClick((folder) currentFolder.inFolder.get(1));
   // filll.view();
    filll.open();
    filll.view();
    
    
   }
        
    public  void view (){
        for(int i=0 ; i<currentFolder.inFolder.size() ; i++)
            currentFolder.inFolder.get(i).print();
        System.out.println("\n \n \n");
    }

    private void printErrorMsg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}