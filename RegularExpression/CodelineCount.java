import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author LIQINGZHEN
 * 2018-11-15
 * 
 * This program can help you to get statistics for JAVA code.
 * Show you how many codeLines, whiteLines and annotationLines in Files.
 */
public class CodelineCount {

	private static int codeLine = 0;
	private static int whiteLine = 0;
	private static int annotationLine = 0;
	
	public static void main(String[] args) {
		
		File file = null;
		BufferedReader br = null;
		String line = null;
		String path;
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input a file path like \"F:\\code\"");
		path = scanner.nextLine();
		file = new File(path);
		
		File[] files = file.listFiles();
		for(int i = 0; i < files.length; i++){
			
			if(files[i].getName().matches(".*\\.java$")){
				
				try {
					
					boolean flag = false;
					br = new BufferedReader(new FileReader(files[i]));
					while((line = br.readLine()) != null){
						
						if(line.matches("^[\\s]*")){
							
							whiteLine++;
						}else if(line.matches("^[\\s]*/\\*.*")){
							
							// annotationLine START
							annotationLine++;
							if(!line.endsWith("*/"))	flag = true;
						}else if(line.matches(".*\\*/$")){
							
							// annotationLine END
							annotationLine++;
							flag = false;
						}else if(flag){
							
							annotationLine++;
						}else if(line.matches("^[\\s]*//.*")){
							
							annotationLine++;
						}else{
							
							codeLine++;
						}
					}
					
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}finally {
					
					if(br != null){
						try {
							br.close();
							br = null;
						} catch (IOException e) {							
							e.printStackTrace();
						}
					}
				}
			}			
		}
		System.out.println("codeLine:" + codeLine);
		System.out.println("annotationLine:" + annotationLine);
		System.out.println("whiteLine:" + whiteLine);
	}
}
