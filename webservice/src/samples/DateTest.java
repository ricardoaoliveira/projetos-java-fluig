package samples;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateTest {

	public static void main(String args[]) throws DatatypeConfigurationException {
		
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DAY_OF_YEAR, 10);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		now.set(Calendar.HOUR_OF_DAY, 0);
		System.out.println(now.getTime());
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(now.getTime());
		XMLGregorianCalendar xmlGc = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
		System.out.println(xmlGc);		
	}
	
}
