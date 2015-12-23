import java.util.LinkedList;

public class Memory {

    public int maxMemroySize;
    LinkedList<Page> Memopages; //LinkedList to store the frames
    int IdOfPro = 0;

    public Memory(int maxMemroySize) {
        this.maxMemroySize = maxMemroySize;
        Memopages = new LinkedList<>();
        Page pg;
        Process p1, p2;

        for (int i = 0; i < maxMemroySize; i++) {
            pg = new Page();
            p1 = new Process(IdOfPro++);
            p2 = new Process(IdOfPro++);
            pg.arr[0] = p1;
            pg.arr[1] = p2;

            Memopages.add(pg);
        }

    }

    public Memory() {
        Memopages = new LinkedList<>();
    }

    /*public boolean write(int PageId, Process p) {
     if (MemoPage.size() >= maxM+
     +
     emroySize) //if memory is exceeded
     {
     System.out.println("MEMORY FULL!");
     return false;
     } else {
     MemoPage.add(new Page(p)); //otherwise add proccess to memory normally.
     return true;
     }
     }*/
    public void ReadMemoData(int PageId) { //get data from proccess with given ID.
        Memory m1 = new Memory();
        for (Page obj : Memopages) {
            if (obj.PageId == PageId) {
                m1.printPage(PageId);
            }
        }

    }

    public void printPage(int pgId) {
        System.out.println("" + Memopages.get(pgId).arr[0]);
        System.out.println("" + Memopages.get(pgId).arr[1]);
    }
    public Process SearchingForProcess(Process newProcess){
        boolean ok=false;
         int IDofProcess=0,IDofPage=0;
        for (int i = 0; i < maxMemroySize; i++) {
            for (int j = 0; j <2; j++) {
                int ID=Memopages.get(i).arr[j].ProcessId;
               
               if( ID==newProcess.ProcessId){//send newprocess to cash
                   IDofPage=i;
                   IDofProcess=j;
                   ok=true;
                   break;
               }
            }
            if(ok)
                break;
        }
        
        //System.out.println(""+IDofPage+"  "+IDofProcess);
        String memType,procesType;
        memType=Memopages.get(IDofPage).arr[IDofProcess].GetProcessName();
        //newProcess.GetProcessInfo();
        procesType=newProcess.GetProcessName();
      
        if(memType.equals(procesType)){
            System.out.println("Process Already Founded in Memory");
        }
        else{
            Memopages.get(IDofPage).arr[IDofProcess].name=procesType;
            System.out.println("Process is Added to Memory");
             System.out.println("Process ID : "+newProcess.ProcessId+", Process Type : "+ newProcess.name);
             
            
        }
        
       return newProcess;
     }

    public void delete(int PageId) { //find process with given ID and delete it from the memory.
        for (int i = 0; i < Memopages.size(); i++) {
            if (Memopages.get(i).PageId == PageId) {
                Memopages.remove(i);
                return;
            }
        }
    }

}
