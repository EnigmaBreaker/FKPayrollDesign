package projectPayroll;

import java.time.LocalDate;

class SalesReceipt {
	private LocalDate date;
	private double amount;
	private int employeeId;

	public SalesReceipt(double amount, int employeeId){
		this.date = LocalDate.now();
		this.amount = amount;
		this.employeeId = employeeId;
	}
}