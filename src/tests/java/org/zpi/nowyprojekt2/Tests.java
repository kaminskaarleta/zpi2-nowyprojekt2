package org.zpi.nowyprojekt2;

import org.junit.Assert;
import org.junit.Test;
import org.zpi.nowyprojekt2.FileEncryption.InvalidAESStreamException;
import org.zpi.nowyprojekt2.FileEncryption.InvalidKeyLengthException;
import org.zpi.nowyprojekt2.FileEncryption.InvalidPasswordException;
import org.zpi.nowyprojekt2.FileEncryption.StrongEncryptionNotAvailableException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Tests {

	final String passwordKey = "jksfid";	
	final int keyLength = 128;
	
    @Test
    public void testCheckSumMD5() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        String expected = "3F17331DDC41C5B7EF305BDB27934B51";
        String result = CheckSum.createHashMD5("file.txt");

		Assert.assertTrue(result.equalsIgnoreCase(expected));
    }

    @Test
    public void testCheckSumSHA1() throws NoSuchAlgorithmException, FileNotFoundException, IOException {
        String expected = "55B3DB1040A10A16BCC047F7E3F646C12D2B63A1";
        String result = CheckSum.createHashSHA1("file.txt");

		Assert.assertTrue(result.equalsIgnoreCase(expected));
    }
    
    @Test
    public void testEncryptFile() throws FileNotFoundException, InvalidKeyLengthException, StrongEncryptionNotAvailableException, IOException, InvalidPasswordException, InvalidAESStreamException{
    	FileEncryption secure = new FileEncryption();
    	
    	String startFile = "file.txt";
    	String encryptedFile = "encryptedFile.txt";
    	String decryptedFile = "decryptedFile.txt";
    	
    	secure.encrypt(keyLength, passwordKey.toCharArray(), new FileInputStream(startFile), new FileOutputStream(encryptedFile));
    	secure.decrypt(passwordKey.toCharArray(), new FileInputStream(encryptedFile), new FileOutputStream(decryptedFile));

    	byte[] bytesOfEncryptedFile = Files.readAllBytes(Paths.get(startFile));
    	byte[] bytesOfDencryptedFile = Files.readAllBytes(Paths.get(decryptedFile));

    	Assert.assertTrue(Arrays.equals(bytesOfEncryptedFile, bytesOfDencryptedFile));
    }
   
    
    @Test(expected = FileNotFoundException.class)  
    public void testEncryptWhenFileNotExist() throws FileNotFoundException, InvalidKeyLengthException, StrongEncryptionNotAvailableException, IOException{
    	FileEncryption secure = new FileEncryption();
    	secure.encrypt(keyLength, passwordKey.toCharArray(), new FileInputStream("a.txt"), new FileOutputStream("encryptedFile.txt"));
    }
    
    @Test(expected = InvalidKeyLengthException.class)  
    public void testEncryptWhenInvalidKeyLength() throws FileNotFoundException, InvalidKeyLengthException, StrongEncryptionNotAvailableException, IOException{
    	FileEncryption secure = new FileEncryption();
    	secure.encrypt(1, passwordKey.toCharArray(), new FileInputStream("decryptedFile.txt"), new FileOutputStream("encryptedFile.txt"));
    }
}