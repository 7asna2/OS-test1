
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.Queue;

public class CPUScheduler implements Runnable{

    int n;
    int option;
    int waiting[];
    int turnaround[];
    int response[];
    process[] processes;

    public CPUScheduler(int n) {
        this.n = n;
        processes = new process[n];
        waiting = new int[n];
        turnaround = new int[n];
        response= new int[n];
         
    }
    void setoption(int option){
        this.option=option;
    }
    int setoption(){
        return option;
    }
    void FIFO(){
         int finish = 0;
            for (int i = 0; i < n; i++) {
                finish += processes[i].duration;
                waiting[i] = finish - processes[i].arrival - processes[i].duration;
            }
            for (int i = 0; i < n; i++) {
                turnaround[i] = waiting[i] + processes[i].duration;
            }
    }
    void SJF(){
         int finish = 0, sum, time;
            for (int i = 0; i < n; i++) {
                waiting[i] = 0;
                turnaround[i] = 0;
            }

            for (int i = 0; i < n; i++) {
                time = processes[i].arrival;
                if (n - i > 1) {

                    if (i == 0) {
                        while (time < processes[i + 1].arrival) {
                            processes[i].remaining--;
                            finish++;
                            time++;
                        }
                    } else {
                        while (time < processes[i + 1].arrival) {
                            if (processes[i].remaining < processes[i - 1].remaining) {
                                processes[i].remaining--;
                            } else {
                               processes[i - 1].remaining--;
                            }
                            finish++;
                            time++;
                        }
                        if (processes[i - 1].remaining == 0 && turnaround[i - 1] == 0) {
                            turnaround[i - 1] = finish - processes[i - 1].arrival;
                        }

                    }

                    if (processes[i].remaining == 0 && turnaround[i] == 0) {
                        turnaround[i] = finish - processes[i].arrival;
                    }

                } else {
                    int minprocess, minindex = 0;
                    do {
                        minprocess = 1000000;
                        for (int j = 0; j < n; j++) {
                            if (processes[j].remaining < minprocess &&processes[j].remaining != 0) {
                                minprocess = processes[j].remaining;
                                minindex = j;
                            }
                        }
                        finish += processes[minindex].remaining;
                        processes[minindex].remaining = 0;
                        turnaround[minindex] = finish - processes[minindex].arrival;
                        sum = 0;
                        for (int j = 0; j < n; j++) {
                            sum += processes[j].remaining;
                        }
                    } while (sum != 0);
                }
            } 
            for (int i = 0; i < n; i++) {
                waiting[i] = turnaround[i] - processes[i].duration;
            }
    }
    void RoundRobin() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int quantum, sum;
            response[0] = 0;
            System.out.print("Enter the Quantum : ");
            quantum = Integer.parseInt(br.readLine());
            Queue<Integer> qs = new LinkedList<Integer>();
            for (int i = 0; i < n; i++) {
                waiting[i] = 0;
                turnaround[i] = 0;
            }
            int finish = 0, rep = 0;
            do {

                for (int i = 0; i < n; i++) {
                    if (rep == 0) {
                        if (processes[i].remaining > quantum) {
                            processes[i].remaining -= quantum;
                            finish += quantum;

                        } else {
                            finish += processes[i].remaining;
                            processes[i].remaining = 0;
                        }
                        response[i] = finish;
                        if (processes[i].remaining > 0) {
                            qs.add(i);

                        }
                        if (n - i > 1) {
                            while (finish < processes[i + 1].arrival) {
                                int ind = qs.poll();

                                if (processes[ind].remaining > quantum) {
                                   processes[ind].remaining -= quantum;
                                    finish += quantum;
                                } else {
                                    finish += processes[ind].remaining;
                                    processes[ind].remaining = 0;
                                }
                                if (processes[ind].remaining != 0) {
                                    qs.add(ind);
                                }

                            }
                        }
                    } else {

                        while (!qs.isEmpty()) {
                            int ind = qs.poll();

                            if (processes[ind].remaining > quantum) {
                                processes[ind].remaining -= quantum;
                                finish += quantum;
                            } else {
                                finish += processes[ind].remaining;
                                processes[ind].remaining = 0;
                            }
                            if (processes[ind].remaining > 0) {
                                qs.add(ind);
                            } else {
                                turnaround[ind] = finish - processes[ind].arrival;
                            }
                        }
                    }

                    if (turnaround[i] == 0 && processes[i].remaining == 0) {
                        turnaround[i] = finish - processes[i].arrival;
                    }

                }
                sum = 0;
                for (int i = 0; i < n; i++) {
                    sum = sum + processes[i].remaining;
                }
                rep++;
            } while (sum != 0);
            for (int i = 0; i < n; i++) {
                waiting[i] = turnaround[i] - processes[i].duration;
            }

    }
    void PrintAVG(){
         double AvgWait, AvgturnAround, AvgResponse;
            int sumwait = 0, sumturn = 0, sumResponse = 0;
            DecimalFormat twoDigits = new DecimalFormat("0.00");
            for (int j = 0; j < n; j++) {

                sumwait += waiting[j];
            }
            for (int j = 0; j < n; j++) {
                sumturn += turnaround[j];
            }
            if (option == 1) {
                sumResponse = sumwait;
            } else if (option == 2) {
                sumResponse = sumwait - waiting[0];
            } else {
                sumResponse += processes[0].arrival - 0;
                for (int i = 1; i < n; i++) {
                    sumResponse += response[i - 1] - processes[i].arrival;
                }
            }
            AvgWait = sumwait / (double) n;
            AvgturnAround = sumturn / (double) n;
            AvgResponse = sumResponse / (double) n;
            System.out.println("process Name | Arrival time | Duration | waiting time | turnaround time ");
            for (int i = 0; i < n; i++) {

                System.out.println("      " + processes[i].process_Name + "           " + processes[i].arrival + "             "
                        + processes[i].duration + "            " + waiting[i] + "               " + turnaround[i]);
            }
            System.out.println("Average Waiting Time = " + twoDigits.format(AvgWait) + " msecs.");
            System.out.println("Average Turn around Time = " + twoDigits.format(AvgturnAround) + " msecs.");
            System.out.println("Average Response Time = " + twoDigits.format(AvgResponse) + " msecs.");
        }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }
    
