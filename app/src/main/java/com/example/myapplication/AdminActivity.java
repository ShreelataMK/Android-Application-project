package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

      public static String DATABASE_NAME="AndroidJSonDataBase";
      SQLiteDatabase sqLiteDatabase;


      EditText editTextName,editTextSym,editTextSymptom,editTextMed,editTextSelf,editTextExpert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //sqLiteHelper = new SQLiteHelper(this);

        sqLiteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();

        editTextName = (EditText)findViewById(R.id.editTextName);
        editTextSym = (EditText)findViewById(R.id.editTextSym);
        editTextSymptom = (EditText)findViewById(R.id.editTextSymptom);
        editTextMed = (EditText)findViewById(R.id.editTextMed);
        editTextSelf = (EditText)findViewById(R.id.editTextMed);
        editTextExpert =(EditText)findViewById(R.id.editTextExpert);

        findViewById(R.id.buttonSubmit).setOnClickListener(this);
        findViewById(R.id.textViewdisp).setOnClickListener(this);

    }

    private void createTable(){



        String sql = "CREATE TABLE IF NOT EXISTS Healthcare (\n " +
                "id INTEGER NOT NULL CONSTRAINT AUTO_INCREMENT PRIMARY KEY,\n" +
                " desname VARCHAR(30) NOT NULL,\n " +
                "symptom VARCHAR(30) NOT NULL,\n " +
                "symptom2 VARCHAR(30) NOT NULL,\n " +
                "medicare VARCHAR(50),\n " +
                "selfcare VARCHAR(50),\n " +
                "expertname VARCHAR(50)\n" +
                " )";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.buttonSubmit:
                addData();
                break;
            case R.id.textViewdisp:

                startActivity(new Intent(this,DisplayActivity.class));
                break;
        }

    }

    private void addData() {

        String name = editTextName.getText().toString().trim();
        String symp = editTextSym.getText().toString().trim();
        String symptom = editTextSymptom.getText().toString().trim();
        String medicare = editTextMed.getText().toString().trim();
        String selfcare = editTextSelf.getText().toString().trim();
        String expertname = editTextExpert.getText().toString().trim();

        if (name.isEmpty()){
            editTextName.setError("Name cannot be empty");
        }

        if (symp.isEmpty()){
            editTextSym.setError("Symptoms 1 cannot be empty");
        }

        if (symptom.isEmpty()){
            editTextSymptom.setError("Symptom 2 canot be empty");
        }

        if (medicare.isEmpty()){
            editTextMed.setError("Medicare cannot be empty");
        }

        if (selfcare.isEmpty()){
            editTextSelf.setError("Selfcare cannot be empty");
        }

        if (expertname.isEmpty()){
            editTextSelf.setError("Selfcare cannot be empty");
        }


        String sql = "INSERT INTO Healthcare(desname,symptom,symptom2,medicare,selfcare,expertname)" +
                "VALUES (?,?,?,?,?,?)";

        sqLiteDatabase.execSQL(sql, new String[]{name, symp, symptom, medicare, selfcare, expertname});
        Toast.makeText(this, "Employee Added Successfully", Toast.LENGTH_SHORT).show();

    }
}
