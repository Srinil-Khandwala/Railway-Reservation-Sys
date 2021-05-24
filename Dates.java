
import java.util.*;
import java.text.*;
public class Dates
{
	void DaysToNextTrain(String Month) throws ParseException
	{
        Scanner sc =new Scanner(System.in);
        Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");		
		Date date = sdf.parse("15"+getMonthForString(Month)+"2021");
		cal1.setTime(date);
		
		date = sdf.parse(cal2.get(cal2.DATE)+""+cal2.get(cal2.MONTH)+""+cal2.get(cal2.YEAR));		
		cal2.setTime(date);
		System.out.println("Your train is after '"+daysBetween(cal1.getTime(),cal2.getTime())+"' Days");
	  }
	
	  static String getMonthForString(String month) 
	  {
			DateFormatSymbols dfs = new DateFormatSymbols();
			String[] months = dfs.getMonths();
			int num=0;
			for(num=0;num<12;num++)
			{
				  if(month.equalsIgnoreCase(months[num]))
				  break;
			}		
			return String.format("%02d", num);        
	  }	  
	
	  static int daysBetween(Date d1, Date d2)
	  {
	       return (int)( (d1.getTime() - d2.getTime()) / (1000 * 60 * 60 * 24));
	  }
  
}
