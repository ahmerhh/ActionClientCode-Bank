# ActionClientCode-Bank
 
The Bank Application
The primary goal of this is to develop a demonstrator of a concurrent client-server system that shows how transactions issued by bank application clients correctly update the data held by the banking application server.

The  Bank Application (WBA) that you will design and implement is very simple as it is only used by three people at the moment (it’s a small bank!)  The amount of money that each user has is represented in the server by a variable (i.e., there are three variables in the server!)  The users are very busy and (1) frequently add and subtract money from their account and (2) transfer money to other user’s accounts (i.e., all these operations run at the server).  Each user has their own client and they use their client to instruct the server to run the operations. You can call the clients A, B and C. The operations are as follows:

Add_money(account, value) – this adds value (virtual) money to the specified account (i.e. the user’s account – you can assume that they only add money to their own account).

Subtract_money(account, value) – this subtracts value (virtual) money from the specified account (i.e. the user’s account – you can assume that they only subtract money from their own account).

Transfer_money(account1, account2, value) – this transfers value (virtual) money from account1 to account2.  

The users start with 1000 units  in their accounts.  Accounts can go below zero. 

You are required to create a multi-threaded client-server system that uses locking and has:

•	One client used by each user to run the operations at the server as above (three in total)
•	One server that holds the account variables and executes the operations as instructed by the clients

To run;

Run `ActionServer` pin the console.
Run `ActionClient1` pin the console.
Run `ActionClient2` pin the console.
Run `ActionClient3` pin the console.

Run the respective commands in the ActionClient to run the program.
