package admin.model;

public class Visitor {

	private String term;
	private int sum;
	
	public Visitor() {
		
	}
	
	public Visitor(String term,int sum) {
		this.term = term;
		this.sum = sum;
	}
	
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	
	@Override
	public String toString() {
		return "Visitor [term=" + term + ", sum=" + sum + "]";
	}
}
