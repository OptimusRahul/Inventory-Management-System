package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEntries {
	public boolean CheckLetter(String text){
		int length=text.length();
		final String regexcheck="^[A-Za-z\\sA-Za-z\\sA-Za-z]{"+length+"}$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckGST(String text){
		final String regexcheck="^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckPhone(String text){
		final String regexcheck="^(1\\-)?[0-9]{3}\\-?[0-9]{3}\\-?[0-9]{4}$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckPAN(String text){
		final String regexcheck="^[a-zA-Z]{5}[0-9]{4}[a-zA-Z]{1}";//[A-Z]{5}\\d{4}[A-Z]{1}
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckAadhaar(String text){
		final String regexcheck="^[0-9]{4}[0-9]{4}[0-9]{4}";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckBank(String text)
	{
		final String regexcheck="^[0-9]{9,18}$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;	
	}
	public boolean CheckEmail(String text)
	{
		final String regexcheck="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
				+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;	
	}
	public boolean CheckIfsc(String text){
		final String regexcheck="^[A-Za-z]{4}0[A-Z0-9a-z]{6}$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckOpenAmount(String text){
		final String regexcheck="^[0-9]+$";
		boolean b=Pattern.matches(regexcheck, text);
		return b;
	}
	public boolean CheckOpenSize(String text){
		final String regexcheck="^[0-9//sA-Za-z]";
		boolean b=Pattern.matches(regexcheck, text);
		return b;	
	}
}
