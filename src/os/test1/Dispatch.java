/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.test1;

public class Dispatch {
    int memory_size;
    int cache_capacity;
    String cashe_policy ="LRU";
    Memory memory;
    Cache cache;
    CPUScheduler cpu_schudele ;
    
    int id=000;
    
    
    
    public Dispatch(){
    id =0;    
    cache_capacity=4;
    memory_size=12;
    cashe_policy ="LRU";
    memory= new Memory (memory_size);
    cache=new Cache(cache_capacity,cashe_policy);
    cpu_schudele = new CPUScheduler();
    
    }
    
    public void add_process(){   // simple procee no need for memory 
        Process process = new Process(id++);
    }
    
    public void add_memory_process(){   // proces that needs data from 
        Process process = new Process(id);
        
    }
    
    private void read_from_memory (Process new_process){
        if(!cache.tryHit(new_process)){
            if(!memory.tryGet(new_process)){
                memory.write(id, new_process)
            }
        }
        
    }
    
    
    
}
