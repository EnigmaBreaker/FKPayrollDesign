package projectPayroll;
import java.util.Scanner;

class Admin{
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
		System.out.println(emp.jsonDump());

		
	}
	public static void main(String[] args) {
		addEmployee();
	}
}