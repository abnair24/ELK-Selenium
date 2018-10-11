package common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mashape.unirest.http.ObjectMapper;

public class TestStatus {

    @JsonProperty("TestClass")
    private String testClass;

    @JsonProperty("Status")
    private String status;

    @JsonProperty("ExecutionTime")
    private String executionTime;

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTestClass(String testClass) {
        this.testClass = testClass;
    }


}
