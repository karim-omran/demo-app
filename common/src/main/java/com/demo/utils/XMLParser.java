/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.utils;

import com.demo.models.XML;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.reflections.Reflections;

/**
 *
 * @author karim.omran
 */
public class XMLParser {
    private static final String MODELS_PACKAGE = "com.demo.models";
    private static final String UTF_8 = "UTF-8";
    
    public static XML handleXMLParsing(InputStream requestBody) throws IOException {
        
        Set<Class<? extends XML>> classes = new Reflections(MODELS_PACKAGE).getSubTypesOf(XML.class);
        
        String content = getContent(requestBody);
        
        for (Class<? extends XML> clazz : classes) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                XML type =  clazz.cast(
                        unmarshaller.unmarshal(
                                new ByteArrayInputStream(content.getBytes())
                        )
                );
                return type;
            } catch (JAXBException ex) {}
        }
        return null;
    };
    
    private static String getContent(InputStream inputStream) throws IOException {
	BufferedReader httpInput = new BufferedReader(new InputStreamReader(inputStream, UTF_8));
	StringBuilder in = new StringBuilder();
	String input;
	while ((input = httpInput.readLine()) != null) {
            in.append(input);
	}
	httpInput.close();
	return in.toString().trim();
}
}
