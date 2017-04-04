package cn.micromoving.bcp.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;


public final class NumberUtils {

    private NumberUtils() {
    }

   
    public static String formatDoubleNumber(String amountString) {
        double amount = Double.parseDouble(amountString);
        return formatDoubleNumber(amount,"###,###.00");
    }

  
    
    public static String formatDoubleNumber(Double amount,String pattern) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(2);
        df.setDecimalSeparatorAlwaysShown(true);
        df.applyPattern(pattern);
        return df.format(amount);
    }
 
    public static String formatLongNumber(String amountString) {
        long amount = Long.parseLong(amountString);
        return formatLongNumber(amount);

    }

    
    public static String formatLongNumber(long amount) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        DecimalFormat df = (DecimalFormat) nf;
        String pattern = "###,###";
        df.applyPattern(pattern);
        return df.format(amount);
    }

  
    public static String formatCurrency(String amountString, Locale locale) {

        double amount = Double.parseDouble(amountString);
        return formatCurrency(amount, locale);

    }

   
    public static String formatCurrency(double amount, Locale locale) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        return nf.format(amount);
    }

   
    public static String formatPlainCurrency(String amount) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormat df = (DecimalFormat) nf;
        df.setMaximumFractionDigits(3);
        //df.setDecimalSeparatorAlwaysShown(true);
        String pattern = "###,###";
        if (amount.indexOf(".") > 0) {
            pattern = "###,###.00";
        }
        df.applyPattern(pattern);
        return df.format(Double.parseDouble(amount));
    }

    public static Number parseCurrency(String amountString, Locale locale) {
    	
    	if (amountString == null || amountString.length() == 0) {
    		return new Long(0);
    	}
        
        Number result = null;
        NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
        try {
            result =  nf.parse(amountString);
        } catch (ParseException pe) {
            throw new NumberFormatException("Wrong Number Format: " + amountString);
        }
        
        return  result;
        
    }

  
    public static Number parseNumberString(String amountString) {
        
    	if (amountString == null || amountString.length() == 0) {
    		return new Long(0);
    	}
    	
        Number result = null;
        NumberFormat nf = NumberFormat.getNumberInstance();
        DecimalFormat df = (DecimalFormat) nf;
        String pattern = "###,###";
        if (amountString.indexOf(".") > 0) {
            pattern = "###,###.00";
        }
        df.applyPattern(pattern);
        try {
            result = df.parse(amountString);
        } catch (ParseException pe) {
        	throw new NumberFormatException("Wrong Number Format: " + amountString);
        }
        return  result;
    }

}