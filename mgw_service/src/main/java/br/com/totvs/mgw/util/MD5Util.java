package br.com.totvs.mgw.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
	public static String hash(String str) throws NoSuchAlgorithmException {
		
		String result = null;
		
		if (str != null){
			
			try {
				MessageDigest m = MessageDigest.getInstance("MD5");
				m.update(str.getBytes(), 0, str.length());
				
				BigInteger i = new BigInteger(1, m.digest()); 
				
				result = String.format("%1$032x", i);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}		
		}
		
		return result;
	}
}
