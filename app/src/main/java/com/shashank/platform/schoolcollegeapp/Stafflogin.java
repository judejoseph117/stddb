package com.shashank.platform.schoolcollegeapp;

import android.content.Intent;
        import android.content.SharedPreferences;
        import android.support.design.widget.TextInputLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.loopj.android.http.AsyncHttpClient;
        import com.loopj.android.http.AsyncHttpResponseHandler;
        import com.loopj.android.http.RequestParams;

        import org.json.JSONObject;

public class Stafflogin extends AppCompatActivity {

    TextInputLayout email,password;
    AsyncHttpClient client;
    RequestParams params;
    JSONObject obj1;
    TextInputLayout userphone,passp;
    Button loginbtn,signup;
    String url="http://sicsglobal.co.in/StudentManagementApp/API/StaffLogin.aspx?email=adarshnair.sics@gmail.com&password=1234";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_stafflogin);


        client=new AsyncHttpClient();
        params=new RequestParams();


        email=(TextInputLayout)findViewById(R.id.email);
        password=(TextInputLayout)findViewById(R.id.password);
        loginbtn=findViewById(R.id.login);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if (email.getEditText().getText().toString().equals(""))
                {
                    email.setError("Input details");
                }
                else if(password.getEditText().getText().toString().equals(""))
                {
                    password.setError("Passwords not matching");
                }

                params.put("phone",userphone.getEditText().getText().toString());
                params.put("password",passp.getEditText().getText().toString());

                client.get(url,params,new AsyncHttpResponseHandler()
                {

                    @Override
                    public void onSuccess(String content)
                    {
                        super.onSuccess(content);

                        try
                        {
                            Log.e("innn","in");

                            obj1=new JSONObject(content);

                            String s=obj1.getString("status");

                            Toast.makeText(Stafflogin.this, ""+s,
                                    Toast.LENGTH_SHORT).show();
                            if (s.equals("Success"))
                            {

                                String sid=obj1.getString("UserId");
                                String sname=obj1.getString("Name");
                                SharedPreferences sp=getApplicationContext()
                                        .getSharedPreferences("d1",MODE_PRIVATE);
                                SharedPreferences.Editor ed=sp.edit();
                                ed.putString("keyid",sid);
                                ed.putString("keyname",sname);
                                Intent i=new Intent(Stafflogin.this,Main2Activity.class);
                                startActivity(i);


                            }


                        }
                        catch (Exception e)
                        {

                        }
                    }
                });










            }
        });



    }

    public void staffreg(View view) {
        startActivity(new Intent(getApplicationContext(),StaffRegister.class));
    }
}