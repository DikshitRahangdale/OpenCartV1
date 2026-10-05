package utilities;
import org.testng.annotations.DataProvider;

public class DataProviders{

	@DataProvider(name = "Status")
	public Object[][] getStatus() {
	    return new Object[][] {
	        {"Yes"},
	        {"No"}
	    };
	}

}
