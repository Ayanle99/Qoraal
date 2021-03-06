package org.ayanledev.qoraal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNoteActivity extends AppCompatActivity {

    private Button addBtn;
    private EditText title, content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        addBtn = findViewById(R.id.addNoteBtn);
        title = findViewById(R.id.title_editext);
        content = findViewById(R.id.content_editext);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String titleContent = title.getText().toString();
                String contentText = content.getText().toString();

                if (TextUtils.isEmpty(titleContent)){

                    Toast.makeText(AddNoteActivity.this,
                            "Ciwaanku waa banaanyahay.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(contentText)){

                    Toast.makeText(AddNoteActivity.this,
                            "Waraaqdu waa madhan tahay.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // save note

                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
                Date date = new Date();
                Log.d("TAG", "onClick: "+dateFormat.format(date));
                saveNote(titleContent, contentText, String.valueOf(dateFormat.format(date)));


            }
        });
    }

    private void saveNote(String t, String c, String d) {


        DatabaseHelper helper = new DatabaseHelper(this);
        helper.addNote(t, c, d);

    }


}