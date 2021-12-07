# ActionClientCode-Bank
 
The Bank Application
The primary goal of this is to develop a demonstrator of a concurrent client-server system that shows how transactions issued by bank application clients correctly update the data held by the banking application server.

The  Bank Application (WBA) that you will design and implement is very simple as it is only used by three people at the moment (it’s a small bank!)  The amount of money that each user has is represented in the server by a variable (i.e., there are three variables in the server!)  The users are very busy and (1) frequently add and subtract money from their account and (2) transfer money to other user’s accounts (i.e., all these operations run at the server).  Each user has their own client and they use their client to instruct the server to run the operations. You can call the clients A, B and C. The operations are as follows:
