package com.brokerexam.common.security;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.brokerexam.common.util.TextUtils;
import com.brokerexam.domain.user.User;


public class InformationEncryptor {
	
	private final static String ENCRYPTION_ALGORITHM_MD5 = "MD5";
	
	public static String getEncryptedText(String preEncryptedText){
		byte[] preEncryptedTextBytes = preEncryptedText.getBytes();
		try {
			MessageDigest algorithm = MessageDigest.getInstance(ENCRYPTION_ALGORITHM_MD5);
			algorithm.reset();
			algorithm.update(preEncryptedTextBytes);
			byte []messageDigest = algorithm.digest();
			
			StringBuffer postEncryptedInformation = new StringBuffer();
			for (int ii=0; ii<messageDigest.length; ii++) {
				postEncryptedInformation.append(Integer.toHexString(0xFF & messageDigest[ii]));
			}
			return postEncryptedInformation.toString();
		} catch (NoSuchAlgorithmException e) {			
		}
		return null;
	}
	
	public static void encryptPassword(User user) {
		String password = user.getPassword();
		
		if (TextUtils.isNotEmpty(password)) {
			String encryptedPassword = InformationEncryptor.getEncryptedText(password);
			user.setPassword(encryptedPassword);
		}
	}
	
	public static boolean isSamePassword(String password, String externalPassword){
		String externalHash = getEncryptedText(externalPassword);
		if(password != null && externalHash != null){
			return password.equals(externalHash);
		}
		return false;
	}	
}
