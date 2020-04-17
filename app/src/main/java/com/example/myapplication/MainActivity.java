package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button UserButton, AdminButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserButton = (Button)findViewById(R.id.buttonUser);

        AdminButton = (Button)findViewById(R.id.buttonAdmin);


        UserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);


            }
        });


        AdminButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Opening new user registration activity using intent on button click.
                Intent intent = new Intent(MainActivity.this, AdLoginActivity.class);
                startActivity(intent);


            }
        });



    }



}




