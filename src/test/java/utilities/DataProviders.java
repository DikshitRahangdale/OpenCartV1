package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Status")
	public Object[][] getStatus() {
		return new Object[][] { { "Yes" }, { "No" } };
	}

	@DataProvider(name = "Email")
	public Object[] getEmail() {
		return new Object[] { "pavanol", "pavanol@", "pavanol@gmail", "pavanol.com" };
	}
	@DataProvider(name = "Phones")
	public Object[] getPhones() {
		return new Object[] { "87", "pava", "111erewui", "ewr123466","78457438d6" };
	}
   //Test
}
