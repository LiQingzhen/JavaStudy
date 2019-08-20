package com.ems.util;

import java.util.Base64;
import java.util.Base64.Decoder;

public class ImageUtil {
	
	/**
	 * Base64字符串解码器
	 * @param image
	 * @return
	 */
	public static byte[] baseToByte(String image) {
    	
		// 格式化
    	image = image.replaceAll("data:image/jpeg;base64,", ""); 

		Decoder decoder = Base64.getDecoder();
		// Base64解码      
		byte[] imageByte = null;
		try {
			imageByte = decoder.decode(image.toString().replace("\r\n", ""));      
			for (int i = 0; i < imageByte.length; ++i) {      
				if (imageByte[i] < 0) {// 调整异常数据      
					imageByte[i] += 256;      
				}      
			}      
		} catch (Exception e) {
			 e.printStackTrace(); 
		}					
		return imageByte;
    }
	
}
