package com.example.kobayashishinji.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kobayashishinji.myapplication.data.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((Button)findViewById(R.id.button)).setOnClickListener(this);
        ((Button)findViewById(R.id.button2)).setOnClickListener(this);
        ((Button)findViewById(R.id.button3)).setOnClickListener(this);
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
        switch(v.getId()){
            case R.id.button: {
                User user = new User(1, "koba", "kobayashi.shinji");

                Bundle bundle = new Bundle();
                bundle.putParcelable("parcelable_data", user);

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), SubActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            }
            case R.id.button2: {
                // TODO: write
                //  echo "test data" >> /data/data/<package>/files/sample.txt
                FileOutputStream fos = null;
                String input = "test data \n";
                try {
                    fos = openFileOutput("sample.txt", Context.MODE_APPEND);
                    fos.write(input.getBytes());
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                break;
            }
            case R.id.button3: {
                byte[] buff = new byte[1024];
                FileInputStream fis = null;
                try {
                    fis = openFileInput("sample.txt");
                    fis.read(buff);
                    fis.close();

                    Log.i("koba", "read data: " + new String(buff, "UTF-8"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
}
