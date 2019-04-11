import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;


public class ImageReadAndWrite {

	public static void main(String[] args) {
		
		InputStream inputStream = null;
    	String picture = null;
    	OutputStream outputStream = null;
    	
    	try {
    		// 读文件
    		inputStream = new FileInputStream("C:/one.png");
    		byte[] data = new byte[inputStream.available()];
    		inputStream.read(data);
    		inputStream.close();

    		// Base64编码
    		Base64.Encoder encoder = Base64.getEncoder();
        	picture = encoder.encodeToString(data);
    		
        	// 解码，输出文件
        	Base64.Decoder decoder = Base64.getDecoder();
    		data = picture.getBytes();      	
        	outputStream = new FileOutputStream( "C:/Software/two.png");	// "C:/Software/two.png"
			outputStream.write(decoder.decode(data));
			outputStream.flush();
			
			// 成功响应码
	    	System.out.println("success");
	    	
		} catch (IOException e) {
			e.printStackTrace();		
    		System.out.println("fail");
			
		}finally {
			if(outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
		}
	}

}
