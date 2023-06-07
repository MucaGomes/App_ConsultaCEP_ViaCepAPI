package com.app.consultarcep;

import static android.app.PendingIntent.getActivity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

import javax.security.auth.login.LoginException;

public class HttpService extends AsyncTask<Void, Void, CEP> {
    private final String cepInserted;
    public HttpService(String cep) { this.cepInserted = cep;}

    @Override
    protected CEP doInBackground(Void... voids) {
        StringBuilder result = new StringBuilder();

        if (this.cepInserted != null && this.cepInserted.length() == 8) {
            try {
                URL url = new URL("https://viacep.com.br/ws/" + this.cepInserted + "/json/");

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Content-type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                connection.setDoOutput(true);
                connection.setConnectTimeout(5000);
                connection.connect();

                Scanner scn = new Scanner(url.openStream());

                while (scn.hasNext()){
                    result.append(scn.next());
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return new Gson().fromJson(result.toString(), CEP.class);
    }
}