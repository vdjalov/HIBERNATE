package app.ccb.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.bind.JAXBException;

public interface CardService {

    Boolean cardsAreImported();

    String readCardsXmlFile() throws IOException;

    String importCards() throws FileNotFoundException, JAXBException;
}
