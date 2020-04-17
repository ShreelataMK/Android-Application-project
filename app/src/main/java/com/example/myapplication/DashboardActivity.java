package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.myapplication.AdminActivity.DATABASE_NAME;

public class DashboardActivity extends AppCompatActivity {

    String EmailHolder;
    TextView Email;
    Button LogOUT;

    EditText InputPatientName, InputTypeSymp1, ImputTypeSymp2;
    Button buttonResult;
    List<Admin> adminList;
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    String PatientNameHolder, PatientAgeHolder, PatientDescriptionHolder;
    Boolean EditTextEmptyHold;
    String SQLiteDataBaseQueryHolder;
    SQLiteHelper sqLiteHelper;
    TextView DisplayName,DisplaySymp,DisplaySymp2,DisplayMed,DisplaySelf,DisplayExpert,AppDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sqLiteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        InputTypeSymp1 = (EditText) findViewById(R.id.editTextsymp);

        DisplayName = (TextView)findViewById(R.id.textViewName);
        DisplaySymp =(TextView)findViewById(R.id.textViewSymp);
        DisplaySymp2 = (TextView)findViewById(R.id.textViewSymp2);
        DisplayMed =(TextView)findViewById(R.id.textViewMed);
        DisplaySelf = (TextView)findViewById(R.id.textViewSelf);
        DisplayExpert = (TextView)findViewById(R.id.textViewExpert);

        AppDisplay = (TextView)findViewById(R.id.textViewappt);


        buttonResult = (Button) findViewById(R.id.buttonResult);
        buttonResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String symptom1 = InputTypeSymp1.getText().toString().trim();
                Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Healthcare where symptom='"+symptom1+"'", null);

                if (cursor.getCount() == 0) {
                    Toast.makeText(getApplicationContext(), "No Data found", Toast.LENGTH_LONG).show();
                    AppDisplay.setVisibility(View.VISIBLE);

                    AppDisplay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(DashboardActivity.this,UserActivity.class);
                            startActivity(intent);
                        }
                    });
                    return;
                }

               // StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()) {
                    /*buffer.append("Name" + cursor.getString(0) + "\n");
                    buffer.append("symptom" + cursor.getString(1) + "\n");
                    buffer.append("Symptoms 2" + cursor.getString(2) + "\n");
                    buffer.append("Medicare" + cursor.getString(3) + "\n");
                    buffer.append("Selfcare" + cursor.getString(4) + "\n");
                    buffer.append("Expert" + cursor.getString(5) + "\n");*/

                    String Name = cursor.getString(1);
                    String Symptom = cursor.getString(2);
                    String Symptom2 = cursor.getString(3);
                    String Medicare = cursor.getString(4);
                    String Selfcare = cursor.getString(5);
                    String Expert = cursor.getString(6);

                    DisplayName.setText(Name);
                    DisplaySymp.setText(Symptom);
                    DisplaySymp2.setText(Symptom2);
                    DisplayMed.setText(Medicare);
                    DisplaySelf.setText(Selfcare);
                    DisplayExpert.setText(Expert);


                    DisplayName.setVisibility(View.VISIBLE);


                    DisplaySymp.setVisibility(View.VISIBLE);


                    DisplaySymp2.setVisibility(View.VISIBLE);


                    DisplayMed.setVisibility(View.VISIBLE);


                    DisplaySelf.setVisibility(View.VISIBLE);


                    DisplayExpert.setVisibility(View.VISIBLE);

                }
                //Toast.makeText(getApplicationContext(), buffer, Toast.LENGTH_SHORT).show();

            }
        });



        /*<............>*/

        Email = (TextView) findViewById(R.id.textView1);
        LogOUT = (Button) findViewById(R.id.button1);

        sqLiteHelper = new SQLiteHelper(this);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
        EmailHolder = intent.getStringExtra(LoginActivity.UserEmail);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString() + EmailHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(DashboardActivity.this, "Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });


    }
}

    /*@Override
    public void onClick(View v) {

        String symptom1 = InputTypeSymp1.getText().toString().trim();

        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Healthcare",null);

        if (cursor.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"No Data found",Toast.LENGTH_LONG).show();
            return;
        }

            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()){
                buffer.append("Id"+cursor.getString(0)+"\n");
                buffer.append("Name"+cursor.getString(1)+"\n");
                buffer.append("symptom"+cursor.getString(2)+"\n");
                buffer.append("Symptoms 2"+cursor.getString(3)+"\n");
                buffer.append("Medicare"+cursor.getString(4)+"\n");
                buffer.append("Selfcare"+cursor.getString(5)+"\n");
                buffer.append("Expert"+cursor.getString(6)+"\n");
            }

            Toast.makeText(getApplicationContext(),"Result \n"+buffer.toString(),Toast.LENGTH_SHORT).show();
        }

}*/

    /*private void LoadData() {

        String symptom1 = InputTypeSymp1.getText().toString().trim();
        String symptom2 = ImputTypeSymp2.getText().toString().trim();

       if (symptom1.isEmpty()){
            InputTypeSymp1.setError("Symptoms 1 cannot be empty");
        }

        if (symptom2.isEmpty()){
            ImputTypeSymp2.setError("Symptoms 1 cannot be empty");
        }

        String sql = "SELECT * FROM Healthcare";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        System.out.println(sql);
    }*/




