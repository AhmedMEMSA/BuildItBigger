package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;

import ahmed.memsa.com.bigger.backend.jokeApi.JokeApi;


/**
 * Created by asmaa on 6/29/2017.
 */

public class JokesAsyncTask extends AsyncTask<Context, Void, String> {
    private static JokeApi myApiService = null;
    private Context context;
    private Callback callback;

    @Override
    protected String doInBackground(Context... params) {
        if (myApiService == null) {  // Only do this once
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // - 10.0.2.2 is localhost's IP address in Android emulator
                    // - turn off compression when running against local devappserver
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];
        if (context instanceof Callback){
            callback = (Callback) context;
        }else {
            throw new RuntimeException(context.toString()
                    + " must implement Callback");
        }
        try {
            return myApiService.getAJoke().execute().getJoke();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        callback.displayJoke(result);
        //Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }

    public interface  Callback{
        void displayJoke(String joke);
    }
}