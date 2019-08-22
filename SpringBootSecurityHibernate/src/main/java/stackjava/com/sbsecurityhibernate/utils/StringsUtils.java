package stackjava.com.sbsecurityhibernate.utils;

import java.util.Random;

public class StringsUtils {
	public static String createRandomNumber(int lenght) {
		Random rand = new Random();
		int[] random = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		StringBuilder strRandom = new StringBuilder();
		for (int i = 0; i < lenght; i++) {
			strRandom.append(String.valueOf(random[rand.nextInt(10)]));
		}
		return strRandom.toString();
	}
}
