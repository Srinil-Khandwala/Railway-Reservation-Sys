import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Train 
{
	String Train_name;
	String Starting_loc;
	String Ending_loc;
	String PNRno;	
	String splitBy = ",";
	String line = "";
	BufferedReader br;
	
	public Train(String Starting_loc, String Ending_loc) 
	{		
		this.Starting_loc = Starting_loc;
		this.Ending_loc = Ending_loc;		
	}
	
	public Train(String Train_name)
	{
		this.Train_name=Train_name;
	}
	
	public Train(){}	
	
	void Search() throws IOException 
	{	
		int i=0;		
		br = new BufferedReader(new FileReader("D:\\Srinil\\SY KJSCE\\OOPM\\mini project/Train_details_With_distance.csv"));
		int flag=0;		
		while ((line = br.readLine()) != null) 
		{			
			String Train_Data[] = line.split(splitBy);			
			if (Train_Data[3].contains(Starting_loc) && Train_Data[7].contains( Ending_loc)) 
			{
	            flag=1;i++;
				System.out.println("\n"+i+". PNR= "+Train_Data[0] + "\n   Name=" + Train_Data[1] + "\n   From= (" +Train_Data[2] +") " +Train_Data[3] + "\n   To= ("+ Train_Data[6]+") " +Train_Data[7]+"\n   Arrival = "+Train_Data[4]+"\n   Departure = "+Train_Data[5]+"\n   Distance = "+Train_Data[8]+" Kms");
			}			
		}
		br.close();
		if(flag==0)System.out.println("No Trains Available!!");
	}
	
	 String getPNR(String Train_name,String Starting_code,String Ending_code) throws IOException
	{		 
		 br = new BufferedReader(new FileReader("D:\\Srinil\\SY KJSCE\\OOPM\\mini project/Train_details_With_distance.csv"));
		while ((line = br.readLine()) != null) 
		{			
			String Train_Data[] = line.split(splitBy);			
			if ((Train_Data[1].contains(Train_name))&&(Train_Data[2].contains(Starting_code))&&(Train_Data[6].contains(Ending_code))) 
			{
				PNRno= Train_Data[0];				
			}			
		}
		br.close();
		return PNRno;		
	}
	
	String getTrain_name(String PNRno) throws IOException
	{
		 br = new BufferedReader(new FileReader("D:\\Srinil\\SY KJSCE\\OOPM\\mini project/Train_details_With_distance.csv"));
		while ((line = br.readLine()) != null) 
		{			
			String Train_Data[] = line.split(splitBy);			
			if (PNRno.contains(Train_Data[0])) 
			{
				Train_name= Train_Data[1];				
			}			
		}
		br.close();
		return Train_name;		
	}
	
	int getDistance(String Train_name,String Starting_code,String Ending_code) throws IOException
	{	
		int dist=0;
		 br = new BufferedReader(new FileReader("D:\\Srinil\\SY KJSCE\\OOPM\\mini project/Train_details_With_distance.csv"));
		while ((line = br.readLine()) != null) 
		{			
			String Train_Data[] = line.split(splitBy);			
			if ((Train_Data[1].contains(Train_name))&&(Train_Data[2].contains(Starting_code))&&(Train_Data[6].contains(Ending_code))) 
			{
				dist=Integer.parseInt(Train_Data[8]);				
			}			
		}
		br.close();
		return dist;		
	}
	
//	void edit_TicketsCSV(String Train_name,String Starting_code,String Ending_code,int n,int Month_int) throws IOException
//	{
//		 // CSVWriter writer = new CSVWriter(new FileWriter("D:\\Srinil\\SY KJSCE\\OOPM\\mini project/Train_details_With_distance.csv"));
//		br = new BufferedReader(new FileReader("D:\\Srinil\\SY KJSCE\\OOPM\\mini project/Train_details_With_distance.csv"));
//		while ((line = br.readLine()) != null) 
//		{			
//			String Train_Data[] = line.split(splitBy);			
//			if ((Train_Data[1].contains(Train_name))&&(Train_Data[2].contains(Starting_code))&&(Train_Data[6].contains(Ending_code))) 
//			{
//				Train_Data[Month_int +9]=Integer.toString(  Integer.parseInt(Train_Data[Month_int+9])  -  n);
//				System.out.println("In Edit Tickets"+Train_Data[Month_int +9]);
//			}			
//		}
//		br.close();
//	}
}

