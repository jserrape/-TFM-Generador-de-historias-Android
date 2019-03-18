package com.jcsp.historiasinteractivas.REST;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://tfm-historias.herokuapp.com/";

    public static GetPostService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(GetPostService.class);
    }
}