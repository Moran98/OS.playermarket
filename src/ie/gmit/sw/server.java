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
	String cID;
	String cEmail;
	String funds;
	String message;
	String email;
	String name;
	String agentID;
	int result;
	
	
	public Connecthandler(Socket s, int i)
	{
		individualconnection = s;
		socketid = i;
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
			
				do
				{
					sendMessage("Please Enter 1 to for AGENT and 2 for CLUB");
					message = (String)in.readObject();
				}while(!message.equals("1")&&!message.equals("2"));
			
				if(message.equals("1"))
				{
					result = 0;
					
					sendMessage("Enter Agent's Name :");
					message = (String)in.readObject();
//					result += Integer.parseInt(message);
					
					sendMessage("Enter the AgentID : ");
					agentID = (String)in.readObject();
					
					sendMessage("Enter Agent Email : ");
					email = (String)in.readObject();
				
					sendMessage("The Agent name is "+message + " ,The Agent email is "+email +" ,The Agent ID is "+agentID);			
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

	
				}
			
			
				sendMessage("Y to repeat or N to terminate");
			
				message = (String)in.readObject();
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
