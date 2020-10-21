package com.example.sfedymob.supporting_functions;

import android.content.Context;

import com.example.sfedymob.rxjavaLifeScycle.RxJavaLifeCycle;
import com.example.sfedymob.supporting_functions.hash_functions.HashFunction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.Callable;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ServerFunction implements Callable<Boolean> {

    private Socket mSocket = null;
    private String mHost = "10.0.2.2";
    private int mPort = 8000;
    private String email,  passwordHash;

    public ServerFunction(String email, String passwordHash ) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    @Override
    public Boolean call() throws Exception {
        openConnection();
        return sendAccountData();
    }

    public void openConnection() throws Exception {
        try {
            mSocket = new Socket(mHost, mPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean sendAccountData() throws Exception {

        char n = '\n';
        if (mSocket == null || mSocket.isClosed())
            throw new Exception("Can't send data. Socked is Closed");
        try {
            mSocket = new Socket("10.0.2.2", 8000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        mSocket.getInputStream()));

        OutputStreamWriter writer = new OutputStreamWriter(mSocket.getOutputStream());
        try {
            writer.write("login" + n + email + n + passwordHash + n);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
        String response = null;
        try {
            response = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        closeConnection();
        return response.equals("true");
    }

    public void closeConnection() {
        if (mSocket != null && !mSocket.isClosed()) {
            try {
                mSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                mSocket = null;
            }
        }
    }
}
