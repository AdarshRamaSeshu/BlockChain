package com.Project.Blochchain.Utils;

import java.text.SimpleDateFormat;
import java.util.UUID;

public class TransactionUtil {
	
	public static String generateTransactionId() {
		
		String txnId = "T-" + new SimpleDateFormat("ddMMyy").format(new java.util.Date()) + "-" + UUID.randomUUID().toString().substring(0,8);
		
		return txnId;
		
	}
	

}
