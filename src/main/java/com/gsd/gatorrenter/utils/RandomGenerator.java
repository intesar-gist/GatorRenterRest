package com.gsd.gatorrenter.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.UUID;

/**
 * Created by Intesar on 3/5/2017.
 */
public class RandomGenerator {

    private static final char[] UID_CHARS = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

    public static String generateUid() {
        return UUID.randomUUID().toString();
    }

    public static String nextSessionId() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

}
