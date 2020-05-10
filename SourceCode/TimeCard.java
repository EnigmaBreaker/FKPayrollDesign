package projectPayroll;

import java.time.LocalTime;
import java.time.LocalDate;

class TimeCard{
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate date;

	public TimeCard(){
		this.startTime = LocalTime.now();
		this.date = LocalDate.now();
		this.endTime = null;
	}
	public void setEndTime(){
		if(endTime != null){
			this.endTime = LocalTime.now();
		}
	}
}