package projectPayroll;

import java.time.LocalTime;
import java.time.LocalDate;

class TimeCard{
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate date;
	private int employeeID;

	public TimeCard(int employeeID){
		this.startTime = LocalTime.now();
		this.date = LocalDate.now();
		this.endTime = null;
		this.employeeID = employeeID;
	}
	public void setEndTime(){
		if(endTime == null){
			this.endTime = LocalTime.now();
		}
	}
}