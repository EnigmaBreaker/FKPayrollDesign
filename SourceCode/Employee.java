package projectPayroll

interface EmployeeInterface {

}

class hourlyEmployee implements EmployeeInterface {
	private String name;
	private String identity;
	private int salary;
	private int dueAmount;
	private int hourlyRate;
	private int commissionRate;
}