
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;

public class CPUScheduler implements Runnable{

    int nomOfProcess;
    int option;
    LinkedList<Process>processes;
    LinkedList<Integer>waiting,turnaround,response;
    
    public CPUScheduler(int nomOfProcess) {
        this.nomOfProcess = nomOfProcess;
        processes=new LinkedList<>();
        waiting=new LinkedList<>();
        turnaround=new LinkedList<>();
        response=new LinkedList<>();
        
        for (int i = 0; i < 1000; i++) {
                waiting.add(0);
                turnaround.add(0);
                response.add(0);
            }
        
    }

    CPUScheduler() {
        
    }

   
    void setoption(int option){
        this.option=option;
    }
    int setoption(){
        return option;
    }
    void FIFO(){
         int finish = 0;
            for (int i = 0; i < nomOfProcess; i++) {
                finish += processes.get(i).excuteTime;            
              waiting.set(i,finish - processes.get(i).arrival - processes.get(i).excuteTime);
            }
            for (int i = 0; i < nomOfProcess; i++) {
                turnaround.set(i,waiting.get(i) + processes.get(i).excuteTime);
            }
    }
    void SJF(){
         int finish = 0, sum, time;
            for (int i = 0; i <nomOfProcess; i++) {
                time = processes.get(i).arrival;
                if (nomOfProcess - i > 1) {

                    if (i == 0) {
                        while (time < processes.get(i+1).arrival) {
                            processes.get(i).remaining--;
                            finish++;
                            time++;
                        }
                    } else {
                        while (time < processes.get(i+1).arrival) {
                            if (processes.get(i).remaining < processes.get(i-1).remaining) {
                                processes.get(i).remaining--;
                            } else {
                               processes.get(i-1).remaining--;
                            }
                            finish++;
                            time++;
                        }
                       
                        if (processes.get(i-1).remaining == 0 && turnaround.get(i-1) == 0) {
                            turnaround.set(i-1,finish - processes.get(i-1).arrival );
                           
                        }

                    }

                    if (processes.get(i).remaining == 0 && turnaround.get(i) == 0) {
                        turnaround.set(i,finish - processes.get(i).arrival );
                      
                    }

                } else {
                    int minprocess, minindex = 0;
                    do {
                        minprocess = 1000000;
                        for (int j = 0; j < nomOfProcess; j++) {
                            if (processes.get(j).remaining < minprocess &&processes.get(j).remaining != 0) {
                                minprocess = processes.get(j).remaining;
                                minindex = j;
                            }
                        }
                        finish += processes.get(minindex).remaining;
                        processes.get(minindex).remaining = 0;
                        turnaround.set(minindex,finish - processes.get(minindex).arrival );
           
                        sum = 0;
                        for (int j = 0; j < nomOfProcess; j++) {
                            sum += processes.get(j).remaining;
                        }
                    } while (sum != 0);
                }
            } 
            for (int i = 0; i < nomOfProcess; i++) {
                waiting.set(i,turnaround.get(i) - processes.get(i).excuteTime);
            }
    }
   void RoundRobin() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int quantum, sum;
       
            System.out.print("Enter the Quantum : ");
            quantum = Integer.parseInt(br.readLine());
            Queue<Integer> qs = new LinkedList<Integer>();
         
            int finish = 0, rep = 0;
            do {

                for (int i = 0; i < nomOfProcess; i++) {
                    if (rep == 0) {
                        if (processes.get(i).remaining > quantum) {
                            processes.get(i).remaining -= quantum;
                            finish += quantum;

                        } else {
                            finish += processes.get(i).remaining;
                            processes.get(i).remaining = 0;
                        }
                        response.set(i, finish);
                        if (processes.get(i).remaining > 0) {
                            qs.add(i);

                        }
                        if (nomOfProcess - i > 1) {
                            while (finish < processes.get(i+1).arrival) {
                                int ind = qs.poll();

                                if (processes.get(ind).remaining > quantum) {
                                   processes.get(ind).remaining -= quantum;
                                    finish += quantum;
                                } else {
                                    finish += processes.get(ind).remaining;
                                    processes.get(ind).remaining = 0;
                                }
                                if (processes.get(ind).remaining != 0) {
                                    qs.add(ind);
                                }

                            }
                        }
                    } else {

                        while (!qs.isEmpty()) {
                            int ind = qs.poll();

                            if (processes.get(ind).remaining > quantum) {
                                processes.get(ind).remaining -= quantum;
                                finish += quantum;
                            } else {
                                finish += processes.get(ind).remaining;
                                processes.get(ind).remaining = 0;
                            }
                            if (processes.get(ind).remaining > 0) {
                                qs.add(ind);
                            } else {
                                turnaround.set(ind, finish - processes.get(ind).arrival);
                            }
                        }
                    }

                    if (turnaround.get(i) == 0 && processes.get(i).remaining == 0) {
                        turnaround.set(i,finish - processes.get(i).arrival);
                    }

                }
                sum = 0;
                for (int i = 0; i < nomOfProcess; i++) {
                    sum = sum + processes.get(i).remaining;
                }
                rep++;
            } while (sum != 0);
            for (int i = 0; i < nomOfProcess; i++) {
                waiting.set(i ,turnaround.get(i) - processes.get(i).excuteTime);
            }

    }
    void PrintAVG(){
         double AvgWait, AvgturnAround, AvgResponse;
            int sumwait = 0, sumturn = 0, sumResponse = 0;
            DecimalFormat twoDigits = new DecimalFormat("0.00");
       
            for (int j = 0; j <nomOfProcess; j++) {

                sumwait += waiting.get(j);
            }
            for (int j = 0; j < nomOfProcess; j++) {
                sumturn += turnaround.get(j);
            }
            if (option == 1) {
                sumResponse = sumwait;
            } else if (option == 2) {
                sumResponse = sumwait - waiting.get(0);
            } else {
                sumResponse += processes.get(0).arrival - 0;
                for (int i = 1; i < nomOfProcess; i++) {
                    sumResponse += response.get(i-1)- processes.get(i).arrival;
                }
            }
            AvgWait = sumwait / (double) nomOfProcess;
            AvgturnAround = sumturn / (double) nomOfProcess;
            AvgResponse = sumResponse / (double) nomOfProcess;
            
            System.out.println("Average Waiting Time = " + twoDigits.format(AvgWait) + " msecs.");
            System.out.println("Average Turn around Time = " + twoDigits.format(AvgturnAround) + " msecs.");
            System.out.println("Average Response Time = " + twoDigits.format(AvgResponse) + " msecs.");
        }
    void getInfo(){
         System.out.println("process Name | Arrival time | Duration | waiting time | turnaround time  | priority ");
           for (int i = 0; i < nomOfProcess; i++) {

                System.out.println("      " + processes.get(i).name + "           " + processes.get(i).arrival + "             "
                        + processes.get(i).excuteTime+ "            " + waiting.get(i) + "               " + turnaround.get(i)+"               "+processes.get(i).priority);
            }
    }
    

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
