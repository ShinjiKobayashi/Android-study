package com.kobashin.example.practice1.app;

import com.kobashin.example.practice1.app.utils.Utility;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener,
        View.OnClickListener, ListItemAdapter.OnCheckboxClickListener {

    private final int ID_REQUEST_ADD = 0x01;

    private ArrayList<User> mUserList = new ArrayList<>();

    private ListItemAdapter adapter;

    private SparseArray<Boolean> mCheckedList = new SparseArray<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String ids = Utility.getUserDataInString(getApplicationContext(), "userIds");
        if (ids.equals("")) {
            User user1 = new User();
            user1.setIconId(R.mipmap.ic_launcher);
            user1.setName("Shinji Kobayashi");
            user1.setMailAddr("hogehoge@kobashin.sample.com");
            user1.setAddress("1-2-3-100 Osaka Japan");
            user1.setPhone("+8190xxxxxxxx");
            user1.setMemo("hogehoge");
            user1.setId(1);
            mUserList.add(user1);
            Utility.saveUserToPreferences(this, user1);
        } else {
            String idArray[] = ids.split(",");
            for (String id : idArray) {
                if(id.equals(""))
                    continue;
                mUserList.add(Utility.getUserData(getApplicationContext(), id));
            }
        }

        adapter = new ListItemAdapter(
                this, R.layout.list_items, mUserList
        );
        adapter.setOnCheckboxClickListener(this);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        ((Button) findViewById(R.id.btn_add)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_del)).setOnClickListener(this);
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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        User user = (User) parent.getItemAtPosition(position);
        Log.i("koba", "click user" + user.toString());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user_data", user);
        intent.putExtras(bundle);
        intent.setClass(this, MainActivity2.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ID_REQUEST_ADD) {
            switch (resultCode) {
                case RESULT_OK:
                    Bundle bundle = data.getExtras();
                    User user = (User) bundle.getSerializable("user_data");
                    user.setId(getEmptyId());
                    Utility.saveUserToPreferences(getApplicationContext(), user);
                    mUserList.add(user);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add: {
                Intent intent = new Intent();
                intent.setClass(this, MainActivity3.class);
                startActivityForResult(intent, ID_REQUEST_ADD);
                break;
            }
            case R.id.btn_del: {
                Log.i("koba", "del list:" + mCheckedList.toString());
                for (int i = 0; i < mCheckedList.size(); i++) {
                    if (mCheckedList.valueAt(i)) {
                        int key = mCheckedList.keyAt(i);
                        for (User user : mUserList) {
                            if (user.getId() == key) {
                                mUserList.remove(user);
                                break;
                            }
                        }
                        Utility.deleteUserIdInPreferences(getApplicationContext(),
                                String.valueOf(key));
                    }
                }
                adapter.notifyDataSetChanged();
                break;
            }
            default:
                break;
        }
    }

    private int getEmptyId() {
        String[] ids = Utility.getUserDataInString(getApplicationContext(), "userIds").split(",");

        SparseArray<Boolean> sparseArray = new SparseArray<>();
        for (String id : ids) {
            sparseArray.put(Integer.parseInt(id), true);
        }

        int cnt = 1;
        while (true) {
            if (sparseArray.indexOfKey(cnt) < 0) {
                return cnt;
            }
            cnt++;
        }
    }

    @Override
    public void onChenged(boolean checked, int userId) {
        mCheckedList.append(userId, checked);
        for (User user : mUserList) {
            if (user.getId() == userId) {
                user.setIsChecked(checked);
                break;
            }
        }
    }
}
