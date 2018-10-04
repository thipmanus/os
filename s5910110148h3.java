import java.util.concurrent.Semaphore;

public class s5910110148h3 {

  private final Semaphore mutexLock=new Semaphore(5); //true: first come, first serve
  private String Buffer = "Buffer is empty";
  private int writer = 1;

  public static void main(String[] args) {
    s5910110148h3 test=new s5910110148h3();
    test.mystart();
  }

  public void mystart() {
      for(int i=0;i<5;i++){
        Reader reader=new Reader();
        reader.start();
      }

      Writer writer=new Writer();
      writer.start();
  }

  public class Reader extends Thread {
    @Override
    public void run() {
	
      System.out.println("Reader "+this.getId()+" starts **");
	    try{
        mutexLock.acquire();
      } catch (InterruptedException e) {}

   	  while(writer == 1) {
		    try{ 
          Thread.sleep(1);
		    }catch(InterruptedException e){}

   	    System.out.println("Reader "+this.getId()+" read ["+Buffer+"] from Buffer. ");

        // must put it to sleep to delay its effect a bit
        try{ 
          Thread.sleep(249);
		    }catch(InterruptedException e){}
	    }
	    System.out.println("Reader "+this.getId()+" end.\n");
    }
  }

  public class Writer extends Thread {
    @Override
    public void run() {
	    try{
        Thread.sleep(50);
      }catch(InterruptedException e) {}
      System.out.println("Writer "+this.getId()+" starts **");
	    try{
        Thread.sleep(200);
      }catch(InterruptedException e) {}
      
      for(int i=0;i<10;i++){
		    System.out.print("Writer "+this.getId()+" write <");
		    Buffer = "This is data number" +i;
		    System.out.println(Buffer+"> to Buffer. ");
		    mutexLock.release();
        // must put it to sleep to delay its effect a bit
        try{ 
          Thread.sleep(250);
		    }catch(InterruptedException e){}
	    }
	    System.out.println("Writer "+this.getId()+" end.\n");
	    writer = 0;
    }
  }
}