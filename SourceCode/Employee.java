package projectPayroll;

import java.time.LocalDate;
import com.google.gson.*;

interface EmployeeInterface {
	public String getName();
	public int getID();
	public double getSalary();
	public void setSalary(double salary);
	public double getHourlyRate();
	public void setHourlyRate(double rate);
	public double getCommissionRate();
	public void setCommissionRate(double rate);
	public double getDueAmount();
	public void changeDueAmount(double amount);
	public String getAddress();
	public void setAddress(String address);
	public PaymentType getPaymentType();
	public void setPaymentType(PaymentType ptype);
}

class Employee implements EmployeeInterface {
	private String name;
	private final int identity;
	private String address;
	private double salary;
	private double dueAmount;
	private double hourlyRate;
	private double commissionRate;
	private final LocalDate joiningDate;
	private LocalDate paymentStartDate;
	private static int lastId = 0;
	private PaymentType paymentType;
	private Employee(){
		lastId++;
		this.identity = lastId;
		this.dueAmount = 0;
		this.joiningDate = LocalDate.now();
		this.paymentStartDate = LocalDate.now();
	}
	public Employee(String name, double salary, double hourlyRate, double commissionRate, String address, PaymentType paymentType){
		this();
		this.name = name;
		this.salary = salary;
		this.hourlyRate = hourlyRate;
		this.commissionRate = commissionRate;
		this.address = address;
		this.paymentType = paymentType;
	}

	// public Employee createAgain(String json){
	// 	Gson gson = new Gson();
	// 	Employee obj = gson.fromJson(json, Employee.class);
	// 	return obj;
	// }

	public String jsonDump(){
		Gson gson = new GsonBuilder().serializeNulls().create();
		return gson.toJson(this);
	}

	public int getID(){
		return this.identity;
	}
	public String getName(){
		return this.name;
	}
	public double getSalary(){
		return this.salary;
	}
	public void setSalary(double salary){
		this.salary = salary;
	}
	public double getHourlyRate(){
		return this.hourlyRate;
	}
	public void setHourlyRate(double rate){
		this.hourlyRate = rate;
	}
	public double getCommissionRate(){
		return commissionRate;
	}
	public void setCommissionRate(double rate){
		this.commissionRate = rate;
	}
	public double getDueAmount(){
		return this.dueAmount;
	}
	public void changeDueAmount(double amount){
		this.dueAmount += amount;
	}
	public String getAddress(){
		return this.address;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public PaymentType getPaymentType() {
		return this.paymentType;
	}
	public void setPaymentType(PaymentType ptype){
		this.paymentType = ptype;
	}
	public static void main(String[] args){
		Employee emp = new Employee("asdf", 100, 100, 10, "ASdsdf", PaymentType.Pickup);
		String json = emp.jsonDump();
		// Employee newone = emp.createAgain(json);
		// String js = emp.jsonDump();
		// System.out.println(js);
	}
}