package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdminAdapter extends ArrayAdapter<Admin> {

    Context context;
    int resource;
    List<Admin> adminList;
    SQLiteDatabase sqLiteDatabase;


    public AdminAdapter(Context context, int resource, List<Admin> adminList,SQLiteDatabase sqLiteDatabase) {
        super(context, resource, adminList);

        this.context = context;
        this.resource = resource;
        this.adminList = adminList;
        this.sqLiteDatabase = sqLiteDatabase;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(resource, null);

        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewSym = view.findViewById(R.id.textViewSym);
        TextView textViewSymptom = view.findViewById(R.id.textViewSymptom);
        TextView textViewMed = view.findViewById(R.id.textViewMed);
        TextView textViewSelf = view.findViewById(R.id.textViewSelf);
        TextView textViewExpert = view.findViewById(R.id.textViewExpert);

        final Admin admin = adminList.get(position);

        textViewName.setText(admin.getName());
        textViewSym.setText(admin.getSymp());
        textViewSymptom.setText(admin.getSymptom());
        textViewMed.setText(admin.getMedicare());
        textViewSelf.setText(admin.getSelfcare());
        textViewExpert.setText(admin.getExpertname());


        view.findViewById(R.id.buttonDelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDisplay(admin);
            }
        });

        view.findViewById(R.id.buttonEditDisplay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                updateDisplay(admin);
            }
        });

        return view;
    }

    private void deleteDisplay(final Admin admin) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                String sql = "DELETE FROM Healthcare WHERE id = ?";
                sqLiteDatabase.execSQL(sql, new Integer[]{admin.getId()});
                loadAdminFromDatabaseagain();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void updateDisplay(final Admin admin) {

        AlertDialog.Builder  builder= new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.update_display, null);
        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextSym = view.findViewById(R.id.editTextSym);
        final EditText editTextSymptom = view.findViewById(R.id.editTextSymptom);
        final EditText editTextMed = view.findViewById(R.id.editTextMed);
        final EditText editTextSelf = view.findViewById(R.id.editTextSelf);
        final EditText editTextExpert = view.findViewById(R.id.editTextExpert);

        editTextName.setText(admin.getName());
        editTextSym.setText(admin.getSymp());
        editTextSymptom.setText(admin.getSymptom());
        editTextMed.setText(admin.getMedicare());
        editTextSelf.setText(admin.getSelfcare());
        editTextExpert.setText(admin.getExpertname());

        view.findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                String sql = "UPDATE Healthcare SET desname= ?,symptom = ?,symptom2 = ?,medicare = ?, selfcare = ?,expertname = ? WHERE id = ?";
                sqLiteDatabase.execSQL(sql, new String[]{name,symp,symptom,medicare,selfcare,expertname, String.valueOf(admin.getId()) });

                Toast.makeText(context, "Update Successfully", Toast.LENGTH_SHORT).show();

                loadAdminFromDatabaseagain();
                alertDialog.dismiss();
            }
        });

    }

    private void loadAdminFromDatabaseagain() {

        String sql = "SELECT * FROM Healthcare";

        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

        if (cursor.moveToFirst()){
            adminList.clear();

            do {
                adminList.add(new Admin(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(6)
                ));

            }while (cursor.moveToNext());

            notifyDataSetChanged();
        }
    }
}
