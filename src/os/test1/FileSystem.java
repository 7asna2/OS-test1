
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

public class FileSystem {

    private static folder currentFolder;
    private MyType tempFolder;      //for copy 
    private MyType onClickObject;
    Directory currentDirectory;
    static folder root;

    public FileSystem() {
        root = new folder();
        root.setParent(null);
        root.setName("PC");
        currentFolder = root;
        root.setDirectory("PC");
        currentDirectory = root.getDirectory();
    }

    public void create(String fileOrFolder, String name) {
        switch (fileOrFolder) {
            case "File":
                for (int i = 0; i < this.currentFolder.inFolder.size(); i++) {
                    if (this.currentFolder.inFolder.get(i).getName().equals(name)) {
                        if (this.currentFolder.inFolder.get(i) instanceof vFile) {
                            System.out.println("sorry, you can't name it like this, this name is already used");
                            return;
                        }
                    }
                }
                vFile newFile = new vFile(currentFolder, name);
                //toJSON(newFile);
                onClickObject = newFile;
                break;
            case "Folder":
                for (int i = 0; i < this.currentFolder.inFolder.size(); i++) {
                    if (this.currentFolder.inFolder.get(i).getName().equals(name)) {
                        if (this.currentFolder.inFolder.get(i) instanceof folder) {
                            System.out.println("sorry, you can't name it like this, this name is already used");
                            return;
                        }
                    }
                }
                folder newFolder = new folder(currentFolder, name);
               // toJSON(newFolder);
                onClickObject = newFolder;
                break;
        }
    }

    public void open() {
        if (onClickObject instanceof folder) {
            currentFolder = (folder) onClickObject;
        }
        currentDirectory = currentFolder.getDirectory();
    }

    public void back() {
        if (currentFolder.equals(root)) {
            return; //validation
        }
        currentFolder = currentFolder.getParent();
        currentDirectory = currentFolder.getDirectory();
    }

    public void onClick(MyType myObject) {
        onClickObject = myObject;
    }

    public void delete() {
        currentFolder.inFolder.remove(onClickObject);
    }

    public void copy() {
        tempFolder = onClickObject;
    }

    public void move() {
        delete();
        tempFolder = onClickObject;
    }

    public void paste() {
        if (tempFolder == null) {
            return;
        }
        tempFolder.setParent(currentFolder);    // el awl h3delo 3shan yd5ol mkano 
        tempFolder.setDirectory(tempFolder.getName());
        currentFolder.inFolder.add(tempFolder);
        tempFolder = null;
    }

    public void rename(String newName) {
        if (newName.equals("PC")) {
            printErrorMsg(); //validation  el mfroud kman y3ml check fe 7aga bnfs l esm wla la2 ????
        } else {
            onClickObject.setName(newName);
            onClickObject.setDirectory(newName);
        }
    }

    public void sortOfType() {
        for (int i = 0; i < currentFolder.inFolder.size(); i++) {
            MyType holded = currentFolder.inFolder.get(i);
            if (holded instanceof vFile) {
                currentFolder.inFolder.add(holded);
                currentFolder.inFolder.remove(holded);
            }
        }
    }

    public void sortOfName() {
        //sortOfType();
        for (int i = 0; i < currentFolder.inFolder.size(); i++) {
            MyType holded = currentFolder.inFolder.get(i);
        }
    }

    public String toJSON(MyType obj) {
        Gson gson = new Gson();
        String json=gson.toJson(obj);
        try {
            FileWriter writer = new FileWriter("c:\\file.json");
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("json: "+json);   
        return json;
    }

    public void fromJSON(BufferedReader br, Class<MyType> classy) {
        Gson gson = new Gson();
        try {
            br = new BufferedReader(new FileReader("c:\\file.json"));
            MyType obj = gson.fromJson(br, MyType.class);
            System.out.println(obj);

        } catch (IOException e) {
            e.printStackTrace();
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
        System.out.println("rooot:  " + root.get_directory_string());
        filll.view();
        filll.create("File", "Ahmed");
        filll.view();
        //filll.delete();
        System.out.println("rooot:  " + root.get_directory_string());
        filll.create("Folder", "A");
        filll.view();
        //filll.delete();
        filll.rename("lolo");
        filll.create("Folder", "A");
        filll.create("Folder", "b");
        filll.create("File", "f");
        filll.create("Folder", "k");
        filll.view();
        filll.sortOfType();
        System.out.println("i'm sorted");
        filll.view();
        filll.onClick(currentFolder.inFolder.get(1));
        // filll.view();
        filll.open();
        filll.view();

    }

    public void view() {
        for (int i = 0; i < currentFolder.inFolder.size(); i++) {
            currentFolder.inFolder.get(i).print();
        }
        System.out.println("\n \n \n");
    }

    private void printErrorMsg() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
