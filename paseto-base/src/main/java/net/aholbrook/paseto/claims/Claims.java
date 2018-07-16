package net.aholbrook.paseto.claims;

import net.aholbrook.paseto.service.Token;
import net.aholbrook.paseto.exception.claims.ClaimException;
import net.aholbrook.paseto.exception.claims.MultipleClaimException;

import java.util.function.Function;

public class Claims {
	public static final Claim[] DEFAULT_CLAIM_CHECKS = new Claim[] {
			new IssuedInPast(), new CurrentlyValid()
	};

	public static VerificationContext verify(Token token) {
		return verify(token, DEFAULT_CLAIM_CHECKS);
	}

	public static VerificationContext verify(Token token, Claim[] claims) {
		VerificationContext context = new VerificationContext(token);
		MultipleClaimException mre = new MultipleClaimException(token);

		for (Claim claim : claims) {
			try {
				claim.check(token, context);
			} catch (ClaimException re) {
				mre.add(re);
			}
		}

		mre.throwIfNotEmpty();
		return context;
	}

	public static <_ReturnType> _ReturnType verify(Token token, Function<VerificationContext, _ReturnType> callback) {
		VerificationContext context = verify(token);
		return callback.apply(context);
	}

	public static <_ReturnType> _ReturnType verify(Token token, Claim[] claims,
			Function<VerificationContext, _ReturnType> callback) {
		VerificationContext context = verify(token, claims);
		return callback.apply(context);
	}
}
