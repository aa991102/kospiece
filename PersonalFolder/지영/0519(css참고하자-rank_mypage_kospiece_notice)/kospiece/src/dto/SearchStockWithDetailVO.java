package dto;

import java.util.ArrayList;

public class SearchStockWithDetailVO {

	private StockVO stockVO;
	private ArrayList<StockVO> list;
	
	public SearchStockWithDetailVO(StockVO stockVO, ArrayList<StockVO> list) {
		super();
		this.stockVO = stockVO;
		this.list = list;
	}
	public StockVO getStockVO() {
		return stockVO;
	}
	public ArrayList<StockVO> getList() {
		return list;
	}
	
	
}
