package Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Base {

  public static String getPropertyValue(String file, String property) throws IOException {
    InputStream input = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/config/"+file+".properties");
    Properties prop = new Properties();
    // load a properties file
    prop.load(input);
    return prop.getProperty(property);
  }
}
