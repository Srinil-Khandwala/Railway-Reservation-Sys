
public class ThreadWait extends Thread
{
	public void run()
	{
		try 
		{
			Thread.sleep(2000);
		}
		catch(Exception e) {System.out.println(e);}		
	}
}
