import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckSumBuilderSHA1 {
	
	 private static final char[] digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};   
	 public static String createSignature(String token, String timestamp, String nonce) {        
		 String[] arr = new String[]{token, timestamp, nonce};        
		 Arrays.sort(arr);        
		 StringBuilder content = new StringBuilder();        
		 for (String anArr : arr) {            
			 content.append(anArr);        
		}        
		MessageDigest md;        
		String tmpStr = null;
		try {            
			md = MessageDigest.getInstance("SHA-1");            
			byte[] digest = md.digest(content.toString().getBytes("UTF-8"));            
			tmpStr = byteToStr(digest);        
		} catch (NoSuchAlgorithmException e) {            
//			LOG.error("加密⽅方式异常", e);        
		} catch (UnsupportedEncodingException e) {            
//			LOG.error("编码格式不不⽀支持", e);        
		}
		return tmpStr;    
	}           
	 public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {        
//		 if (isEmpty(token, signature, timestamp, nonce)) {            
//			 return false;        
//		 }        
		 String[] arr = new String[]{token, timestamp, nonce};        
		 Arrays.sort(arr);        
		 StringBuilder content = new StringBuilder();        
		 for (String anArr : arr) {            
			 content.append(anArr);        
		 }        
		 MessageDigest md;        
		 String tmpStr = null;
		 try {            
			 md = MessageDigest.getInstance("SHA-1");            
			 byte[] digest = md.digest(content.toString().getBytes("UTF-8"));            
			 tmpStr = byteToStr(digest);        
		 } catch (NoSuchAlgorithmException e) {            
//			 LOG.error("加密⽅方式异常", e);        
		 } catch (UnsupportedEncodingException e) {            
//			 LOG.error("编码格式不不⽀支持", e);        
		 }        
		 return tmpStr != null && tmpStr.equalsIgnoreCase(signature);    
	 }    

	 private static String byteToStr(byte[] byteArray) {        
		 int len = byteArray.length;        
		 StringBuilder strDigest = new StringBuilder(len * 2);        
		 for (byte aByteArray : byteArray) {            
			 strDigest.append(byteToHexStr(aByteArray));        
		 }        
		 return strDigest.toString();    
	 }
	 

	 private static String byteToHexStr(byte mByte) {        
		 char[] tempArr = new char[2];        
		 tempArr[0] = digit[(mByte >>> 4) & 0X0F];        
		 tempArr[1] = digit[mByte & 0X0F];        
		 return new String(tempArr);    
	 }

}

