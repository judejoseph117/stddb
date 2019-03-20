package com.shashank.platform.schoolcollegeapp;

import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class StudentRegister extends AppCompatActivity {

    TextView back;
    TextInputLayout name,regno,dob,course,period,email,pass,age;
    Button submitbtn;

    AsyncHttpClient client;
    RequestParams params;

    String url="http://sicsglobal.co.in/StudentManagementApp/API/Register.aspx?";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_student_register);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        name=findViewById(R.id.namexml);
        regno=findViewById(R.id.regnoxml);
        dob=findViewById(R.id.dobxml);
        course=findViewById(R.id.coursexml);
        period=findViewById(R.id.periodxml);
        email=findViewById(R.id.emailxml);
        pass=findViewById(R.id.passxml);
        submitbtn=findViewById(R.id.signupbtn);
        age=findViewById(R.id.agexml);

        client=new AsyncHttpClient();
        params=new RequestParams();

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nams=name.getEditText().getText().toString();
                String reg=regno.getEditText().getText().toString();
                String birth=dob.getEditText().getText().toString();
                String cour=course.getEditText().getText().toString();
                String per=period.getEditText().getText().toString();
                String eml=email.getEditText().getText().toString();
                String paswd=pass.getEditText().getText().toString();
                String ages=age.getEditText().getText().toString();



                if (!nams.isEmpty()&&!reg.isEmpty()&&!birth.isEmpty()&&!cour.isEmpty()&&!per.isEmpty()&&!eml.isEmpty()&&!paswd.isEmpty())
                {

                    params.put("name",nams);
                    params.put("regno",reg);
                    params.put("dob",birth);
                    params.put("course",cour);
                    params.put("periodofcrs",per);
                    params.put("email",eml);
                    params.put("password",paswd);
                    params.put("age",ages);

                    client.get(url,params,new AsyncHttpResponseHandler(){
                        @Override
                        public void onSuccess(String content) {
                            super.onSuccess(content);
                            try{
                                JSONObject jobj=new JSONObject(content);
                                String s= jobj.getString("Status");

                                if (s.equals("Success")){
                                    Toast.makeText(StudentRegister.this, "Success", Toast.LENGTH_SHORT).show();

                                    String userid=jobj.getString("UserId");
                                    SharedPreferences sharedreg=getApplicationContext().getSharedPreferences("spf",MODE_PRIVATE);
                                    SharedPreferences.Editor edt=sharedreg.edit();
                                    edt.putString("userid",userid);
                                    edt.apply();


                                }else{
                                    Toast.makeText(StudentRegister.this, "Not Success", Toast.LENGTH_SHORT).show();

                                }



                            }catch(Exception e){
                                Toast.makeText(StudentRegister.this, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        }
                    });



                }else {
                    Toast.makeText(StudentRegister.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}
