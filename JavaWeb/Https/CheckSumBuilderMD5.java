import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckSumBuilderMD5 {

	private static final String KEY = "xxxxxxxxxxxxxxxxxxxxxxx";
	
	public static String createSignature(String bizid, String cmdid, String nonce, String requestData) {        
		String sign = "bizid=" + bizid + "&cmdid=" + cmdid + "&nonce=" + nonce +"&req=" + requestData + "&key=" + KEY;     
		MessageDigest md;        
		String result = null;
		try {            
			md = MessageDigest.getInstance("MD5");            
			byte[] digest = md.digest(sign.toString().getBytes("UTF-8"));            
			result = byteToStr(digest).toUpperCase();        
		} catch (NoSuchAlgorithmException e) {            
//			LOG.error("加密⽅方式异常", e);        
		} catch (UnsupportedEncodingException e) {            
//			LOG.error("编码格式不不⽀支持", e);        
		}
		return result;    
	}
	
	private static String byteToStr(byte[] byteArray) {        
		 int length = byteArray.length;        
		 StringBuilder hex = new StringBuilder(length * 2);   
		 for (byte b : byteArray) {
			 if ((b & 0xFF) < 0x10){
				 hex.append("0");
	         }
	         hex.append(Integer.toHexString(b & 0xFF));
	     }
		   
		 return hex.toString();    
	 }
}
