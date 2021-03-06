package org.ayanledev.qoraal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateActivity extends AppCompatActivity {

    private Button updateBtn,deleteBtn;

    private EditText title, content;


    String id, the_title, the_content, the_time;

    DatabaseHelper helper;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        helper = new DatabaseHelper(UpdateActivity.this);

        updateBtn = findViewById(R.id.updateNoteBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        title = findViewById(R.id.update_title_editext);
        content = findViewById(R.id.update_content_editext);


        getIntentData();

        title.setText(the_title);
        content.setText(the_content);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                Date date = new Date();

                String formatedDate = "Tifaftirtay " + date;

                helper.updateData(id, title.getText().toString(),
                        content.getText().toString(),
                        String.valueOf(formatedDate));
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmDeletion(id);
            }
        });
    }

    private void confirmDeletion(final String id) {


        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Masax?");
        alert.setMessage("Ma rabtaa inaad masaxdo [ " + the_title + " ] ?");

        alert.setPositiveButton("Haa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String theID = id;
                helper.deleteData(theID);
                Toast.makeText(UpdateActivity.this,
                        "Waad masaxday!", Toast.LENGTH_SHORT).show();

            }
        });
        alert.setNegativeButton("Maya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.create().show();;


    }

    private void updateNote(String id, String Title, String Content, String formatedDate) {

        try {
            helper.updateData(id, Title, Content, formatedDate);
            Log.d("TAG", "updateNote: "+id);

        }catch (Exception e){
            Log.d("TAG", "updateNote: "+e.toString());
            Toast.makeText(this,
                    "An error occured.", Toast.LENGTH_SHORT).show();
        }
    }

    public void getIntentData() {

        if (getIntent().getStringExtra("id") != null){
            id = getIntent().getStringExtra("id");
        }
        if (getIntent().getStringExtra("title") != null){
            the_title = getIntent().getStringExtra("title");}

        if (getIntent().getStringExtra("content") != null) {
            the_content = getIntent().getStringExtra("content");

        }
        if (getIntent().getStringExtra("date") != null) {
            the_time = getIntent().getStringExtra("date");
        }


    }
}