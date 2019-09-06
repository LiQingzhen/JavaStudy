import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AESUtil {

	private static final String CipherMode = "AES/CBC/PKCS7Padding";
	private static final String IV = "0000000000000000";

	/**
	 * 加密
	 * @param content 明文
	 * @param password 密钥
	 * @return
	 */
	public static String encrypt(String content, String password) {
		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			SecureRandom securerandom = new SecureRandom(tohash256Deal(password));
//			kgen.init(256);
//			SecretKey secretKey = kgen.generateKey();
//			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(password.getBytes("ASCII"), "AES");
//			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes("ASCII"));
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
			byte[] byteContent = content.getBytes();
			byte[] cryptograph = cipher.doFinal(byteContent);
			return new String(Base64.getEncoder().encode(cryptograph));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * @param cryptograph 密文
	 * @param password	密钥
	 * @return
	 */
	public static String decrypt(String cryptograph, String password) {
		try {
//			KeyGenerator kgen = KeyGenerator.getInstance("AES");
//			SecureRandom securerandom = new SecureRandom(tohash256Deal(password));
//			kgen.init(256);
//			SecretKey secretKey = kgen.generateKey();
//			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(password.getBytes("ASCII"), "AES");
//			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			IvParameterSpec ivspec = new IvParameterSpec(IV.getBytes("ASCII"));
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance(CipherMode);
			cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
			byte[] content = cipher.doFinal(Base64.getDecoder().decode(cryptograph));
			return new String(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	private static byte[] tohash256Deal(String datastr) {
//		try {
//			MessageDigest digester = MessageDigest.getInstance("SHA-256");
//			digester.update(datastr.getBytes());
//			byte[] hex = digester.digest();
//			return hex;
//		} catch (NoSuchAlgorithmException e) {
//			throw new RuntimeException(e.getMessage());
//		}
//	}

}
