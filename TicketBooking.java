package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TicketBooking 
{
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ticketbooking", "root", "admin");
			
			boolean repet=true;
			do
			{
				
				System.out.println("1: BookTicket\n2: UpdateTicket\n3: CancleTicket\n4: GenerateTicket\n5: Exit");
				System.out.println("-------------------------");
				System.out.println("Enter Choice 1-5");
				int ch=sc.nextInt();
				
				switch(ch)
				{
				case 1:
				{
					PreparedStatement st=con.prepareStatement("insert into ticketbooking values(?,?,?,?,?,?)");
					
					System.out.println("enter id");
					int id=sc.nextInt();
					
					System.out.println("enter name");
					String name=sc.next();
					
					System.out.println("enter s_station");
					String S_station=sc.next();
					
					System.out.println("enter d_station");
					String D_station=sc.next();
					
					System.out.println("enter no_tickets");
					int No_tickets=sc.nextInt();
					
					System.out.println("enter amount");
					double cost=sc.nextDouble();
					cost=No_tickets*cost;
					
					
					st.setInt(1,id);
					st.setNString(2, name);
					st.setString(3,S_station);
					st.setString(4, D_station);
					st.setInt(5, No_tickets);
					st.setDouble(6, cost);
					
					st.execute();
					System.out.println("=========================");
					System.out.println("Ticket Book Successfully");
					System.out.println("=========================");
				
				}
				break;
				case 2:
				{
					PreparedStatement st=con.prepareStatement("update ticketbooking set name=?, S_station=?, D_station=?, No_tickets=?, cost=? where tid=?");
					
					System.out.println("enter id");
					int id=sc.nextInt();
					st.setInt(6,id);
					
					
					System.out.println("enter name");
					String name=sc.next();
					st.setNString(1, name);
					
					System.out.println("enter s_station");
					String S_station=sc.next();
					st.setString(2,S_station);
					
					System.out.println("enter d_station");
					String D_station=sc.next();
					st.setString(3, D_station);
					
					System.out.println("enter no_tickets");
					int No_tickets=sc.nextInt();
					st.setInt(4, No_tickets);
					
					System.out.println("enter amount");
					double cost=sc.nextDouble();
					st.setDouble(5, cost);
					
					st.execute();
					System.out.println("=========================");
					System.out.println(" Ticket updated successfully");
					System.out.println("=========================");
				}
				break;
				case 3:
				{
					PreparedStatement st=con.prepareStatement("delete from ticketbooking where tid=?");
					System.out.println("enter id");
					int id=sc.nextInt();
					st.setInt(1, id);
					st.execute();
					System.out.println("=========================");
					System.out.println(" Ticket cancled successfully");
					System.out.println("=========================");
				}
				break;
				case 4:
				{
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select * from ticketbooking");
					while(rs.next())
					{
						System.out.println("Ticket ID :"+rs.getInt(1));
						System.out.println("Passanger Name :"+rs.getString(2));
						System.out.println("Starting Station :"+rs.getString(3));
						System.out.println("Designatin Station :"+rs.getString(4));
						System.out.println("No_Tickets :"+rs.getInt(5));
						System.out.println("Total cost:"+rs.getDouble(6));
						System.out.println("=========================");
						System.out.println("Happy Journey");
						System.out.println("==========================");
					}
				}
				break;
				case 5:
				{
					System.out.println("=========================");
					System.out.println("Visit Again ThankYou");
					System.out.println("=========================");
				}
				break;
				default :
				{	System.out.println("=========================");
					System.out.println("Entered Invalid choice");
					System.out.println("=========================");
					
				}
				}
			}
			while(repet);
			
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
 			e.printStackTrace();
		}
		
	}
}
