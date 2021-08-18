package com.example.cookit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {
    Button signout;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
         signout = findViewById(R.id.logout);

         signout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 FirebaseAuth.getInstance().signOut();
                 Intent intoMain = new Intent(HomeActivity.this, MainActivity.class);
                 startActivity(intoMain);
             }
         });
    }
}