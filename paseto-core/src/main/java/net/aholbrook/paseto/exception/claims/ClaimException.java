package net.aholbrook.paseto.exception.claims;

import net.aholbrook.paseto.exception.PasetoTokenException;
import net.aholbrook.paseto.service.Token;

public class ClaimException extends PasetoTokenException {
	private final String ruleName;

	public ClaimException(String s, String ruleName, Token token) {
		super(s, token);
		this.ruleName = ruleName;
	}

	public String getRuleName() {
		return ruleName;
	}
}
