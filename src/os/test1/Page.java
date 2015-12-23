
public class Page {
public int PageId;
    public String Pagedata;
    public int indexOfProcess;
    Process arr[] = new Process[2];

    public  Page(){
    }
    //constructor 1
    public Page(int PageId) {
        this.PageId = PageId;
    }
    

    //constructor 2

    public Page(Process p){

        this.PageId = p.ProcessId / 2;
        indexOfProcess = p.ProcessId % 2;
        this.arr[indexOfProcess] = p;

    }
   
    public int getPageId() {
        return PageId;
    }

         
      
    
    }
     
   /* public boolean empty(int PageId) {
        if(arr[PageId] == null) {
            return true;
        } else {
            return false;
        }
    }*/
/*public class Page {

    public int PageId;
    public String Pagedata;
    public int indexOfProcess;
    int PageNum;
    int IndexInArray;

    Process arr[] = new Process[2];
    public boolean EmptyPage = false;

    //constructor 1
   /* public Page(int PageId) {
     this.PageId = PageId;
     }
     *//*
    public Page(int ProcessId, Process p) {

        this.PageId = ProcessId / 2;
        indexOfProcess = ProcessId % 2;
        this.arr[indexOfProcess] = p;

    }

    //constructor 2
    public Page(Process p) {

    }

    public void WriteProcessInPage(Process p) {
        /*  //   super(ProcessId,ProcessData);
         this.PageId = PageId;
         indexOfProcess = p.GetProcessId();

         if (indexOfProcess >= 0 && indexOfProcess < 2) {
         this.arr[indexOfProcess] = p;
         }
         }*//*
        PageNum = p.GetProcessId();
        PageId = PageNum / 2;

        IndexInArray = PageNum % 2;
        arr[IndexInArray] = p;
        EmptyPage = false;

    }

    public void ReadPageData(int PageId) {

        if () {
            System.out.println("Not Empty !!");
            for (int i = 0; i < 2; i++) {
                System.out.println(arr[i].GetProcessId());
            }
    //arr[indexOfProcess].GetProcessInfo();
            //arr[indexOfProcess + 1].GetProcessInfo();
        } else {
            System.out.println("There is no data in this page !");
        }
        EmptyPage = false;
        //PageNotEmpty=false;
    }

    public boolean empty(int PageId) {
        if (arr[PageId] == null) {
            return true;
        } else {
            return false;
        }
    }
}*/
//write page data
