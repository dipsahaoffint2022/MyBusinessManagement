package com.example.mybusinessmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;

    TextInputLayout Name, Mail, Password, Phone;
    TextInputEditText Register_Name_Enter, Register_Mail_Enter, Register_Code_Enter, Register_Phone_Enter;
    Button Register_Button;

    public static String link = "https://my-business-management-d6077-default-rtdb.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_register );

        Name = findViewById ( R.id.Name );
        Mail = findViewById ( R.id.Mail );
        Password = findViewById ( R.id.Password );
        Phone = findViewById ( R.id.phone );

        Register_Name_Enter = findViewById ( R.id.Register_Name_Enter );
        Register_Mail_Enter = findViewById ( R.id.Register_Mail_Enter );
        Register_Code_Enter = findViewById ( R.id.Register_Code_Enter );
        Register_Phone_Enter = findViewById ( R.id.Register_Phone_Enter );
        Register_Button = findViewById ( R.id.Register_Button );



        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance(link);

        Register_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_email = Register_Mail_Enter.getText().toString();
                String get_password = Register_Code_Enter.getText().toString();
                UserData ob = new UserData(Register_Name_Enter.getText().toString(), get_email, get_password, Register_Phone_Enter.getText().toString());
                auth.createUserWithEmailAndPassword(get_email,get_password).addOnCompleteListener(new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            String userid = auth.getUid();

                            database.getReference().child("Users").child(userid).setValue(ob);

                        }
                        else
                            Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });




    }
}