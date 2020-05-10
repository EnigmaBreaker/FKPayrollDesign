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
	public void addEmployee();
	public void deleteEmployee(int employeeID);
	public void loadEmployees();
	public void dumpEmployees();
	public void loadTimeCard();
	public void dumpTimeCard();
}

class Admin implements AdminInterface{
	public ArrayList<Employee> employees = new ArrayList<>();
	public ArrayList<TimeCard> timecards = new ArrayList<>();
	
	public Admin(){
		this.loadEmployees();
		this.loadTimeCard();
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

	public void addEmployee(){
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
	public void dumpEmployees(){
		writeToFile("Database/emp.json", employees);
	}
	public void dumpTimeCard(){
		writeToFile("Database/timecards.json", timecards);
	}

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.addEmployee();
		admin.employees.get(0).startTime();
		TimeCard newone = admin.employees.get(0).submitTimeCard();
		admin.timecards.add(newone);
		admin.dumpTimeCard();
		admin.dumpEmployees();
		// admin.deleteEmployee(1);
		// admin.dumpEmployees();
	}
}