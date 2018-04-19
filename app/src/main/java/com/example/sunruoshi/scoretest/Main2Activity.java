package com.example.sunruoshi.scoretest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Main2Activity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button btn_login, btn_register;
    private FirebaseAuth firebaseAuth;
    private String TAG = "Database";
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        username = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        btn_register = findViewById(R.id.btn_register);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();



        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = username.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getBaseContext(), "Nope", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(getBaseContext(), "Nope", Toast.LENGTH_LONG).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getBaseContext(), "Registered Successfully", Toast.LENGTH_LONG).show();

                                    currentUser = FirebaseAuth.getInstance().getCurrentUser();
                                    User newUser = new User(email, 0);
                                    myRef.child("Users").child(currentUser.getUid()).setValue(newUser);
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    i.putExtra("name", email);
                                    startActivity(i);
                                }else{
                                    Toast.makeText(getBaseContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = username.getText().toString().trim();
                String password1 = password.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getBaseContext(), "Nope", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password1)){
                    Toast.makeText(getBaseContext(), "Nope", Toast.LENGTH_LONG).show();
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(email, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getBaseContext(), "login Successfully", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                    i.putExtra("name", username.getText().toString());
                                    startActivity(i);
                                }else{
                                    Toast.makeText(getBaseContext(), "Wrong password or username", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        });

    }


/***
    public void Onclick(View view) {
        if (view == btn_login) {
            if (username.getText().toString().equals("scoretest") && password.getText().toString().equals("591")) {
                Intent intent = new Intent();
                intent.setClass(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_LONG).show();
                return;
            }
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.putExtra("name", username.getText().toString());
            startActivity(i);
        } else if (view == btn_register) {
            String email = username.getText().toString();
            String password1 = password.getText().toString();

            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password1)) {

            } else {
                firebaseAuth.createUserWithEmailAndPassword(email, password1)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getBaseContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getBaseContext(), "Registration Failed", Toast.LENGTH_LONG).show();
                                }

                            }
                        });
            }
        }
    }
    ***/
}
