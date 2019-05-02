// ONLY MODIFY CODE BELOW THE NEXT OCCURENCE OF THE FOLLOWING LINE !
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

#include "types.h"
#include "defs.h"
#include <stdio.h>
#include "proc.h"

#define NCPU 1

struct cpu cpus[NCPU];
int ncpu;

void printstate(enum procstate pstate){ // DO NOT MODIFY
  switch(pstate) {
    case UNUSED   : printf("UNUSED");   break;
    case EMBRYO   : printf("EMBRYO");   break;
    case SLEEPING : printf("SLEEPING"); break;
    case RUNNABLE : printf("RUNNABLE"); break;
    case RUNNING  : printf("RUNNING");  break;
    case ZOMBIE   : printf("ZOMBIE");   break;
    default       : printf("????????");
  }
}

// Per-CPU process scheduler.
// Each CPU calls scheduler() after setting itself up.
// Scheduler never returns.  It loops, doing:
//  - choose a process to run
//  - swtch to start running that process
//  - eventually that process transfers control
//      via swtch back to the scheduler.

// local to scheduler in xv6, but here we need them to persist outside,
// because while xv6 runs scheduler as a "coroutine" via swtch,
// here swtch is just a regular procedure call.
struct proc *p;
struct cpu *c = cpus;

// +++++++ ONLY MODIFY BELOW THIS LINE ++++++++++++++++++++++++++++++
// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

struct processUses {
  int key[NPROC];
  int value[NPROC];
};
  
//This is a piece of code I just inserted for convenience. It is adapted from an copied piece of code, to fit my variables.
void swap(int* a, int* b) 
{ 
    int t = *a; 
    *a = *b; 
    *b = t; 
} 
  
/* This function takes last element as pivot, places 
   the pivot element at its correct position in sorted 
    array, and places all smaller (smaller than pivot) 
   to left of pivot and all greater elements to right 
   of pivot */
int partition (int arr[], int arr2[], int low, int high) 
{ 
    int pivot = arr[high];    
    int i = (low - 1);
  
    for (int j = low; j <= high- 1; j++) 
    { 
        if (arr[j] <= pivot) 
        { 
            i++;    
            swap(&arr[i], &arr[j]); 
            swap(&arr2[i], &arr2[j]); 
        } 
    } 
    swap(&arr[i + 1], &arr[high]); 
    swap(&arr2[i + 1], &arr2[high]);
    return (i + 1); 
} 

void quickSort(int arr[], int arr2[], int low, int high) 
{ 
    if (low < high) 
    { 
        int pi = partition(arr, arr2, low, high); 
        
        quickSort(arr, arr2, low, pi - 1); 
        quickSort(arr, arr2, pi + 1, high); 
    } 
} 

void
scheduler(void)
{ int runnableFound; // DO NOT MODIFY/DELETE
  
  int procCount = 0;
  struct processUses uses;  //keep track of number of process uses.
  //for (int i=0; i<NPROC; i++) {
  for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
    uses.key[p->pid] = p->pid;
    uses.value[p->pid] = 0;
  }
  //uses.key = {0 ... NPROC};
  //uses.value = {};
  c->proc = 0;
  
  runnableFound = 1 ; // force one pass over ptable

  while(runnableFound){ // DO NOT MODIFY
    // Enable interrupts on this processor.
    // sti();
    runnableFound = 0; // DO NOT MODIFY
    
    quickSort(uses.value, uses.key, 0, procCount);
    // Loop over process table looking for process to run.
    // acquire(&ptable.lock);
    for(p = ptable.proc; p < &ptable.proc[NPROC]; p++){
    //for (int i = 0; i < NPROC; i++) {
      //p = ptable.proc[uses.key[i]];
      
      if(p->state != RUNNABLE)
        continue;
      
      runnableFound = 1; // DO NOT MODIFY/DELETE/BYPASS
      
      // Switch to chosen process.  It is the process's job
      // to release ptable.lock and then reacquire it
      // before jumping back to us.
      c->proc = p;
      uses.value[p->pid]++;
      //switchuvm(p);
      p->state = RUNNING;
      procCount++;
      


      swtch(p);
      // p->state should not be running on return here.
      //switchkvm();
      // Process is done running for now.
      // It should have changed its p->state before coming back.
      c->proc = 0;
    }
    // release(&ptable.lock);

  }
  printf("No RUNNABLE process!\n");
}



