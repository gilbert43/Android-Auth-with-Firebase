package com.example.cookit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    TextInputEditText emailId,password,cpassword;
    Button btnSignUp;
    TextView SignIn;
    FirebaseAuth mFirebaseAuth;
    ProgressDialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.txtEmailSignUp);
        password = findViewById(R.id.txtPasswordSignUp);
        cpassword = findViewById(R.id.txtConfirmSignUp);
        btnSignUp = findViewById(R.id.btnSignUp);
        SignIn = findViewById(R.id.txtSignIn);
        dialog = new ProgressDialog(MainActivity.this);
        dialog.setCancelable(false);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String cpwd = cpassword.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("please enter email");
                    emailId.requestFocus();
                }
                else if(pwd.isEmpty()){
                    password.setError("please enter password");
                    password.requestFocus();
                }
                else if(!(cpwd.equals(pwd))){
                    cpassword.setError("Passwords not matching");
                    cpassword.requestFocus();
                }
                else if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pwd.isEmpty())){
                    dialog.setMessage("Registering");


                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Sign Up Unsuccessful",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                dialog.show();
                                startActivity(new Intent(MainActivity.this,HomeActivity.class));
                                dialog.dismiss();

                            }
                        }
                    });
                    dialog.dismiss();
                }
                else{
                    Toast.makeText(MainActivity.this,"Eoor occured",Toast.LENGTH_SHORT).show();
                }


            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}