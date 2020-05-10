package projectPayroll;

import java.time.LocalDate;

class SalesReceipt {
	LocalDate date;
	double amount;
	int employeeId;

	public SalesReceipt(double amount, int employeeId){
		this.date = LocalDate.now();
		this.amount = amount;
		this.employeeId = employeeId;
	}
}