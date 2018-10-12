package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mashape.unirest.http.ObjectMapper;

public class TestStatus {

    @JsonProperty("testClass")
    private String testClass;

    @JsonProperty("status")
    private String status;

    @JsonProperty("executionTime")
    private String executionTime;

    @JsonProperty("browser")
    private String browser;

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

}
