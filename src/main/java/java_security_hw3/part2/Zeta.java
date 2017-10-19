package java_security_hw3.part2;

public class Zeta {

	@AuthorizationCheck
	@LockCriticalResource
	void m1() {

	}

	@AuditEvent
	void m2() {

	}

	@AuthorizationCheck
	@AuditEvent
	@LockCriticalResource
	void m3() {

	}

}
