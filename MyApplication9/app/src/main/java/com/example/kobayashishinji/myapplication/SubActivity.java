package com.example.kobayashishinji.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kobayashishinji.myapplication.data.User;
import com.example.kobayashishinji.myapplication.util.Utility;


public class SubActivity extends Activity implements View.OnClickListener {
    private User mUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        ((Button) findViewById(R.id.button1)).setOnClickListener(this);
        ((Button) findViewById(R.id.button2)).setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mUser = bundle.getParcelable("parcelable_data");
        Log.i("koba", "get data:" + mUser.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub, menu);
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

        switch (v.getId()) {
            case R.id.button1: {
                Utility.savePreferences(
                        getApplicationContext(),
                        mUser.getUserId(),
                        mUser.getUserName(),
                        mUser.getMail()
                );
                break;
            }
            case R.id.button2: {
                SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                int userId = preferences.getInt("userId", -1);
                String userName = preferences.getString("userName", "");
                String mailAddr = preferences.getString("mail", "");

                Log.i("koba", "userid:" + userId  + "  userName: " + userName + " mail:" + mailAddr);
                break;
            }
        }
    }
}
