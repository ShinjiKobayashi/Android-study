package com.example.kobayashishinji.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("koba", "Hello Log Message");

        TextView textView = (TextView) findViewById(R.id.textview1);
        textView.setText("fugafuga");

        Button button = (Button) findViewById(R.id.button1);
/*
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("koba", "button clicked");
            }
        });
        */
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("koba", "X:" + event.getX() + "  Y:" + event.getY());

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("koba", "getAction() ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("koba", "getAction() ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d("koba", "getAction() ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d("koba", "getAction() ACTION_CANCEL");
                break;
        }
        return true;
    }
}
