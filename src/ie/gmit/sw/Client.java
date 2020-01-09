package ie.gmit.sw;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
//test
public class Client 
{
	
	private Socket connection;
	private String message;
	private  Scanner console;
	private  String ipaddress;
	private  int portaddress;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private int choice;
	String club = "clubs.txt";
	String agent ="agents.txt";
	PrintWriter A;
	PrintWriter C;


	public Client()
	{
		console = new Scanner(System.in);
		
		System.out.println("Enter the IP Address of the server");
		ipaddress = console.nextLine();
		
		System.out.println("Enter the TCP Port");
		portaddress  = console.nextInt();
		
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
	
	
	public static void main(String[] args) throws IOException 
	{
			Client temp = new Client();
			temp.clientapp();
	}

	public void clientapp() throws IOException
	{
		
		FileWriter agent = new FileWriter("agents.txt", true);
		FileWriter club = new FileWriter("clubs.txt", true);

		
		try 
		{
			connection = new Socket(ipaddress,portaddress);
		
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			System.out.println("Client Side ready to communicate");
		
		
		    /// Client App.
			
			do
			{
				do
				{
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
				}while(!message.equalsIgnoreCase("1")&&!message.equalsIgnoreCase("2"));
				
				if(message.equals("1"))
				{
					//printwriter object
					A = new PrintWriter(agent, true);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					A.append(message+" ");
					
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					A.append(message+" ");
				
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					A.append(message+" ");

					A.append("\n--------------\n");

					//close
					A.flush();
					A.close();
				}
			
				else if(message.equals("2"))
				{
					//printwriter object
					C = new PrintWriter(club);
					
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					C.append(message+" ");
					
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					C.append(message+" ");

					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					C.append(message+" ");

					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					C.append(message+" ");
					
					C.append("\n-------------------\n");
					//close
					C.flush();
					C.close();
					
				}
			
				message = (String)in.readObject();
				System.out.println(message);
				message = console.next();
				sendMessage(message);
				
			}while(message.equalsIgnoreCase("Y"));
			
			out.close();
			in.close();
			connection.close();
		} 
		
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
