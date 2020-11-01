/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karim.omran
 */
public class Resource {
    
    private static Resource resource = null;
    private static final String FILE_NAME = "config.properties";
    private Properties properties;
    
    private Resource() {
        readProperties();
    }
    
    public static Resource getInstance() 
    { 
        if (resource == null) {
            resource = new Resource(); 
        }
        return resource; 
    } 
    
    private void readProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(FILE_NAME)) {
            properties = new Properties();

            if (input == null) {
                return;
            }
            properties.load(input);
        } catch(IOException exception) {
            Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, exception);
            
        }
    }
    
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
 
}
