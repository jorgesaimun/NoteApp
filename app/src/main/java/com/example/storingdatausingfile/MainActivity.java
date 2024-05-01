package com.example.storingdatausingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button saveBtn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextId);
        saveBtn = findViewById(R.id.saveBtnId);

        getData();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoreData();

               getData();
            }

        });
    }

    public void getData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Note", Context.MODE_PRIVATE);
        if (sharedPreferences.contains("note")) {
            String str = sharedPreferences.getString("note", " ");
            editText.setText(str);
        }
    }

    private void StoreData() {
        SharedPreferences sharedPreferences = getSharedPreferences("Note", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("note", editText.getText().toString());
        editor.commit();
    }
}