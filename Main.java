package Sample;

import java.util.ArrayList;
import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

	static ArrayList<Account> acc = new ArrayList<>();
	static ArrayList<Transaction> tra = new ArrayList<>();	
	
	public static ArrayList<Transaction> filTrans(int accNo) {
		ArrayList<Transaction> tra1 = new ArrayList<>();
		for(Transaction t :tra) {
			if(t.getAcc_no()==accNo) {
				tra1.add(t);
			}
		}
		
		return tra1;
	
	}
	public static  double StartBal(double curBal,int nom,ArrayList<Transaction> tra2) {
		LocalDate curDate=LocalDate.of(2023, 07, 01);
		LocalDate sixDate= curDate.minusMonths(nom);
		for(Transaction t:tra2) {
			if(t.getMode()=="withdraw" && t.getTdate().isAfter(sixDate)) {
				curBal+=t.getAmt();
			}
			else if(t.getMode()=="deposit" && t.getTdate().isAfter(sixDate)) {
				curBal-=t.getAmt();
			}
		}
		
		return curBal;
	}
	public long nomonth(LocalDate d1) {
		LocalDate curDate=LocalDate.of(2023, 07, 01);
		long monthsDifference = ChronoUnit.MONTHS.between(d1, curDate);
		return monthsDifference;
	}
	
	
	public static void main(String[] args) {
		
		
		
			try{
				BufferedReader bin=new BufferedReader(new FileReader("C:\\Users\\likhita.p\\eclipse-workspace\\likhi\\src\\Sample\\Account.txt"));
				BufferedReader bin1 = new BufferedReader(new FileReader("C:\\Users\\likhita.p\\eclipse-workspace\\likhi\\src\\Sample\\Transactions.txt"));
				String line;
				String line1;
					while((line=bin.readLine())!=null){
						String[] m= line.split(",");
						Account a = new Account(m[0],m[1],m[2],m[3]);
						acc.add(a);
					}
					while((line1=bin1.readLine())!=null) {
						String [] n= line1.split(",");
						Transaction t=new Transaction(n[0],n[1],n[2],n[3],n[4]);
						tra.add(t);
						
					}
					bin.close();
					bin1.close();
			}
			catch(Exception e) {}
			
	}
}
