import java.util.*;

public class s5910110148h2 {
  public static void main(String[] args) {
    int numSeq = Integer.parseInt(args[0]);
    System.out.printf ("Numbers of Sequence is %d \n",numSeq);
  
    SeqRun sr = new SeqRun(numSeq);
  // Three threads
  Thread t1 = new Thread(sr);
  Thread t2 = new Thread(sr);
  Thread t3 = new Thread(sr);
  
  try {
   // First thread
   System.out.println("\nThread 1 START\n");
   t1.start();
   t1.join();
   System.out.println("\nThread 1 END\n");
   // Second thread
   System.out.println("\nThread 2 START\n");
   t2.start();
   t2.join();
   System.out.println("\nThread 2 END\n");
   // Third thread
   System.out.println("\nThread 3 START\n");
   t3.start();
   t3.join();
   System.out.println("\nThread 3 END\n");
  } catch (InterruptedException e) {
   e.printStackTrace();
  }
 }
}

class SeqRun implements Runnable{
    public int input;
    public int s;
    public SeqRun(int data){
        this.input=data;

    }
    public static int Sequence(int n){
        if (n == 0)      
          return 1;     
        else if(n>3){
          if((n%3)==0)
          return(Sequence(n-1));
          else
          return(Sequence(n-2)+Sequence(n-2)-1);
        }     
        else 
          return n;     
    }

 public void run() {
  for(s=0; s<this.input; s++){
    System.out.println("S_" + s + " = " + Sequence(s));
  }
 } 
}