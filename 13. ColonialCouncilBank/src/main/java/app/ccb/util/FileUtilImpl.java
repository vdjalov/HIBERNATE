package app.ccb.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtilImpl implements FileUtil {

	public String readFile(String filePath) throws IOException {
		
		List<String> readFile = Files.readAllLines(Paths.get(filePath));
		
		return String.join("\n", readFile);
	}

	

}
