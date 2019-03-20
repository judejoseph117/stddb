package com.shashank.platform.schoolcollegeapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
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

import org.json.JSONArray;
import org.json.JSONObject;

public class StaffRegister extends AppCompatActivity {
    AsyncHttpClient client;
    RequestParams params;
    EditText nameh,ageh,depth,emailh,passh,staffh;
    Button signup;
    TextView back;
    JSONArray jarray;

    String url="http://sicsglobal.co.in/StudentManagementApp/API/StaffRegister.aspx?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_staff_register);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        nameh=findViewById(R.id.namexml);
        ageh=findViewById(R.id.agexml);
        depth=findViewById(R.id.deptxml);
        staffh=findViewById(R.id.staffxml);
        emailh=findViewById(R.id.emailxml);
        passh=findViewById(R.id.passxml);
        signup=findViewById(R.id.sign);
        client=new AsyncHttpClient();
        params=new RequestParams();
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names=nameh.getText().toString();
                String ages=ageh.getText().toString();
                String depts=depth.getText().toString();
                String staffs=staffh.getText().toString();
                String emails=emailh.getText().toString();
                String passs=passh.getText().toString();

                if (!names.isEmpty()&&!ages.isEmpty()&&!depts.isEmpty()&&!staffs.isEmpty()&&!emails.isEmpty()&&!passs.isEmpty())
                {
                    params.put("name",names);
                    params.put("age",ages);
                    params.put("dept",depts);
                    params.put("staffId",staffs);
                    params.put("email",emails);
                    params.put("password",passs);
                    client.get(url,params,new AsyncHttpResponseHandler()
                    {
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try {
                                JSONObject obj=new JSONObject(content);
                                String st=obj.getString("Status");
                                if(st.equals("Success"))
                                {
                                    Toast.makeText(StaffRegister.this, "Sucess", Toast.LENGTH_SHORT).show();
                                    String staffusid=obj.getString("UserId");
                                    SharedPreferences shared=getApplicationContext().getSharedPreferences("any",MODE_PRIVATE);
                                    SharedPreferences.Editor edt=shared.edit();
                                    edt.putString("staffuserid",staffusid);
                                    edt.apply();

                                }else
                                {
                                    Toast.makeText(StaffRegister.this, "fail", Toast.LENGTH_SHORT).show();
                                }


                            }catch (Exception e){
                                Toast.makeText(StaffRegister.this, ""+e, Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
                else{
                    Toast.makeText(StaffRegister.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
