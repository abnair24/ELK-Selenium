package common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;

public class ELKMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String CONTENT_TYPE_VALUE = "application/json";
    private static final String ELASTICSEARCH_URL = "http://0.0.0.0:9200/app/_doc";

    public static void send(final TestStatus testStatus) {

        try {
            Unirest.post(ELASTICSEARCH_URL)
                    .header(CONTENT_TYPE,CONTENT_TYPE_VALUE)
                    .body(objectMapper.writeValueAsString(testStatus)).asJson();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
