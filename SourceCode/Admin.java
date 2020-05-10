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
}

class Admin implements AdminInterface{
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
			Gson gson = new GsonBuilder().create();
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
		ArrayList<Employee> arr = new ArrayList<>();
		readFromFile("Database/emp.json", arr);
		arr.add(emp);
		writeToFile("Database/emp.json", arr);
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
		writeToFile("Database/emp.json", newones);
	}

	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.addEmployee();
	}
}