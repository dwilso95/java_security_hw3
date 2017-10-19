package java_security_hw3.part3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DOSPuzzle {

	public static void main(String[] args) throws NoSuchAlgorithmException {

		final String bits = "10110100101001010110001011011011011101010111001010";

		final MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		final byte[] hash = mDigest.digest(bits.getBytes());

		// generate puzzle of different difficulties, from 5 bits missing up to 20
		for (int y = 5; y < 20; y++) {
			final String proposedSolution = runDemo(bits.substring(0, bits.length() - y), y, hash);
			if (proposedSolution.equals(bits.substring(bits.length() - y))) {
				System.out.println("Proposed solution [" + proposedSolution + "] is correct! ");
			}
		}

	}

	private static String runDemo(String knownBits, int i, byte[] hash) throws NoSuchAlgorithmException {
		String currentBits = generateStringOfZeroBits(i);
		final MessageDigest mDigest = MessageDigest.getInstance("SHA1");

		while (!currentBits.equals("")) {
			if (Arrays.equals(mDigest.digest((knownBits + currentBits).getBytes()), hash)) {
				return currentBits;
			}
			currentBits = getNextBits(currentBits);
		}

		throw new RuntimeException("Unable to find y bits!");
	}

	private static String generateStringOfZeroBits(int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	public static String getNextBits(String currentBits) {
		final char[] bitsAsArray = currentBits.toCharArray();

		for (int i = bitsAsArray.length - 1; i >= 0; i--) {
			if (bitsAsArray[i] == '0') {
				bitsAsArray[i] = '1';
				return new String(bitsAsArray);
			} else {
				bitsAsArray[i] = '0';
			}
		}

		return "";
	}
}
