/*
 * *
 *  * Created by Juan Carlos Serrano Pérez on 18/03/19 9:14
 *  * Any question send an email to jcsp0003@red.ujaen.es
 *  * Copyright (c) 2019 . All rights reserved.
 *  * Last modified 18/03/19 4:46
 *
 */

package com.jcsp.historiasinteractivas.REST;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "https://tfm-historias.herokuapp.com/";

    public static GetPostService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(GetPostService.class);
    }
}