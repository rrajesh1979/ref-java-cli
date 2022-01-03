package org.rrajesh1979.demo;

import java.io.File;
import java.math.BigInteger;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckSum {
    static String algorithm = "MD5";

    public static void main(String[] args) {
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }
        try {
            byte[] checkSum = getCheckSum(fileName);
            System.out.printf("%0" + (checkSum.length*2) + "x%n", new BigInteger(1, checkSum));
        } catch (IOException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public static byte[] getCheckSum(String fileName) throws IOException, NoSuchAlgorithmException {
        File file = new File(fileName);
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        return digest;
    }
}
