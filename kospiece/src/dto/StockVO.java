package dto;

public class StockVO {
	
	private String no; //회사코드번호(sno)
	private String name; //회사명(sname)
	private String field; //업종(sfield)
	private String detail; //세부업종(sdetail)
	private int price; //현재가(sprice)
	private String dayrate;//전일비(sdayrate)
	private float changerate;//등락률(schangerate)
	private String volume;//거래량(svolume)
	private String dealprice;//거래대금[백만](sdealprice)
	private int total;//시가총액[억](stotal)
	private String high52;//52주고가(shigh52)
	
	//해당 회원이 주식을 갖고있는지 여부
	private int interest;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDayrate() {
		return dayrate;
	}
	public void setDayrate(String dayrate) {
		this.dayrate = dayrate;
	}
	public float getChangerate() {
		return changerate;
	}
	public void setChangerate(float changerate) {
		this.changerate = changerate;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public String getDealprice() {
		return dealprice;
	}
	public void setDealprice(String dealprice) {
		this.dealprice = dealprice;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getHigh52() {
		return high52;
	}
	public void setHigh52(String high52) {
		this.high52 = high52;
	}
	
	public int getInterest() {
		return interest;
	}
	public void setInterest(int interest) {
		this.interest = interest;
	}
	
	public StockVO() {}
	
	
	public StockVO(String no, String name, String field, String detail, int price, String dayrate, float changerate,
			String volume, String dealprice, int total, String high52) {
		this.no = no;
		this.name = name;
		this.field = field;
		this.detail = detail;
		this.price = price;
		this.dayrate = dayrate;
		this.changerate = changerate;
		this.volume = volume;
		this.dealprice = dealprice;
		this.total = total;
		this.high52 = high52;
	}
	public StockVO (String name, float changerate, int total)
	{
		this.name = name;
		this.changerate = changerate;
		this.total = total;
	}
	@Override
	public String toString() {
		return "StockVO [no=" + no + ", name=" + name + ", field=" + field + ", detail=" + detail + ", price=" + price
				+ ", dayrate=" + dayrate + ", changerate=" + changerate + ", volume=" + volume + ", dealprice="
				+ dealprice + ", total=" + total + ", high52=" + high52 + ", interest=" + interest + "]";
	}
	
}
