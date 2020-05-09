package dto;

public class MyStockVO {
	
	private int totalquantity;	//총 보유량
	private Stock stock;		//실시간주식정보

	public int getTotalquantity() {
		return totalquantity;
	}
	public void setTotalquantity(int totalquantity) {
		this.totalquantity = totalquantity;
	}
		
	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}
	public MyStockVO(int totalquantity, Stock stock) {

		this.totalquantity = totalquantity;
		this.stock = stock;
	}
	
	
	
}
