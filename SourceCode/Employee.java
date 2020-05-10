package projectPayroll

interface EmployeeInterface {
	public int getID();
	public String getName();
	public double getSalary();
	public void setSalary(double salary);
	public double getHourlyRate();
	public void setHourlyRate(double rate);
	public double getCommissionRate(double rate);
	public double getDueAmount();
	public void changeDueAmount(double amount);
}

class Employee implements EmployeeInterface {
	private String name;
	private final String identity;
	private double salary;
	private double dueAmount;
	private double hourlyRate;
	private double commissionRate;
	private static int lastId = 0;

	private Employee(){
		lastId++;
		this.identity = lastId;
		this.dueAmount = 0;
	}

	public Employee(String name, double salary, double hourlyRate, double commissionRate){
		this();
		this.name = name;
		this.salary = salary;
		this.hourlyRate = hourlyRate;
		this.commissionRate = commissionRate;
	}

}