import java.io.*;
import java.net.*;

public class ActionClient1 {
    public static void main(String[] args) throws IOException {

        // Set up the socket, in and out variables

        Socket ActionClientSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int ActionSocketNumber = 4545;
        String ActionServerName = "localhost";
        String ActionClientID = "ActionClient1";
        String UserAccount = "User01";

        try {
            ActionClientSocket = new Socket(ActionServerName, ActionSocketNumber);
            out = new PrintWriter(ActionClientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(ActionClientSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: localhost ");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+ ActionSocketNumber);
            System.exit(1);
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        char UserInput;
        String fromUser = null;

        System.out.println("Initialised " + ActionClientID + " client and IO connections");
		System.out.println("This program only supports A for addmoney, S for subtractmoney, T for trasfer");

        // This is modified as it's the client that speaks first

        while (true) {
        	fromUser = stdIn.readLine();
        	UserInput = ValidateUinput(fromUser);
        	if(UserInput=='F') {
        		System.out.println("You have selected an invalid input. This program only supports A for addmoney, S for subtractmoney, T for trasfer");
        		System.out.println("Program will now terminate. Select right input next time!!");
        		System.exit(1);
        	}
        	if(UserInput=='A') {
        		System.out.println("Please enter the ammount you want to add");
        		double AmountToADD = Double.parseDouble(stdIn.readLine());
        		out.println("Add_money("+UserAccount+","+AmountToADD+")");
        		fromServer = in.readLine();
        		System.out.println(fromServer);
        	}
        	if(UserInput=='S') {
        		System.out.println("Please enter the ammount you want to subtract");
        		double AmountToSub = Double.parseDouble(stdIn.readLine());
        		out.println("Subtract_money("+UserAccount+","+AmountToSub+")");
        		fromServer = in.readLine();
        		System.out.println(fromServer);
        	}
        	if(UserInput=='T') {
        		System.out.println("Please enter the ammount you want to Transfer");
        		double AmountToTransfer = Double.parseDouble(stdIn.readLine());
        		System.out.println("Please send the User account you want to send to, User01, User02 or User03");
        		String TransferUserAccount = stdIn.readLine();
        		out.println("Transfer_money("+UserAccount+","+AmountToTransfer+","+TransferUserAccount+")");
        		fromServer = in.readLine();
        		System.out.println(fromServer);
        	}
        }
             
        
       // Tidy up - not really needed due to true condition in while loop
      //  out.close();
       // in.close();
       // stdIn.close();
       // ActionClientSocket.close();
    }
    
    public static char ValidateUinput(String inp){
        char Uchoice = 'F';
        if(inp.length()==1 && inp !=null){
            inp = inp.toUpperCase();
            Uchoice = inp.charAt(0);
            if(Uchoice=='A' || Uchoice=='S' || Uchoice=='T' ) {
                return Uchoice;
            }
        }
    return 'F';
    }
}
