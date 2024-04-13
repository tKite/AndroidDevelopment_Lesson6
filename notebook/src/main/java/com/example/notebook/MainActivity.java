package com.example.notebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.notebook.databinding.ActivityMainBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        if ((isExternalStorageReadable() || isExternalStorageWritable()) == false) {
            ActivityCompat.requestPermissions(this,	new	String[]
                            {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    200);
        }
    }
    /* Проверяем хранилище на доступность чтения и записи*/
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Проверяем внешнее хранилище на доступность чтения */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
    public	void saveFiles(View view)	{
        File path =	Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file =	new	File(path,	binding.fileName.getText().toString() + ".txt");
        try	{
            FileOutputStream fileOutputStream =	new	FileOutputStream(file.getAbsoluteFile());
            OutputStreamWriter output =	new	OutputStreamWriter(fileOutputStream);
            //	Запись строки в файл
            output.write(binding.C.getText().toString());
            //	Закрытие потока записи
            output.close();
        }	catch(IOException e)	{
            Log.w("ExternalStorage",	"Error	writing	"	+	file,	e);
        }
        Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
    }
    public	void loadFiles(View view)	{
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File file =	new	File(path,	binding.fileName.getText().toString() + ".txt");
        try	{
            FileInputStream fileInputStream	= new FileInputStream(file.getAbsoluteFile());
            InputStreamReader inputStreamReader	= new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            List<String> lines	= new ArrayList<String>();
            BufferedReader reader	=	new	BufferedReader(inputStreamReader);
            String	line =	reader.readLine();
            while(line	!=	null)	{
                lines.add(line);
                line = reader.readLine();
            }
            String listString = String.join("\n", lines);
            binding.C.setText(listString);
            Toast.makeText(this, "Файл загружен", Toast.LENGTH_SHORT).show();
            Log.w("ExternalStorage", String.format("Read	from	file	%s	successful",	lines.toString()));
        }	catch	(Exception	e)	{
            Log.w("ExternalStorage", String.format("Read	from	file	%s	failed",	e.getMessage()));
        }
    }

}