package com.in28minutes.unitesting.unittesting.spike;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonPathTest {

    @Test
    public void learning() {
        String responseFromService = "[" +
                "{\"id\":10000,\"name\":\"Pencil\",\"location\":\"Brad\"}," +
                "{\"id\":10001,\"name\":\"Pen\",\"location\":\"Ilidia\"}," +
                "{\"id\":10002,\"name\":\"Erasar\",\"location\":\"Juncar\"}" +
                "]";

        DocumentContext context = JsonPath.parse(responseFromService);
        int length = context.read("$.length()");
        assertThat(length).isEqualTo(3);

        List<Integer> ids = context.read("$..id");
        assertThat(ids).containsExactly(10000,10001,10002);
        System.out.println(context.read("$..location").toString());
    }
}
