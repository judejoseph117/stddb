package com.shashank.platform.schoolcollegeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class Main3Activity extends AppCompatActivity {

    TextView back;
    JSONObject jsonObject;
    TextInputLayout email,password;
    Button login;
    AsyncHttpClient client;
    RequestParams params;
    String url="http://sicsglobal.co.in/StudentManagementApp/API/StudentLogin.aspx?";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main3);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        connectwidegets();

        client=new AsyncHttpClient();

        params=new RequestParams();

       login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String emails=email.getEditText().getText().toString();
               String passwds=password.getEditText().getText().toString();


               if(!emails.isEmpty()&&!passwds.isEmpty())
               {
                  params.put("email",emails);
                  params.put("password",passwds);

                  client.get(url,params,new AsyncHttpResponseHandler(){
                      @Override
                      public void onSuccess(String content) {
                          super.onSuccess(content);
                          try {
                              jsonObject=new JSONObject(content);
                              String status=jsonObject.getString("Status");
                              Toast.makeText(Main3Activity.this, ""+status, Toast.LENGTH_SHORT).show();
                              String Userid=jsonObject.getString("UserId");
                              String name=jsonObject.getString("Name");

                              SharedPreferences sharedlogin=getApplicationContext().getSharedPreferences("sharedlogin",MODE_PRIVATE);
                              SharedPreferences.Editor editor=sharedlogin.edit();
                              editor.putString("uid",Userid);
                              editor.putString("namekey",name);


                          } catch (JSONException e) {
                              e.printStackTrace();
                          }


                      }
                  });


               }else {
                   Toast.makeText(Main3Activity.this, "Enter all fields", Toast.LENGTH_LONG).show();
               }


           }
       });


    }

    private void connectwidegets() {

        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.loginbutton);
    }

    public void studregister(View view) {
        startActivity(new Intent(Main3Activity.this,StudentRegister.class));
    }
}
