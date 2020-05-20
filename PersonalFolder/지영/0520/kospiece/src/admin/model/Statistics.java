package admin.model;

//통계자료를 담아 뷰단으로 넘겨주는 객체를 생성하기위해 VO클래스생성
public class Statistics {
	
	private int todayMember;
	private int todayPost;
	private int totalMember;
	private int totalPost;
	
	public int getTodayMember() {
		return todayMember;
	}
	public void setTodayMember(int todayMember) {
		this.todayMember = todayMember;
	}
	public int getTodayPost() {
		return todayPost;
	}
	public void setTodayPost(int todayPost) {
		this.todayPost = todayPost;
	}
	public int getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	
	@Override
	public String toString() {
		return "Statistics [todayMember=" + todayMember
				+ ", todayPost=" + todayPost + ", totalMember=" + totalMember + ", totalPost=" + totalPost + "]";
	}
}
