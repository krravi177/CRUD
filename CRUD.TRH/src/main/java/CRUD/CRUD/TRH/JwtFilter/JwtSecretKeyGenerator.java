package CRUD.CRUD.TRH.JwtFilter;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtSecretKeyGenerator {

	public static void main(String[] args) {
		String secretKey = generateRandomSecretKey();
		System.out.println("Generated JWT Secret Key: " + secretKey);
	}

	public static String generateRandomSecretKey() {
		SecureRandom secureRandom = new SecureRandom();
		byte[] randomBytes = new byte[32];
		secureRandom.nextBytes(randomBytes);
		return Base64.getEncoder().encodeToString(randomBytes);
	}
}
