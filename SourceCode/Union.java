package projectPayroll;
import java.util.ArrayList;

interface UnionInterface {
	public int getID();
	public String getName();
	public double getFee();
	public void updateFee(double fee);
	public void updateName(String name);
	public void addMember(int memberId, double donation);
	public void removeMember(int memberId);
}

class Union{
	private String name;
	private final int identity;
	private double membershipfees;
	private ArrayList<Integer> members;
	private ArrayList<Double> donations;
	private static int lastID = 0;
	private Union() {
		lastID ++;
		this.identity = lastID;
		this.members = new ArrayList<>();
		this.donations = new ArrayList<>();
	}
	public Union(String name, double membershipfees){
		this();
		this.name = name;
		this.membershipfees = membershipfees;
	}
	public int getID(){
		return this.identity;
	}
	public String getName() {
		return this.name;
	}
	public double getFee() {
		return this.membershipfees;
	}
	public void updateFee(double fee){
		this.membershipfees = fee;
	}
	public void updateName(String name){
		this.name = name;
	}
	public void addMember(int memberId, double donation){
		this.members.add(memberId);
		this.donations.add(donation);
	}
	public void removeMember(int memberId){
		int ind = this.members.indexOf(memberId);
		if(memberId != -1){
			this.members.remove(ind);
			this.donations.remove(ind);
		}
	}
}