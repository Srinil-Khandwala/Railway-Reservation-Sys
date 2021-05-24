
public class ThreadPassword extends Thread
{	
	public void run()
	{
		try 
		{
			for(int i=0;i<6;i++)
			{
				System.out.print("*");
				Thread.sleep(250);
			}
		}
		catch(Exception e) {System.out.println(e);}		
	}
}
