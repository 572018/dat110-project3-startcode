package no.hvl.dat110.util;

/**
 * project 3
 * @author tdoy
 *
 */

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash { 
	
	private static BigInteger hashint; 
	private static byte[] digested;
	
	public static BigInteger hashOf(String entity) {		
		
		// Task: Hash a given string using MD5 and return the result as a BigInteger.
		try {
			// we use MD5 with 128 bits digest
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// compute the hash of the input 'entity'
			digested = md5.digest(entity.getBytes("utf8"));
			// convert the hash into hex format
			String hashvalue = toHex(digested);
			// convert the hex into BigInteger
			hashint = new BigInteger(hashvalue, 16);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// return the BigInteger
		return hashint;
	}
	
	public static BigInteger addressSize() {
		
		// Task: compute the address size of MD5
		// compute the number of bits = digest length * 8
		int nbits = bitSize();
		// compute the address size = 2 ^ number of bits
		BigInteger aSize = BigInteger.valueOf(2);
		// return the address size
		return aSize.pow(nbits);
	}
	
	public static int bitSize() {
		// find the number of bits
		int length;
		try {
			length = MessageDigest.getInstance("MD5").getDigestLength();
			return length*8;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static String toHex(byte[] digest) {
		StringBuilder strbuilder = new StringBuilder();
		for(byte b : digest) {
			strbuilder.append(String.format("%02x", b&0xff));
		}
		return strbuilder.toString();
	}

}
