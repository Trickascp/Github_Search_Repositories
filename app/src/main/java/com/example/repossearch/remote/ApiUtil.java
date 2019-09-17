package com.example.repossearch.remote;

public class ApiUtil {

    private ApiUtil(){

    }

    public static final String base_url = "https://api.github.com/";

    public static ApiService getDataService(){
        return RetroClient.getClient(base_url).create(ApiService.class);
    }


}
