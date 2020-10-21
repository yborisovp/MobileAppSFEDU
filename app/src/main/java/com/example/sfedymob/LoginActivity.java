package com.example.sfedymob;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Connection;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sfedymob.rxjavaLifeScycle.RxJavaLifeCycle;
import com.example.sfedymob.supporting_functions.ServerFunction;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class LoginActivity extends AppCompatActivity {
    private final String USER_DATA = "USER_DATA";
    RxJavaLifeCycle rxJavaLifeCycle = new RxJavaLifeCycle();
    private Connection mConnection = null;

    private String HOST = "10.0.2.2";
    private int PORT = 8000;
    ServerFunction serverFunction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Intent intent = new Intent(this, NavigationActivity.class);


        findViewById(R.id.login_activity_log_in_button).setOnClickListener(v -> {

            EditText email = findViewById(R.id.login_activity_email_address);
            EditText password = findViewById(R.id.login_activity_password);

            if(sendEmailAndPasswordRequest(email.getText().toString(), password.getText().toString())){
                User user = new User(email.getText().toString(), password.getText().toString());
                intent.putExtra(USER_DATA, user);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.login_activity_guest_button).setOnClickListener(v -> {
            User user = new User();
            intent.putExtra(USER_DATA, user);
            startActivity(intent);
            finish();
        });
    }

    private boolean sendEmailAndPasswordRequest(String email, String password) {
        ExecutorService executor;
        executor = Executors.newFixedThreadPool(1);
        Future<Boolean> future;

        Callable<Boolean> callable = new ServerFunction(email, password);
        future = executor.submit(callable);
        try {
            executor.shutdown();
            return future.get();
        }catch (InterruptedException | ExecutionException e) {
            Log.e("Callable fall", String.valueOf(e));
            executor.shutdown();
            return false;
        }
    }

    @Override
    protected void onStop() {
        rxJavaLifeCycle.dispose();
        super.onStop();
    }
}
