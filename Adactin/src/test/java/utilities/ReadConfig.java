  package utilities;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
Properties prop;
	
	public ReadConfig() {
	
		File src = new File(".\\Confuguration\\config.properties");
	try {		
			FileInputStream fis = new FileInputStream(src);
			prop = new Properties();
			prop.load(fis);
		}
    catch (Exception e) {
	    	
	   		System.out.println("Exception is"+ e.getMessage());
    	}
	}
	public String getApplicationURL() {
		String url = prop.getProperty("baseUrl");
	    return url;
	}
   public String getChromepath() {
	   String Chrome = prop.getProperty("chromepath");
        return Chrome;
   }
   public String getgeckopath() {
	   String gecko = prop.getProperty("geckopath");
        return gecko;
   }
   public String getedgepath() {
	   String edge = prop.getProperty("edgepath");
        return edge;
   }	
}
