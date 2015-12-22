package Memory;

public class Page {

    public int PageId;
    public String Pagedata;
    public int indexOfProcess;
    Process arr[] = new Process[2];

    //constructor 1
    public Page(int PageId) {
        this.PageId = PageId;
    }

    //constructor 2
    //each page ,i will give it its id(pageid) & prcocess
    public Page(int PageId, Process p) {

        //   super(ProcessId,ProcessData);
        this.PageId = PageId;
        indexOfProcess = p.GetProcessId();

        if (indexOfProcess >= 0 && indexOfProcess < 2) {
            this.arr[indexOfProcess] = p;
        }
    }

    public int getPageId() {
        return PageId;
    }

    // i made the modifiaction on the class of Process
   /* public void modify(int id, Process p) {

     }*/
    //Show the page content(info of processes)
    public void ReadPageData(int PageId) {

        arr[0].GetProcessInfo();
        arr[1].GetProcessInfo();

    }
}
