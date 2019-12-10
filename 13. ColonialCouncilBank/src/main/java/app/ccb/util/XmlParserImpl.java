package app.ccb.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlParserImpl implements XmlParser {

	@SuppressWarnings("unchecked")
	public <O> O parseXml(Class<O> objectClass, String filePath) throws JAXBException, FileNotFoundException {
		
		File file = new File(filePath);
    	FileInputStream fis = new FileInputStream(file);
    	BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fis));
    	JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (O) unmarshaller.unmarshal(bufferedReader);
	}

}
