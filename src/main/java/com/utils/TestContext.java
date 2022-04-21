package com.utils;

import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final Logger LOG = LogManager.getLogger(TestContext.class);

    public Response response;
    public Map<String, Object> session = new HashMap<String, Object>();
    public Map<String, Object> params = new HashMap<String, Object>();
    public ArrayList<String> titles = new ArrayList<>();
    public static String movieDetails = "";
    private static final String CONTENT_TYPE = PropertiesFile.getProperty("content.type");

    public RequestSpecification requestSetup() {
        RestAssured.reset();
        Options options = Options.builder().logStacktrace().build();
        RestAssuredConfig config = CurlRestAssuredConfigFactory.createConfig(options);
        RestAssured.baseURI = PropertiesFile.getProperty("baseURL");
        return RestAssured.given()
                .queryParams(params)
                .config(config)
                .filter(new RestAssuredRequestFilter())
                .contentType(CONTENT_TYPE)
                .accept(CONTENT_TYPE);
    }

    public void getMovieTitle() throws JSONException {
        try {
            JSONObject obj = new JSONObject(movieDetails);
            JSONArray arr = obj.getJSONArray("data");

            for (int i = 0; i < arr.length(); i++) {
                titles.add(arr.getJSONObject(i).getString("Title"));
            }

        } catch (JSONException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sortMovieTitle() throws Exception {
        try {
            Collections.sort(titles);
            for (int i = 0; i <= titles.size(); i++) {
                System.out.println(titles.get(i));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
