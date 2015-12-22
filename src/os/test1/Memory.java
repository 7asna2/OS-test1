package os.test1;
import Memory.Page;
import java.util.LinkedList;

public class Memory {
    
    public int maxMemroySize;
    LinkedList<Page> MemoPage; //LinkedList to store the frames

    public Memory(int maxMemroySize) {            //constructor to get size from main
        this.maxMemroySize = maxMemroySize;
        MemoPage = new LinkedList<>();

    }

    public Memory() {
        this.maxMemroySize = 12;
        MemoPage = new LinkedList<>();
    }

    public boolean write(int PageId, Process p) {
        if (MemoPage.size() >= maxMemroySize) //if memory is exceeded
        {
            System.out.println("MEMORY FULL!");
            return false;
        } else {
            MemoPage.add(new Page(PageId,p)); //otherwise add proccess to memory normally.
            return true;
        }
    }

    public void ReadMemoData(int PageId) { //get data from proccess with given ID.
      /*  Page pg= new Page(PageId);
      pg.ReadPageData(PageId);*/
        
        for (Page obj : MemoPage) {
            if (obj.PageId == PageId) 
               obj.ReadPageData(PageId);
        }

    }

    public void delete(int PageId) { //find process with given ID and delete it from the memory.
        for (int i = 0; i < MemoPage.size(); i++) {
            if (MemoPage.get(i).PageId == PageId) {
                MemoPage.remove(i);
                return;
            }
        }
    }

   
}

