package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

     private Properties prop;
     public Properties initProperties(){
         prop = new Properties();
         try (InputStream input = getClass().getClassLoader().getResourceAsStream("configuration/properties.properties")) {
             if(input == null){
                 throw new RuntimeException("Properties file is not found");
             }
             prop.load(input);
         } catch (IOException e) {
             throw new RuntimeException("Failed to load properties: " +e.getMessage(), e);
         }
         return prop;
     }
}
