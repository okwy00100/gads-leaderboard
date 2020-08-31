package com.okwy.gadsleaderboard.utilities;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.okwy.gadsleaderboard.model.ResponseBody;


import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class UtilityClass {

    public static List<ResponseBody> skillIQGETProcessor(){
        List<ResponseBody> list = new ArrayList<>();

        Gson responsePayload = new Gson();
        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://gadsapi.herokuapp.com/api/skillIQ")
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            list = responsePayload.fromJson(response.body().string(), new TypeToken<List<ResponseBody>>(){}.getType());

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }



    public static List<ResponseBody> topLearnersGETProcessor(){
        List<ResponseBody> list = new ArrayList<>();

        Gson responsePayload = new Gson();
        OkHttpClient httpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://gadsapi.herokuapp.com/api/hours")
                .addHeader("content-type", "application/json")
                .addHeader("cache-control", "no-cache")
                .build();

        try{
            Response response = httpClient.newCall(request).execute();
            list = responsePayload.fromJson(response.body().string(), new TypeToken<List<ResponseBody>>(){}.getType());

        }catch (Exception e){
            e.printStackTrace();
        }

        return list;
    }


    public static String projectSubmissionFormProcessor(String firstName, String lastName, String email, String githubUrl){
        String responseCode = "";

        /**
         * side note: I chose not to utilize the retrofit implementation because of the gradle conflicts with the okhttp3 libraries, as it was recommended not mandatory
         * */

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("entry.1877115667", firstName)
                .add("entry.2006916086", lastName)
                .add("entry.1824927963", email)
                .add("entry.284483984", githubUrl)
                .build();

        Request request = new Request.Builder()
                .url("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
//                .url("https://docs.google.com/forms/d/e/1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponsexyz")  //used a dummy URL to test the appearance of the custom toast
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("cache-control", "no-cache")
                .build();

        try{
            Response response = client.newCall(request).execute();
            responseCode = String.valueOf(response.code());

        }catch (Exception e){
            e.printStackTrace();
        }

        return responseCode;
    }


}
