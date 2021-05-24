import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Clerk
{	
	void ViewTickets() throws IOException
	{
		int i=0;
	    int flag=0;
		BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
        String line="";
        String splitBy = ",";
        while ((line = br.readLine()) != null)
        {        	
            String records[] = line.split(splitBy);
            if(Integer.parseInt(records[4])!=-1) 
            {            	
               System.out.println("\n"+(i+1)+". TICKET NO : "+records[4]+"\n   NAME : "+records[0]+"\n   AGE : "+records[1]+"\n   TRAIN : "+records[5]+"\n   From : "+records[2]+"\n   To : "+records[3]+"\n   PNR No. : "+records[6]+"\n   Arrival Time : "+records[7]+"\n   Month : "+records[9]);
               i++;flag=1;
            }
        }
        if(flag==0) {System.out.println("\nNO TICKETS ARE BOOKED AS OF NOW!!");}
	}
	
	void ViewSpecificTicket() throws IOException
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter you Ticket No:");
		int Ticket_no=sc.nextInt();
		int i=0;
	    int flag=0;
		BufferedReader br = new BufferedReader(new FileReader("Records.txt"));
        String line="";
        String splitBy = ",";
        while ((line = br.readLine()) != null)
        {        	
            String records[] = line.split(splitBy);
            if(Integer.parseInt(records[4])!=-1 && (Integer.parseInt(records[4])==Ticket_no)) 
            {            	
               System.out.println("\n"+(i+1)+". TICKET NO : "+records[4]+"\n   NAME : "+records[0]+"\n   AGE : "+records[1]+"\n   TRAIN : "+records[5]+"\n   From : "+records[2]+"\n   To : "+records[3]+"\n   PNR No. : "+records[6]+"\n   Arrival Time : "+records[7]+"\n   Month : "+records[9]);
               i++;flag=1;
            }
        }
        if(flag==0) {System.out.println("\nNO TICKETS ARE BOOKED AS OF NOW!!");}
	}
	
	void SearchTrains() throws IOException
	{
		Passenger p= new Passenger();		
		p.SearchTrains();
	}	

	void reserveTicket() throws Exception
	{
		Passenger p=new Passenger();
		p.ReserveTicket();
	}
	
	void cancelTicket() throws NumberFormatException, IOException
	{     
           int flag=0;  		
		   System.out.println("Please Enter your Ticket no : ");
		   Scanner sc = new Scanner(System.in);
		   int ticket_no=sc.nextInt();
		   sc.nextLine();		   
		   System.out.println("\nPLEASE CHECK YOUR TICKET DETAIlS=>");
		   System.out.println("-------------------------------------------");
		   BufferedReader br1 = new BufferedReader(new FileReader("Records.txt"));
	        String line="";
	        String splitBy = ",";
	        while ((line = br1.readLine()) != null)
	        {
	            String records[] = line.split(splitBy);
	            if(Integer.parseInt(records[4])==ticket_no) 
	            {
	               System.out.println("\nTICKET NO : "+records[4]+"\nNAME : "+records[0]+"\nAGE : "+records[1]+"\nTRAIN : "+records[5]+"\nFrom :"+records[2]+"\nTo :"+records[3]+"\nPNR No.:"+records[6]+"\nArrival Time:"+records[7]);
	               flag=1;
	            }
	        }
	        if(flag==1)
	        {
		        System.out.println("-------------------------------------------");
		        System.out.print("\nDo you want to cancel your ticket ?(YES/NO) : ");
		        if((sc.next().toUpperCase()).contains("YES"))
		        {		        	
		        	Train t=new Train();	
		        	BufferedReader br2 = new BufferedReader(new FileReader("Records.txt"));	 	       
			        while ((line = br2.readLine()) != null)
			        {
			            String records[] = line.split(splitBy);
			            if(Integer.parseInt(records[4])==ticket_no) 
			            {
			               System.out.println("\nCancellation Charges(1/4*Total Amount) :"+Integer.parseInt(records[8])/4+" Rs");
			               System.out.println("Amount Refunded to your bank Account  : "+3*Integer.parseInt(records[8])/4+" Rs");	              
			               break;
			            }
			        }
				   modifyFile("Records.txt",Integer.toString(ticket_no),"-1");			   		   
		        }  
	        }
	        else {System.out.println("Enter Valid Ticket Number!!");}
		  
	}
	
	static void modifyFile(String filePath, String oldString, String newString)
    {
        File fileToBeModified = new File(filePath);
        String oldContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            //Reading all the lines of input text file into oldContent
            String line = reader.readLine();
            while (line != null)
            {
                oldContent = oldContent + line + System.lineSeparator();
                line = reader.readLine();
            }
            //Replacing oldString with newString in the oldContent
            String newContent = oldContent.replaceAll(oldString, newString);
            //Rewriting the input text file with newContent
            writer = new FileWriter(fileToBeModified);
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {                
                reader.close();
                writer.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

	public static void main(String[] args) throws Exception  
	{
	    
		String c="YES";
		Clerk ck=new Clerk();		
		while(c.equals("YES"))
		{
        	int choice=0;
			System.out.println("-------------------------------------------");
			System.out.println("1. SEARCH TRAIN\n2. RESERVE TICKET\n3. CANCEL TICKET\n4. VIEW BOOKED TICKETS\n5. VIEW SPECIFIC TICKET");
			System.out.println("-------------------------------------------");
			Scanner scan=new Scanner(System.in);			
			System.out.print("Enter your choice => ");
			choice=scan.nextInt();
			System.out.println("-------------------------------------------");
			switch (choice) 
			{
				case 1: 
				{				    
					ck.SearchTrains();
					break;
				}
				case 2:
				{					
					ck.reserveTicket();
					break;
				}
				case 3:
				{
					ck.cancelTicket();
					break;
				}				
				case 4:
				{
				    ck.ViewTickets();
				    break;
				}
				case 5:
				{
					ck.ViewSpecificTicket();
					break;
				}
				default:
				{					
					System.out.println("Enter a value between 1 & 4");
					break;
				}
			}
			System.out.println("\n-------------------------------------------");
			System.out.print("Do you want to Continue ?(YES/NO) : ");
			c=scan.next().toUpperCase();			
		}
		System.out.println("THANK YOU !!");
	}
	
}
