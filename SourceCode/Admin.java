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


class Admin{
	public static ArrayList<Employee> readFromFile(String file){
		ArrayList<Employee> employees = new ArrayList<Employee>();
		try {
		    Gson gson = new Gson();
		    Reader reader = Files.newBufferedReader(Paths.get(file));

		    // convert JSON array to list of users
		    employees = new Gson().fromJson(reader, new TypeToken<ArrayList<Employee>>() {}.getType());
		    reader.close();
		    return employees;

		} catch (Exception ex) {
		    ex.printStackTrace();
		}
		return employees;
	}

	public static void writeToFile(String file, ArrayList<Employee> employees){
		try (Writer writer = new FileWriter(file)){
			Gson gson = new GsonBuilder().create();
			gson.toJson(employees, writer);
		}
		catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public static void addEmployee(){
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
		ArrayList<Employee> arr = readFromFile("Database/emp.json");
		arr.add(emp);
		writeToFile("Database/emp.json", arr);
	}
	public static void main(String[] args) {
		addEmployee();
	}
}