package us.noop.data;

import java.util.HashMap;

public class Stock {
	private HashMap<String, Double> numberValues = new HashMap<String, Double>();
	
	private String ticker, name, sector;
	public Stock(String ticker, String name, String sector, double price, double divYield, double ppe, double eps,
			double bookvalue, double fwl, double fwh, double mc, double ebitda, double ppsales, double ppb,
			String securl) {
		this.ticker = ticker;
		this.name = name;
		this.sector = sector;
		numberValues.put("price", price);
		numberValues.put("dividendyield", divYield);
		numberValues.put("priceperearnings", ppe);
		numberValues.put("earningspershare", eps);
		numberValues.put("bookvalue", bookvalue);
		numberValues.put("52weeklow", fwl);
		numberValues.put("52weekhigh", fwh);
		numberValues.put("marketcap", mc);
		numberValues.put("ebitda", ebitda);
		numberValues.put("pricepersales", ppsales);
		numberValues.put("priceperbook", ppb);
	}
	public double getValue(String category){
		return numberValues.get(category);
	}
	public String getName(){
		return name;
	}
	public String getSector(){
		return sector;
	}
	public String getTicker(){
		return ticker;
	}
}
