package dto;

public class MyStockVO {
	
	private int mno;
	private int totalquantity;	//총 보유량
	private StockVO stock;		//실시간주식정보
	private int mdeposit;

	public int getMno() {
		return mno;
	}
	public int getMdeposit() {
		return mdeposit;
	}
	public int getTotalquantity() {
		return totalquantity;
	}
	public void setTotalquantity(int totalquantity) {
		this.totalquantity = totalquantity;
	}
		
	public StockVO getStock() {
		return stock;
	}
	public void setStock(StockVO stock) {
		this.stock = stock;
	}
	public MyStockVO(int mno, int mdeposit, int totalquantity, StockVO stock) {
		
		this.mno = mno;
		this.mdeposit = mdeposit;
		this.totalquantity = totalquantity;
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "MyStockVO [mno=" + mno + ", totalquantity=" + totalquantity + ", stock=" + stock + ", mdeposit="
				+ mdeposit + "]";
	}
	
	
	
}
