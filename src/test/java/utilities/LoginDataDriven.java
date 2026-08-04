package utilities;

import org.testng.annotations.DataProvider;

public class LoginDataDriven {

	@DataProvider(name = "LoginData")
	public Object[][] loginDataprovider() {
		Object[][] dataObjects = new Object[][] { { "john.doe@testmail.com", "John@123", "Invalid" },
				{ "alice.smith@testmail.com", "Alice@456", "Invalid" },
				{ "robert.johnson@testmail.com", "Robert@789", "Invalid" },
				{ "pofoye5680@apdtax.com", "Test@123456", "Valid" },
				{ "emma.wilson@testmail.com", "Emma@321", "Invalid" },
				{ "michael.brown@testmail.com", "Michael@654", "Invalid" }

		};

		return dataObjects;
	}

}
