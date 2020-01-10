package ie.gmit.sw;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;
//test
public class server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ServerSocket listener;
		int clientid=0;
		try 
		{
			 listener = new ServerSocket(10000,10);
			 
			 while(true)
			 {
				System.out.println("Main thread listening for incoming new connections");
				Socket newconnection = listener.accept();
				
				System.out.println("New connection received and spanning a thread");
				Connecthandler t = new Connecthandler(newconnection, clientid);
				clientid++;
				t.start();
			 }
			
		} 
		
		catch (IOException e) 
		{
			System.out.println("Socket not opened");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


class Connecthandler extends Thread
{

	Socket individualconnection;
	int socketid;
	ObjectOutputStream out;
	ObjectInputStream in;
	String cName;
	String name;
	String cID;
	String cEmail;
	String funds;
	String message;
	String option;
	String email;
	String agentID;
	String pName;
	String pAge;
	String pID;
	String valuation;
	String status;
	String position;
		
	public Connecthandler(Socket s, int i)
	{
		individualconnection = s;
		socketid = i;
	}
	
	public void addPlayer() {
		System.out.println("ADD PLAYER");
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
	public void run()
	{
		
		try 
		{
		
			out = new ObjectOutputStream(individualconnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(individualconnection.getInputStream());
			System.out.println("Connection"+ socketid+" from IP address "+individualconnection.getInetAddress());
		
		 
			//Commence
			do 
			{
				sendMessage("Please Enter 1 to LOGIN and 2 to REGISTER");
				message = (String)in.readObject();
				
				//LOGIN
				if(message.equals("1")) {
					do
					{
						sendMessage("Please Enter 1 to for AGENT and 2 for CLUB");
						message = (String)in.readObject();
					}while(!message.equals("1")&&!message.equals("2"));
					
					if(message.equals("1")) {
						sendMessage("Enter Agent's Name :");
						name = (String)in.readObject();
						
						sendMessage("Enter the AgentID : ");
						agentID = (String)in.readObject();
					}
					else if(message.equals("2")) {
						sendMessage("Enter Club's Name :");
						name = (String)in.readObject();
						
						sendMessage("Enter the Club's ID : ");
						agentID = (String)in.readObject();
					}
				
				}
				
				//REGISTER
				else if(message.equals("2")) {
					do
					{
						sendMessage("Please Enter 1 to for AGENT and 2 for CLUB");
						message = (String)in.readObject();
					}while(!message.equals("1")&&!message.equals("2"));
				
					if(message.equals("1"))
					{					
						sendMessage("Enter Agent's Name :");
						name = (String)in.readObject();
						
						sendMessage("Enter the Agent'S ID : ");
						agentID = (String)in.readObject();
						
						sendMessage("Enter Agent Email : ");
						email = (String)in.readObject();
					
						sendMessage("The Agent name is "+name + " ,The Agent email is "+email +" ,The Agent ID is "+agentID);	
						
						sendMessage("Do you wish to do the following :\n1) Add Player\n2) Update Player\n3) Delete Player");
						option = (String)in.readObject();
						
						//ADD PLAYER
						if(option.equals("1")) {						
							sendMessage("Enter Player's Name :");
							pName = (String)in.readObject();
							
							sendMessage("Enter the Player's Age :");
							pAge = (String)in.readObject();
							
							sendMessage("Enter Player's ID : ");
							pID = (String)in.readObject();
							
							sendMessage("Enter Club's ID : ");
							cID = (String)in.readObject();
							
							sendMessage("Enter Agent's ID : ");
							agentID = (String)in.readObject();
							
							sendMessage("Enter Player's Valuation : ");
							valuation = (String)in.readObject();
							
							sendMessage("Enter Player's Status : ");
							status = (String)in.readObject();
							
							sendMessage("Choose the Player's Position : \n 1)GOALKEEPER \n 2)DEFENDER \n 3)MIDFIELDER \n 4)FORWARD");
							position = (String)in.readObject();
						}
						else if(option.equals("2")) {
							sendMessage("Enter Player's ID that you wish to UPDATE : ");
							pID = (String)in.readObject();
							
						}
						else if(option.equals("3")) {
							sendMessage("Enter Player's ID that you wish to DELETE : ");
							pID = (String)in.readObject();						}
						
					}
				
					else if(message.equals("2"))
					{
						sendMessage("Enter the Clubs name : ");
						cName = (String)in.readObject();
						
						sendMessage("Enter the Clubs ID : ");
						cID = (String)in.readObject();
	
						sendMessage("Enter the Clubs Email : ");
						cEmail = (String)in.readObject();
						
						sendMessage("Enter the Clubs Funds : ");
						funds = (String)in.readObject();
						
						sendMessage("The Club's name is " +cName + " ,The Club email is "+cEmail +" ,The Club ID is "+cID +" ,The Clubs funds are : "+funds);			
	
						sendMessage("Do you wish to do the following :\n 1) Search Player by position\n 2) Search for player for sale in your club\n 3) Suspend/Resume sale of Player\n 4) Purchase a Player");
						option = (String)in.readObject();
	
						if(option.equals("1")) {
							sendMessage("Enter player's Position you wish to SEARCH : \n 1)GOALKEEPER \n 2)DEFENDER \n 3)MIDFIELDER \n 4)FORWARD");
							position = (String)in.readObject();
						}
						
						else if(option.equals("2")) {
							sendMessage("DISPLAY PLAYERS FOR SALE");
						}
						
						else if(option.equals("3")) {
							sendMessage("Enter player ID you wish to SUSPEND/RESUME :");
							pID = (String)in.readObject();
						}
						
						else if(option.equals("4")) {
							sendMessage("Enter player ID you wish to PURCHASE :");
							pID = (String)in.readObject();
						}
		
					}
				}
				
//				sendMessage("Y to repeat or N to terminate");
//				message = (String)in.readObject();
			}while(message.equalsIgnoreCase("Y"));
		
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		finally
		{
			try 
			{
				out.close();
				in.close();
				individualconnection.close();
			}
			
	
			catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}