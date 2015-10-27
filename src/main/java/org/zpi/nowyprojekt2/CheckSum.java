package org.zpi.nowyprojekt2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class CheckSum {
	public static String createHash(String fileName) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		String digest = getDigest(new FileInputStream(fileName), md, 2048);

		System.out.println("MD5 Digest:" + digest);
		return digest;
	}

	public static String getDigest(InputStream is, MessageDigest md, int byteArraySize)
			throws NoSuchAlgorithmException, IOException {

		md.reset();
		byte[] bytes = new byte[byteArraySize];
		int numBytes;
		while ((numBytes = is.read(bytes)) != -1) {
			md.update(bytes, 0, numBytes);
		}
		byte[] digest = md.digest();
		String result = new String(Hex.encodeHex(digest));
		return result;
	}

}
