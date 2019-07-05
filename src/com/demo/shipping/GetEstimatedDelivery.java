package com.demo.shipping;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class GetEstimatedDelivery {

	//Determine Delivery Date
	public Date DeliveryDate(Options options) {
		Set<Integer> weekdays = new HashSet<>();
		weekdays.add(4);	weekdays.add(5);	weekdays.add(6);	weekdays.add(2);	weekdays.add(3);
		int count=0;
		String shipDate = options.shipDate;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date shipped = null;
		try {
			shipped = sdf.parse(shipDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	System.out.println("SHIPPED DATE INPUT"+shipped);
		Calendar c = Calendar.getInstance();
		c.setTime(shipped);
		//	System.out.println(c.get(Calendar.DAY_OF_WEEK));
		int day = c.get(Calendar.DAY_OF_WEEK);
		int month = c.get(Calendar.MONTH);
		int date = c.get(Calendar.DATE);
		Date estimatedDD = null;
		while(count < options.minimumDeliveryDays) {
			if(weekdays.contains(day) && !isHoliday(month,date,options.holidays))
				count++;
			String estimatedDeliveryDate = c.get(Calendar.YEAR) +"-"+(++month)+"-" + (++date);
			Calendar c1 = Calendar.getInstance();

			try {
				estimatedDD = sdf.parse(estimatedDeliveryDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c1.setTime(estimatedDD);
			day=c1.get(Calendar.DAY_OF_WEEK);
			month=c1.get(Calendar.MONTH);
			date=c1.get(Calendar.DATE);
		}

		return estimatedDD;
	}

	public boolean isHoliday(int month, int date, Holidays[] holidays) {
		String mon = String.valueOf(month);
		String d = String.valueOf(date);

		for(int i=0;i<holidays.length;i++) {
			if(holidays[i].day.equalsIgnoreCase(d) && holidays[i].month.equalsIgnoreCase(mon))
				return true;
		}		
		return false;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Holidays[] holidayList = new Holidays[] {
				new Holidays("01","01"),
				new Holidays("05","28"),
				new Holidays("07","12"),
				new Holidays("09","03"),
				new Holidays("11","23"),
				new Holidays("12","25"),
				new Holidays("12","26")				
		};
		Options option1 = new Options(12,holidayList,"2018-11-20");
		GetEstimatedDelivery ged = new GetEstimatedDelivery();
		System.out.println(ged.DeliveryDate(option1));
	}
}
