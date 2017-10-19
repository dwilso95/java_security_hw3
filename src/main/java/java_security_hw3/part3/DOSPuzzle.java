package java_security_hw3.part3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class DOSPuzzle {

	/**
	 * Runs a simple DOS puzzle solver simulation.
	 * 
	 * A hash is computed from a string of bits.
	 * 
	 * The hash and some ordered subset of the bits, starting from the beginning
	 * of the string is supplied to some client.
	 * 
	 * This client then attempts to find the missing bits. These are then return
	 * to the server and verified.
	 * 
	 * The client in this simulation performs a brute force search, trying all
	 * possible combinations until the hash is found.
	 * 
	 * @param args
	 *            - all provided arguments are ignored
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(final String[] args) throws NoSuchAlgorithmException {
		System.out.println("Running part 3 - DOS Puzzle");

		final String bits = "10110100101001010110001011011011011101010111001010";

		final MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		final byte[] hash = mDigest.digest(bits.getBytes());

		// generate puzzle of different difficulties, from 5 bits missing up to
		// 10
		for (int y = 5; y < 20; y++) {
			System.out.println("\tSending puzzle with " + y + " missing bits:");
			final String proposedSolution = runClientPuzzleSolver(bits.substring(0, bits.length() - y), y, hash);
			if (proposedSolution.equals(bits.substring(bits.length() - y))) {
				System.out.println("\tProposed solution [" + proposedSolution + "] is correct! ");
			}
		}

	}

	/**
	 * Attempts to find the missing bits which will equal the given hash.
	 * 
	 * @param knownBits
	 *            - String of ordered bits from the start of the string
	 * @param i
	 *            - number of missing bits
	 * @param hash
	 *            - the hash of the entire string of bits
	 * @return the solution to the puzzle, which is, the missing bits of the
	 *         entire string
	 * @throws NoSuchAlgorithmException
	 *             - thrown if the digest (SHA1 in this case) is not available
	 */
	private static final String runClientPuzzleSolver(final String knownBits, final int i, final byte[] hash) throws NoSuchAlgorithmException {
		final MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		String currentBits = generateStringOfZeroBits(i);
		
		while (!currentBits.equals("")) {
			if (Arrays.equals(mDigest.digest((knownBits + currentBits).getBytes()), hash)) {
				return currentBits;
			}
			currentBits = incrementBitString(currentBits);
		}

		throw new RuntimeException("Unable to find y bits!");
	}

	/**
	 * Generates a string of '0's of the length specified
	 * 
	 * @param length
	 *            - number of '0's in the resulting string
	 * @return String of '0's
	 */
	private static final String generateStringOfZeroBits(int length) {
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			sb.append("0");
		}
		return sb.toString();
	}

	/**
	 * Increments a string of bits and returns the new string.
	 * 
	 * @param bitString
	 *            - string of '1's and '0's to increment
	 * @return string of bits which is one greater than the provided bitString
	 */
	private static final String incrementBitString(final String bitString) {
		final char[] bitsAsArray = bitString.toCharArray();

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
