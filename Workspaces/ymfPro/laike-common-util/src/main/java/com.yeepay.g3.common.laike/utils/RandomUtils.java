package com.yeepay.g3.common.laike.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author eric
 * @date 2013-5-15
 */
public class RandomUtils {
	public static final String randomString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new SecureRandom();
			numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
					+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	public static final String randomNumberString(int length) {
		Random randGen = null;
		char[] numbersAndLetters = null;

		if (length < 1) {
			return null;
		}
		if (randGen == null) {
			randGen = new SecureRandom();
			numbersAndLetters = ("01234567890123456789").toCharArray();
		}
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(19)];
		}
		return new String(randBuffer);
	}
}
