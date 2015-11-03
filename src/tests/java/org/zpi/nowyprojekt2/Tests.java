package org.zpi.nowyprojekt2;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tests {

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
}