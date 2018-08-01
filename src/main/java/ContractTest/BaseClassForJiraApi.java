package ContractTest;

import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseClassForJiraApi {

    Properties properties = new Properties();

    @BeforeTest
    public String getBaseUri() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("/Users/monilj/Downloads/Devlopment/TescoRepos/ContractTestOld/src/main/resources/env.properties");
        properties.load(fileInputStream);
        String baseUri =properties.getProperty("jiraApiHost");
        return baseUri;
    }

}
