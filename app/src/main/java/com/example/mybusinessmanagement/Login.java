package com.example.mybusinessmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    public EditText Login_Mail_Enter, Login_Password_Enter;
    public Button Login_Button;

    public static String userID = "";

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login_Mail_Enter = findViewById(R.id.Login_Mail_Enter);
        Login_Password_Enter = findViewById(R.id.Login_Password_Enter);
        Login_Button = findViewById(R.id.Login_Button );

        auth = FirebaseAuth.getInstance();

        Login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_email = Login_Mail_Enter.getText().toString();
                String get_password = Login_Password_Enter.getText().toString();

                auth.signInWithEmailAndPassword(get_email,get_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Successfully Long", Toast.LENGTH_SHORT).show();

                            userID = auth.getUid();
                            userID = auth.getUid();
                            userID = auth.getUid();

                            Intent next =new Intent(Login.this,MainPage.class);
                            startActivity(next);
                        }
                        else
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }
}