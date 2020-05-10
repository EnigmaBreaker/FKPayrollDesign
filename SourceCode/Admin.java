package projectPayroll;
import java.util.Scanner;
import java.io.Reader;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.reflect.TypeToken;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.Writer;
import java.io.FileWriter;

interface AdminInterface {
	public int addEmployee();
	public void deleteEmployee(int employeeID);
	public int addUnion();
	public void deleteUnion(int unionID);
	public void loadEmployees();
	public void dumpEmployees();
	public void loadTimeCard();
	public void dumpTimeCard();
	public void loadSalesReceipt();
	public void dumpSalesReceipt();
	public void loadUnion();
	public void dumpUnion();
}

class Admin implements AdminInterface{
	public ArrayList<Employee> employees = new ArrayList<>();
	public ArrayList<TimeCard> timecards = new ArrayList<>();
	public ArrayList<SalesReceipt> salesreceipts = new ArrayList<>();
	public ArrayList<Union> unions = new ArrayList<>();

	
	public Admin(){
		this.loadEmployees();
		this.loadTimeCard();
		this.loadSalesReceipt();
		Employee.setLastId(this.employees.get(this.employees.size() - 1).getID());
	}
	public static <E> void readFromFile(String file, ArrayList<E> arr){
		try {
		    Gson gson = new Gson();
		    Reader reader = Files.newBufferedReader(Paths.get(file));

		    ArrayList<E> temp = new Gson().fromJson(reader, new TypeToken<ArrayList<E>>() {}.getType());
		    reader.close();
		    for (int i=0; i<temp.size(); i++){
		    	arr.add(temp.get(i));
		    }
		} 
		catch (Exception ex) {
		    ex.printStackTrace();
		}
	}

	public static <E> void writeToFile(String file, ArrayList<E> arr){
		try (Writer writer = new FileWriter(file)){
			Gson gson = new GsonBuilder().serializeNulls().create();
			gson.toJson(arr, writer);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public int addUnion(){
		Scanner sc = new Scanner(System.in);
		System.out.print("[@] Name: ");
		String name = sc.nextLine();
		System.out.print("[@] Min Amount: ");
		Double amount = sc.nextDouble();
		Union un = new Union(name, amount);
		this.unions.add(un);
		return un.getID();
	}

	public int addEmployee(){
		Scanner sc = new Scanner(System.in);
		System.out.print("[@] Name: ");
		String name = sc.nextLine();
		System.out.print("[@] Address: ");
		String address = sc.nextLine();
		System.out.print("[@] Salary: ");
		double salary = sc.nextDouble();
		System.out.print("[@] HourlyRate: ");
		double hourlyRate = sc.nextDouble();
		System.out.print("[@] CommissionRate: ");
		double commissionRate = sc.nextDouble();
		String nexxx = sc.nextLine();
		System.out.print("[@] PaymentType[Mail/Pickup/Bank]: ");

		String paymentT = sc.nextLine();
		PaymentType paymentType;
		if(paymentT.equals("Mail")){
			paymentType = PaymentType.Mail;
		}
		else if(paymentT.equals("Pickup")){
			paymentType = PaymentType.Pickup;
		}
		else if(paymentT.equals("Bank")){
			paymentType = PaymentType.BankTransfer;
		}
		else{
			paymentType = PaymentType.Mail;
		}

		Employee emp = new Employee(name, salary, hourlyRate, commissionRate, address, paymentType);
		employees.add(emp);
		return emp.getID();
	}

	public void deleteEmployee(int employeeId){
		ArrayList<Employee> employees = new ArrayList<>();
		ArrayList<Employee> newones = new ArrayList<>();
		readFromFile("Database/emp.json", employees);
		for (int i=0; i<employees.size(); i++){
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(employees.get(i));
			Employee temp = gson.fromJson(json, Employee.class);
			if(temp.getID() != employeeId){
				newones.add(temp);
			}
			else{
				break;
			}
		}
		this.employees = newones;
	}

	public void deleteUnion(int unionID){
		ArrayList<Union> unions = new ArrayList<>();
		ArrayList<Union> newones = new ArrayList<>();
		readFromFile("Database/union.json", unions);
		for (int i=0; i<unions.size(); i++){
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(unions.get(i));
			Union temp = gson.fromJson(json, Union.class);
			if(temp.getID() != unionID){
				newones.add(temp);
			}
			else{
				break;
			}
		}
		this.unions = newones;
	}



	public void loadEmployees(){
		ArrayList<Employee> arr = new ArrayList<>();
		readFromFile("Database/emp.json", arr);
		for (int i=0; i<arr.size(); i++){
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(arr.get(i));
			Employee temp = gson.fromJson(json, Employee.class);
			employees.add(temp);
		} 
	}
	public void loadTimeCard(){
		ArrayList<TimeCard> arr = new ArrayList<>();
		readFromFile("Database/timecards.json", arr);
		for (int i=0; i<arr.size(); i++){
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(arr.get(i));
			TimeCard temp = gson.fromJson(json, TimeCard.class);
			timecards.add(temp);
		} 
	}

	public void loadSalesReceipt(){
		ArrayList<SalesReceipt> arr = new ArrayList<>();
		readFromFile("Database/salesreceipts.json", arr);
		for (int i=0; i<arr.size(); i++){
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(arr.get(i));
			SalesReceipt temp = gson.fromJson(json, SalesReceipt.class);
			salesreceipts.add(temp);
		} 
	}	

	public void loadUnion(){
		ArrayList<Union> arr = new ArrayList<>();
		readFromFile("Database/union.json", arr);
		for (int i=0; i<arr.size(); i++){
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(arr.get(i));
			Union temp = gson.fromJson(json, Union.class);
			unions.add(temp);
		} 
	}

	public void dumpEmployees(){
		writeToFile("Database/emp.json", employees);
	}
	public void dumpTimeCard(){
		writeToFile("Database/timecards.json", timecards);
	}
	public void dumpSalesReceipt(){
		writeToFile("Database/salesreceipts.json", salesreceipts);
	}
	public void dumpUnion(){
		writeToFile("Database/union.json", unions);
	}

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.addEmployee();
		admin.employees.get(0).startTime();
		SalesReceipt newone = admin.employees.get(0).submitSalesReceipt(1000);
		admin.salesreceipts.add(newone);
		admin.dumpSalesReceipt();
		admin.dumpEmployees();
		// admin.deleteEmployee(1);
		// admin.dumpEmployees();
	}
}