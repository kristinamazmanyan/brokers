package com.brokerexam.common.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Handler {

    private MessageDigest md = null;

    private static MD5Handler md5 = null;

    private static final char[] hexChars = { 
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
            'a', 'b', 'c', 'd', 'e', 'f' };

    /**
     * Constructor is private so you must use the getInstance method
     */
    private MD5Handler() {
        try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// should never happen since we looking for MD5
			// that must be present in any JRE.
			throw new RuntimeException(e);
		}
    }

    /**
     * This returns the singleton instance
     */
    public static synchronized MD5Handler getInstance() {
        if (md5 == null) {
            md5 = new MD5Handler();
        }
        return (md5);
    }

    /**
     * Return md5 hash in hex format
     * @param dataToHash data to calculate md5 hash
     * @return md5 hash code of the given data
     */
    public String hashData(byte[] dataToHash) {
        return hexStringFromBytes((calculateHash(dataToHash)));
    }

    /**
     * Caculates md5 hash code of teh given data
     * @param dataToHash data to calculate md5 hash code
     * @return md5 hash code of the given data
     */
    public byte[] calculateHash(byte[] dataToHash) {
        synchronized (md) {
        	md.reset();
            md.update(dataToHash, 0, dataToHash.length);
            return (md.digest());
        }
    }

    /**
     * Returns hex string representation of the given array  
     * @param b array of byte
     * @return hex string representation of the given array
     */
    public String hexStringFromBytes(byte[] b) {
        StringBuffer hex = new StringBuffer(b.length * 2);
        int msb;
        int lsb;
        // MSB maps to idx 0
        for (int i = 0; i < b.length; i++) {
            // The binary operator "&" converts both operands to int,
            // if their type is byte or short
            msb = (b[i] & 0x000000F0) >>> 4;
            lsb = b[i] & 0x0000000F;
            hex.append(hexChars[msb]).append(hexChars[lsb]);
        }
        return (hex.toString());
    }
    
    /**
     * Converts the given byte array to decimal string values
     * @param barr the byte array to convert
     * @return the decimal string
     */
    public String decStringFromBytes(byte[] barr) {
		StringBuilder builder2 = new StringBuilder(barr.length * 3);
		for(byte b : barr){
			int i = b;
			if(i < 0){
				i = -i + 128;
			}
			if(i < 100){
				builder2.append("0");
			}
			if(i < 10){
				builder2.append("0");
			}
			builder2.append(String.valueOf(i));
		} 
		return builder2.toString();
    }
}