package com.example.rumireakirillovalesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.system.Int64Ref;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rumireakirillovalesson6.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private String groupNum;
    private Integer yourNum;
    private String yourFilm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
    public void onClicked(View view){
        groupNum = binding.editText.getText().toString();
        yourNum = Integer.parseInt(binding.editText2.getText().toString());
        yourFilm = binding.editText3.getText().toString();
        SharedPreferences sharedPref = getSharedPreferences("mirea_settings",	Context.MODE_PRIVATE);
        SharedPreferences.Editor editor	= sharedPref.edit();
        editor.putString("GROUP", groupNum);
        editor.putInt("NUMBER", yourNum);
        editor.putString("FILM/SERIES", yourFilm);
        editor.apply();
        Toast.makeText(this, "Saved settings", Toast.LENGTH_SHORT).show();
    }
}