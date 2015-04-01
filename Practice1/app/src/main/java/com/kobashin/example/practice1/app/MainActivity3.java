package com.kobashin.example.practice1.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class MainActivity3 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity3);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            // TODO: To check whether the EditText value are null.
            User user = new User();
            user.setName(((EditText) findViewById(R.id._userName)).getText().toString());
            user.setMailAddr(((EditText) findViewById(R.id._userMailAddr)).getText().toString());
            user.setPhone(((EditText) findViewById(R.id._userPhone)).getText().toString());
            user.setAddress(((EditText) findViewById(R.id._userAddr)).getText().toString());
            user.setMemo(((EditText) findViewById(R.id._userMemo)).getText().toString());
            user.setIconId(R.mipmap.ic_launcher);

            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putSerializable("user_data", user);
            intent.putExtras(bundle);

            setResult(RESULT_OK, intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
