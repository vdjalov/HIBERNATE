package mostwanted.util;

import java.io.*;

public class FileUtilImpl implements FileUtil {

    public String readFile(String filePath) throws IOException {
       
    	File file = new File(filePath);
    	FileInputStream fis = new FileInputStream(file);
    	@SuppressWarnings("resource")
		BufferedReader bf = new BufferedReader(new InputStreamReader((fis)));
    	
    	StringBuilder sBuilder = new StringBuilder();
    	String line = "";
    		while((line = bf.readLine()) != null) {
    			sBuilder.append(line)
    					.append(System.lineSeparator());
    		}
    		
        return sBuilder.toString();
    }
}