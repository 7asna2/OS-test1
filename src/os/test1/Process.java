import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Scanner;

public class Process implements Runnable,Comparable<Process> {

   public String name;
    public int ProcessId, arrival, excuteTime, remaining, priority;
    static Scanner input = new Scanner(System.in);

    //remove processdata
    //constructor
  public Process(){
      
  }
    public Process(int ProcessId) {
        this.ProcessId = ProcessId;
        SetProcessName("");
    }

    //constructor
    public Process(int ProcessId, String name) {
        this.ProcessId = ProcessId;
        this.name = name;
       
    }

    public void SetProcessId(int ProcessId) {
        this.ProcessId = ProcessId;
        
    }

    public int GetProcessId() {
        return ProcessId;
    }

    public void SetProcessName(String name) {
        this.name = name;
    }

    public String GetProcessName() {
       
        return name;
    }

   /* public void ModifyProcess(Process p, String name) {
        p.name = name;
    }*/
    
////////////////////////////////////////////////////////////I want to set random periorty
    public void SetPeriority(Process p, int t) {

        if (t > 10) {
            t = 10;
        }
        if (t <= 0) {
            t = 1;
        }

        this.priority = t;
    }

    public void GetProcessInfo() {
   
        System.out.println("Process Info:");
        System.out.println("    Process id: "+this.ProcessId);
        System.out.println("    Process name : " + this.name);
        System.out.println("    process Periority : " + this.priority);
        System.out.println("    process excute time : " + this.excuteTime);
     
    }

   /*void initialize(Process p) {
        System.out.print("Enter Process Name: ");
        name = input.next();
        System.out.print("Enter Arrival Time: ");
        arrival = input.nextInt();
        System.out.print("Enter Duration of the process: ");
        excuteTime = input.nextInt();
        remaining = excuteTime;
        System.out.print("Enter Priority of the process : ");
        priority = input.nextInt();
    }*/

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the num of process: ");
      
          Dispatch ds=new Dispatch();
          CPUScheduler ss = new CPUScheduler(ds.nomOfProcess);
          
          System.out.println(""+ds.nomOfProcess);

        System.out.println("Please select your scheduling technique or Exit the scheduler : ");
        System.out.println("1. First In First Out [FIFO] ");
        System.out.println("2. Shortest Job First [SJF] ");
        System.out.println("3. Round-Robin [RR] ");
        System.out.println("0. ExIT ");
        int option;
        option = Integer.parseInt(br.readLine());
        ss.setoption(option);

        while (option > 3) {
            System.out.println("Please Enter a valid option : ");
            option = Integer.parseInt(br.readLine());
        }
        if (option == 1) {
          //  Collections.sort(ss.processes);
            
            ss.FIFO();

        } else if (option == 2) {
            ss.SJF();

        } else if (option == 3) {
            ss.RoundRobin();
        } else {
            System.out.println("scheduler is Terminated.");
        }
        if (option == 1 || option == 2 || option == 3) {
            ss.getInfo();
            ss.PrintAVG();
        }
    }

   @Override
    public void run() {
        System.out.println("the process" + this.name + "is now Running");
    }
    public int compareTo(Process o) {
        if(this.priority> o.priority)
			return 1;
		else if(this.priority < o.priority)
			return -1;
		else{
			if(this.arrival > o.arrival)
				return 1;
			else if(this.arrival< o.arrival)
				return -1;
			else
				return 0;
        
        
    }

    }
}
  

 
