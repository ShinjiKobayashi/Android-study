package com.kobashin.example.practice1.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity2 extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            User user = (User) bundle.getSerializable("user_data");
            ((TextView) findViewById(R.id._userName)).setText(user.getName());
            ((TextView) findViewById(R.id._userMailAddr)).setText(user.getMailAddr());
            ((TextView) findViewById(R.id._userAddr)).setText(user.getAddress());
            ((TextView) findViewById(R.id._userPhone)).setText(user.getPhone());
            ((TextView) findViewById(R.id._userMemo)).setText(user.getMemo());
            ((ImageView) findViewById(R.id.userIcon))
                    .setImageDrawable(getResources().getDrawable(user.getIconId()));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity2, menu);
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
}
