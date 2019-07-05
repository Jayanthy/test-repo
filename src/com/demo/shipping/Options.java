package com.demo.shipping;

public class Options {
	int minimumDeliveryDays;
	Holidays[] holidays;
	String shipDate;
	
	public Options(int minimumDeliveryDays, Holidays[] holidays, String shipDate ) {
		this.minimumDeliveryDays = minimumDeliveryDays;
		this.holidays = holidays;
		this.shipDate = shipDate;
	}
}
