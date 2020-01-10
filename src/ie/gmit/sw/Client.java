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
	String players = "players.txt";
	PrintWriter A;
	PrintWriter C;
	PrintWriter P;


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
		FileWriter player = new FileWriter("players.txt", true);


		
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
				//LOGIN OR REGISTER
				message = (String)in.readObject();
				System.out.println(message);
				message = console.next();
				sendMessage(message);
				
				//LOGIN
				if(message.equals("1")) {
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);
					
					//NAME
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);	
					//ID
					message = (String)in.readObject();
					System.out.println(message);
					message = console.next();
					sendMessage(message);	
				}

				//REGISTER
				else if(message.equals("2")) {	
					do
					{
						//AGENT OR CLUB
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
					}while(!message.equalsIgnoreCase("1")&&!message.equalsIgnoreCase("2"));
					
					if(message.equals("1"))
					{
						//printwriter object
						A = new PrintWriter(agent, true);
						P = new PrintWriter(player, true);
						
						//NAME
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						A.append(message+" ");
						
						//ID
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						A.append(message+" ");
					
						//CLUB ID
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						A.append(message+" ");
						System.out.println("FINISHED");
	
	
						A.append("\n------------------------------------------\n");
						
						//close
						A.flush();
						A.close();
						
						message = (String)in.readObject();
						System.out.println(message);
						
						System.out.println("\n-----------------------------------------------\n");
						
						//Agent Options
						message = (String)in.readObject();
						System.out.println(message);	
						message = console.next();
						sendMessage(message);
						
							if(message.equals("1")) {
								/* Agent OPTIONS to ADD a player
								 * Below is all the required details
								 * of each player.
								 * 
								 */
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);
								P.append(message+", ");
								
								P.append("\n-----------------------------------------------\n");

	
								//close file - adding player details to file
								P.flush();
								P.close();
							}
							
							else if(message.equals("2")) {
								System.out.println("UPDATE");
							}
							
							else if(message.equals("3")) {
								System.out.println("DELETE");
							}
							
					}
				
					else if(message.equals("2"))
					{
						//printwriter object
						C = new PrintWriter(club);
						
						//CLUB NAME
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						C.append(message+" ");
						
						//CLUB ID
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						C.append(message+" ");
						
						//CLUB EMAIL
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						C.append(message+" ");
	
						//CLUB FUNDS
						message = (String)in.readObject();
						System.out.println(message);
						message = console.next();
						sendMessage(message);
						C.append(message+" ");
						System.out.println("FINISHED");
						
						C.append("\n-----------------------------------------------\n");
						//close
						C.flush();
						C.close();
						
						message = (String)in.readObject();
						System.out.println(message);
						
						System.out.println("\n-----------------------------------------------\n");
						
						//Club Options
						message = (String)in.readObject();
						System.out.println(message);	
						message = console.next();
						sendMessage(message);
						
							if(message.equals("1")) {
								//Club OPTIONS to search a player by ID
								message = (String)in.readObject();
								System.out.println(message);
								message = console.next();
								sendMessage(message);		
							}
					}
				}
			
	
				
				//TERMINATE OPTIONS
//				System.out.println("Do you wish to continue ? (Y/N)");
//				message = console.next();
//				sendMessage(message);
				
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