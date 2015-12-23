package os.test1;


import os.test1.FileSystem;



class Dispatch {
    int memory_size;
    int cache_capacity;
    String cashe_policy ="LRU";
    Memory memory;
    Cache cache;
    CPUScheduler cpu_schudele ;
    folder Folder;
    FileSystem fileSystem;
    
    int id=000;
    int nomOfProcess=0;
    public Dispatch(){
    id =0;    
    cache_capacity=4;
    memory_size=5;
    cashe_policy ="LRU";
    memory= new Memory (memory_size);
    cache=new Cache(cache_capacity,cashe_policy);
    cpu_schudele = new CPUScheduler();
    
    }
    
    public void add_process(){   // simple procee no need for memory //create
        Process process = new Process(id++);
         nomOfProcess++;
       cpu_schudele.processes.add(process);
    }
    
    public void add_memory_process(){   // proces that needs data from 
        Process process = new Process(id++);
        read_from_memory(process); 
        nomOfProcess++;
       cpu_schudele.processes.add(process);
       
    }
    
    private void read_from_memory (Process new_process){
        if(!cache.tryHit(new_process)){
        Process p= memory.SearchingForProcess(new_process);
           cache.readRequest(p);
           
        }
    }
        
         public static void main(String args[]) {
             
         }
        
    }
    
    
    

