import java.net.*;
import java.util.HashMap;
import java.io.*;

public class SharedActionState{
	
	private SharedActionState mySharedObj;
	private String myThreadName;
	private HashMap<String, Double> bank = new HashMap<String, Double>();
	private boolean accessing=false; // true a thread has a lock, false otherwise
	private int threadsWaiting=0; // number of waiting writers

// Constructor	
	
	SharedActionState(HashMap<String, Double> Bank) {
		bank= Bank;
	}

//Attempt to aquire a lock
	
	  public synchronized void acquireLock() throws InterruptedException{
	        Thread me = Thread.currentThread(); // get a ref to the current thread
	        System.out.println(me.getName()+" is attempting to acquire a lock!");	
	        ++threadsWaiting;
		    while (accessing) {  // while someone else is accessing or threadsWaiting > 0
		      System.out.println(me.getName()+" waiting to get a lock as someone else is accessing...");
		      //wait for the lock to be released - see releaseLock() below
		      wait();
		    }
		    // nobody has got a lock so get one
		    --threadsWaiting;
		    accessing = true;
		    System.out.println(me.getName()+" got a lock!"); 
		  }

		  // Releases a lock to when a thread is finished
		  
		  public synchronized void releaseLock() {
			  //release the lock and tell everyone
		      accessing = false;
		      notifyAll();
		      Thread me = Thread.currentThread(); // get a ref to the current thread
		      System.out.println(me.getName()+" released a lock!");
		  }
	
	
    /* The processInput method */

	public synchronized String processInput(String myThreadName, String theInput) {
    		System.out.println(myThreadName + " received "+ theInput);
    		String theOutput = null;
    		System.out.println(theInput);
    		// Check what the client wants to do.
    		String Task;
    		String UserAccount;
    		double Amount;
    		String TargetAccount;
    		if(theInput.startsWith("Add_money")) {
    			Task="Add";
    			UserAccount= theInput.substring(theInput.indexOf("(") + 1, theInput.indexOf(","));
    			Amount = Double.parseDouble(theInput.substring(theInput.indexOf(",") + 1, theInput.indexOf(")")));
    			double nowBalance = Add_money(UserAccount, Amount);
    			theOutput="Added successfully User:"+ UserAccount+ " has balance:"+ nowBalance ;
    			
    		}
    		else if(theInput.startsWith("Subtract_money")) {
    			Task="Sub";
    			UserAccount= theInput.substring(theInput.indexOf("(") + 1, theInput.indexOf(","));
    			Amount = Double.parseDouble(theInput.substring(theInput.indexOf(",") + 1, theInput.indexOf(")")));
    			double nowBalance = Subtract_money(UserAccount, Amount);
    			theOutput= "Balance Deducted successfully"+ UserAccount+ " has balance:"+ nowBalance;
    		}
    		else if(theInput.startsWith("Transfer_money")) {
    			Task = "Transfer";
    			UserAccount= theInput.substring(theInput.indexOf("(") + 1, theInput.indexOf(","));
    			String[] parts = theInput.split(",");
    			Amount = Double.parseDouble(parts[1]);
    			System.out.println(Amount);
    			String TargetAccountRaw = parts[2];
    			System.out.println(TargetAccountRaw);
    			TargetAccount = TargetAccountRaw.substring(0, TargetAccountRaw.indexOf(")"));
    			double nowBalance = Transfer_money(UserAccount, TargetAccount, Amount);
    			theOutput = "Transfer Sucessfull"+ TargetAccount+ " has balance:"+ nowBalance;
    		}
    		else {
    			//Invalid Input here
    		}
    		
     		//Return the output message to the ActionServer
    		System.out.println(theOutput);
    		return theOutput;
    	}
	
	public double Add_money(String account, Double value) {
		Double currentBalance = bank.get(account);
		currentBalance+= value;
		bank.put(account, currentBalance);
		return currentBalance;
		
	}
	public double Subtract_money(String account, Double value) {
		Double currentBalance = bank.get(account);
		currentBalance-= value;
		bank.put(account, currentBalance);
		return currentBalance;
	}
	public double Transfer_money(String account1, String account2, Double value) {
		//deduct from account 1
		Double currentBalance1 = bank.get(account1);
		currentBalance1-=value;
		bank.put(account1, currentBalance1);
		//add to account 2
		System.out.println(bank.get(account2));
		System.out.println(account2);
		Double currentBalance2 = bank.get(account2);
		currentBalance2+=value;
		bank.put(account2, currentBalance2);
		return currentBalance2;
		
	}
	
	
}

