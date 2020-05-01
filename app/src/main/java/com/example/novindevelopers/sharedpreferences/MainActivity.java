package com.example.novindevelopers.sharedpreferences;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSeekBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText editText;
    private TextView textView;
    private Button btnshared, btnred, btngreen;
    private AppCompatSeekBar seekBar;
    private Toolbar toolbar;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        textView = (TextView) findViewById(R.id.textname);
        editText = (EditText) findViewById(R.id.edName);
        btnshared = (Button) findViewById(R.id.btnShared);
        btngreen = (Button) findViewById(R.id.btngreen);
        btnred = (Button) findViewById(R.id.btnred);
        seekBar = (AppCompatSeekBar) findViewById(R.id.seekbar);
        linearLayout = (LinearLayout) findViewById(R.id.layout);

        getColor();
        seekBAR();
        SharedPreferences preferences = getSharedPreferences("name", MODE_PRIVATE);
        textView.setText(preferences.getString("text", ""));

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
            {
                SharedPreferences changeprogress = getSharedPreferences("progress",MODE_PRIVATE);
                SharedPreferences.Editor editor = changeprogress.edit();
                editor.putInt("seekbar",progress);
                editor.commit();
                textView.setTextSize((float)progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }
        });

        btnshared.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences preferences = getSharedPreferences("name", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("text", editText.getText().toString());
                editor.commit();
                textView.setText(editText.getText().toString());
            }
        });
        btnred.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences changeTheme = getSharedPreferences("theme",MODE_PRIVATE);
                SharedPreferences.Editor editor = changeTheme.edit();
                editor.putBoolean("color",true);
                editor.commit();
                recreate();
            }
        });
        btngreen.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SharedPreferences changeTheme = getSharedPreferences("theme",MODE_PRIVATE);
                SharedPreferences.Editor editor = changeTheme.edit();
                editor.putBoolean("color",false);
                editor.commit();
                recreate();
            }
        });

    }
    public void getColor()
    {
        SharedPreferences changeTheme = getSharedPreferences("theme",MODE_PRIVATE);
        boolean b = changeTheme.getBoolean("color",false);

        if (b)
        {
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.redPrimary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.redDark));
            }
            linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.light));
          
        }
        else
        {
            toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
            }
            linearLayout.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.with));
        }
    }

    public void seekBAR()
    {
        SharedPreferences changeprogress = getSharedPreferences("progress",MODE_PRIVATE);
        int i =changeprogress.getInt("seekbar",0);
        int size =changeprogress.getInt("seekbar",25);
        textView.setTextSize((float)size);
        seekBar.setProgress(i);
    }
}
