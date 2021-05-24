import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Random;
import java.util.Scanner;

public class Passenger
{   
   String Pass_name;
   int age;
   String address;
   String Starting_loc;
   String Ending_loc;
   String Starting_code;
   String Ending_code;
   String Train_name;
   String Arrival_Time;
   String PNRno;   
   int distance;
   int amount;
   
   Passenger(String Pass_name,int age,String address,String Starting_loc,String Ending_loc,String PNRno,String Train_name,String Arrival_Time,int distance)//Constructor for ReserveTicket after getting PNRno in Passenger class
   {
	   this.Pass_name=Pass_name;
	   this.age=age;
	   this.address=address;
	   this.Starting_loc=Starting_loc;
	   this.Ending_loc=Ending_loc;
	   this.PNRno=PNRno;
	   this.Train_name=Train_name;
	   this.Arrival_Time=Arrival_Time;
	   this.distance=distance;	   
   }
   
   public Passenger() 
   {   }

   void SearchTrains() throws IOException
   {
	   //System.out.println("Please Enter Everything in CAPITAL Letters !");
		Scanner sc = new Scanner(System.in);
		System.out.print("\nFrom= ");
		Starting_loc = sc.nextLine().toUpperCase();
		// Starting_loc.toUpperCase();
		System.out.print("To= ");
		Ending_loc = sc.nextLine().toUpperCase();		
		Train t = new Train(Starting_loc, Ending_loc);
		t.Search();
   }
  
   
   void ReserveTicket() throws IOException, ParseException
   {
//	    int FirstClassFlag=0;int SecondSleeperFlag=0;int SecondSittingFlag=0;
		Scanner sc=new Scanner(System.in);
	    System.out.print("\nNo of tickets : ");
	    int n=sc.nextInt();sc.nextLine();
	    System.out.println("-------------------------------------------");
	    System.out.print("\nFrom (Location Code) :");
		Starting_code = sc.nextLine().toUpperCase();
		System.out.print("To (Location code) :");
		Ending_code = sc.nextLine().toUpperCase();
		System.out.print("Enter the Train name :");
		Train_name = sc.nextLine().toUpperCase();
		System.out.print("Enter Arrival Time (HH:MM:SS) :");
		Arrival_Time=sc.next();
		Train tr = new Train(Train_name);
		PNRno=tr.getPNR(Train_name,Starting_code,Ending_code);
		Train_name=tr.getTrain_name(PNRno);
		int dist=tr.getDistance(Train_name,Starting_code, Ending_code);
		Passenger doPay=new Passenger();
		
		if(PNRno==null || dist<0) 
		{
			System.out.println("Invalid Train name");
		}
		else
		{
			int Ticket_no;
			Random r=new Random();
			Ticket_no=r.nextInt(100000);
			
			System.out.println("\nTrain PNRno : "+PNRno+"\nDistance : "+dist+" kms"+sc.nextLine());
			Dates d=new Dates();
			System.out.print("\nPlease Enter Month And Of Travelling : ");
			String month=sc.nextLine();
//			int Month_int=Integer.parseInt(Dates.getMonthForString(month));			
			amount=(n*doPay.TicketAmount(dist));
			for(int i=0;i<n;i++) 
		    {	    	
				System.out.print("\nPassenger name '"+(i+1)+"' : ");
				Pass_name = sc.nextLine();
				do {
						System.out.print("Age : ");
					try {
							age = sc.nextInt();
							sc.nextLine();
							if (age < 0 || age > 120) 
							{
								throw new AgeException("Invalid age (Enter age between 0 and 120)!!");
							} 
							else 
							{
//								System.out.println("Valid age!!");
								break;
							}
						}
						catch (AgeException a) 
						{
							System.out.println(a+"\n");
						}
				} while (true);				
				if(i==0) 
				{				
				    System.out.print("Address : ");
				    address = sc.nextLine();			  
				}			
				
				FileWriter fw= new FileWriter("Records.txt",true);
	            BufferedWriter bw= new BufferedWriter(fw);
	            PrintWriter pw= new PrintWriter(bw);
	            pw.println(Pass_name+","+age+","+Starting_code+","+Ending_code+","+Ticket_no+","+Train_name+","+PNRno+","+Arrival_Time+","+amount+","+month);
	            pw.flush();
	            pw.close();			    								
		    }
			
			d.DaysToNextTrain(month);//Prints Your Train is after 'x' Days
			doPay.doPayment(amount,Pass_name);
//			tr.edit_TicketsCSV(Train_name, Starting_code, Ending_code,n,Month_int);
		}	    
   }  
   
   int TicketAmount(int dist)
   {
	   int amount=0;
	   Scanner sc=new Scanner(System.in);	   
	   
		   System.out.print("\nPlease select the class that you would like to tarvel by : \n1. First Class\n2. Second Sleeper\n3. Second Sitting\n=> ");
		   switch(sc.nextInt())
		   {
			   case 1: 
			   {				  
				   amount= dist*4;
				   break;
			   }
			   case 2:
			   {				  
				   amount=dist*2;
				   break;
			   }
			   case 3: 
			   {				  
				   amount=dist*1;
				   break;
			   }
			   default:
				   System.out.println("Enter valid input!");
				   break;
		   }	   
	   return amount;
   }
   
   void doPayment(int amount,String name)
   {
	   Scanner sc=new Scanner(System.in);
	   System.out.println("");
	   System.out.print("The Amount you must pay is "+amount+" Rs!"+"\n\nSelect the mode of payment : \n1. CASH\n2. NET BANKING\n=> ");
		switch(sc.nextInt())
		{
			case 1:
			{
				System.out.println("\nPlease Pay the Required Amount!!\n");
				ThreadWait t=new ThreadWait();
				t.start();
				try {t.join();}
				catch(Exception e) {System.out.println(e);}
				System.out.println("Thank you and Have a Safe Journey!!\n");
				break;
			}
			case 2: 
			{	
				System.out.print("\nPlease Select a Bank\n1. Bank of India\n2. ICICI Bank\n3. HDFC Bank\n4. SBI\n5. Axis Bank\n6. Indian Bank\n=> ");		
				int bank=sc.nextInt();			
			    String alpha = null;	        
		        Console cnsl = System.console();     	            
		        alpha = cnsl.readLine("Bank ID: ");
		        System.out.println("Welcome  " + name+"!");        
		        char[] pwd = cnsl.readPassword("Enter Password: ");				
				 ThreadPassword t=new ThreadPassword(); 
				 t.start();
				 try 
				 {
					 t.join(); 
			     }
				 catch(Exception e) {System.out.println(e);}
		        System.out.println("\nYour Transaction is complete\n\nThank you and Have a Safe Journey!!\\n");
		        break;
			}
			default :System.out.println("Enter valid input!");
			break;
		}		
   }   
}
