package com.example.storingdatausingfile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    Button saveBtn;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextId);
        saveBtn = findViewById(R.id.saveBtnId);

        readFromFile();
        //  getData();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                if (text != null) {
                    writeToFile(text);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter some data ", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    public void writeToFile(String text) {
        try {
            FileOutputStream fileOutputStream = openFileOutput("note.txt", Context.MODE_PRIVATE);

            try {
                fileOutputStream.write(text.getBytes());
                fileOutputStream.close();

                Toast.makeText(this, "Data is saved", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void readFromFile() {
        try {
            FileInputStream fileInputStream = openFileInput("note.txt");

            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            StringBuffer stringBuffer = new StringBuffer();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");

            }
            editText.setText(stringBuffer);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void getData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("Note", Context.MODE_PRIVATE);
//        if (sharedPreferences.contains("note")) {
//            String str = sharedPreferences.getString("note", " ");
//            editText.setText(str);
//        }
//    }
//
//    private void StoreData() {
//        SharedPreferences sharedPreferences = getSharedPreferences("Note", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("note", editText.getText().toString());
//        editor.commit();
//    }
}