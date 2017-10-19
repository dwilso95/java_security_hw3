package java_security_hw3.part1;

public class VerifierHelper {

	public static void main(String[] args) {
		if (args.length != 1) {
			throw new IllegalArgumentException("Must supply 1 and only 1 argument.");
		}
		
		switch (args[0]) {
		case "11":
			new Pass11();
			break;
		case "12":
			new Pass12();
			break;
		case "21":
			new Pass21();
			break;
		case "22":
			new Pass22();
			break;
		case "31":
			new Pass31().add(2, 3);
			break;
		case "32":
			new Pass32(10);
			break;
		case "41":
			new Pass41().printHello();
			break;
		case "42":
			new Pass42().doSomething();
			break;
		default:
			throw new IllegalArgumentException("Argument [" + args[0] + "] is not a supported argument.");
		}

	}

}
