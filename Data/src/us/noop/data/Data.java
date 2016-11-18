package us.noop.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
	ArrayList<Stock> stocks = new ArrayList<Stock>();
	public static void main(String... args) throws FileNotFoundException{
		Data d = new Data();
		System.out.println("Name\t\t\t\tPPE");
		System.out.println("Information Technology:\t\t" + truncateDouble(d.sectorAverage("Information Technology", "priceperearnings")));
		System.out.println("Industrials:\t\t\t" + truncateDouble(d.sectorAverage("Industrials", "priceperearnings")));
		System.out.println("Financials:\t\t\t" + truncateDouble(d.sectorAverage("Financials", "priceperearnings")));
		System.out.println("Consumer Discretionary:\t\t" + truncateDouble(d.sectorAverage("Consumer Discretionary", "priceperearnings")));
		System.out.println("Health Care:\t\t\t" + truncateDouble(d.sectorAverage("Health Care", "priceperearnings")));
		System.out.println("Utilities:\t\t\t" + truncateDouble(d.sectorAverage("Utilities", "priceperearnings")));
		System.out.println("Consumer Staples:\t\t" + truncateDouble(d.sectorAverage("Consumer Staples", "priceperearnings")));
		System.out.println("Materials:\t\t\t" + truncateDouble(d.sectorAverage("Materials", "priceperearnings")));
		System.out.println("\nName\t\t\t\tMC/Book Value");
		System.out.println("Information Technology:\t\t" + truncateDouble(d.sectorAverage("Information Technology", "marketcap") / d.sectorAverage("Information Technology", "ebitda")));
		System.out.println("Industrials:\t\t\t" + truncateDouble(d.sectorAverage("Industrials", "marketcap") / d.sectorAverage("Industrials", "ebitda")));
		System.out.println("Financials:\t\t\t" + truncateDouble(d.sectorAverage("Financials", "marketcap") / d.sectorAverage("Financials", "ebitda")));
		System.out.println("Consumer Discretionary:\t\t" + truncateDouble(d.sectorAverage("Consumer Discretionary", "marketcap") / d.sectorAverage("Consumer Discretionary", "ebitda")));
		System.out.println("Health Care:\t\t\t" + truncateDouble(d.sectorAverage("Health Care", "marketcap") / d.sectorAverage("Health Care", "ebitda")));
		System.out.println("Utilities:\t\t\t" + truncateDouble(d.sectorAverage("Utilities", "marketcap") / d.sectorAverage("Utilities", "ebitda")));
		System.out.println("Consumer Staples:\t\t" + truncateDouble(d.sectorAverage("Consumer Staples", "marketcap") / d.sectorAverage("Consumer Staples", "ebitda")));
		System.out.println("Materials:\t\t\t" + truncateDouble(d.sectorAverage("Materials", "marketcap") / d.sectorAverage("Materials", "ebitda")));
	}
	
	public Data() throws FileNotFoundException{
		Scanner sc = new Scanner(new File("financials.csv"));
		sc.nextLine();
		while(sc.hasNextLine()){
			String s = sc.nextLine();
			s = s.replaceAll(",(?=[^\"]*\"[^\"]*(?:\"[^\"]*\"[^\"]*)*$)", "");
			String[] dat = s.split(",");
			Stock st = new Stock(dat[0], dat[1], dat[2], 
					parseDouble(dat[3]),
					parseDouble(dat[4]),
					parseDouble(dat[5]),
					parseDouble(dat[6]),
					parseDouble(dat[7]),
					parseDouble(dat[8]),
					parseDouble(dat[9]),
					parseDouble(dat[10]),
					parseDouble(dat[11]),
					parseDouble(dat[12]),
					parseDouble(dat[13]),
					dat[14]);
			stocks.add(st);
		}
		
	}
	
	public double sectorAverage(String sector, String category){
		double num = 0d;
		int ct = 0;
		for(Stock s : stocks){
			if(s.getSector().equals(sector)){
				num += s.getValue(category);
				ct++;
			}
		}
		return num / (double) ct;
	}
	
	public static double parseDouble(String s){
		if(s.equals("") || s.equals(null)){
			return 0;
		}else{
			return Double.parseDouble(s);
		}
	}
	
	public static String truncateDouble(double d){
		return new BigDecimal(d + "").setScale(2, RoundingMode.HALF_UP).toString();
	}
}
