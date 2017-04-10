package com.udacity.gradle.builditbigger.service;

import android.os.AsyncTask;

import com.example.juan.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by tincio on 01/04/2017.
 */

public class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    private MyApi myApiService = null;

    @Override
    protected String doInBackground(String... params) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://robotic-abode-162708.appspot.com/_ah/api/");
            myApiService = builder.build();
        }
        String name = params[0];

        try {
            return myApiService.sayHi(name).execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if(mListenerResponse!= null){
            mListenerResponse.getDownloadResponse("",result);
        }
    }

    public interface ListenerResponse{
        void getDownloadResponse(String mError, String mResponse);
    }

    public ListenerResponse mListenerResponse;

    public void setListenerResponse (ListenerResponse mListenerResponse){
        this.mListenerResponse = mListenerResponse;
    }
}

