package com.example.kjong_8gahnmf.contact;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.ListView;
import java.util.List;


public class AddressListActivity extends AppCompatActivity {

    private ListView addressList;

    private  DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);

        databaseHelper = new DatabaseHelper(this);

        setTitle("주소록");
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        addressList = (ListView) findViewById(R.id.addressList);

        List<AdressInfo> addressInfoList = databaseHelper.getAllAddress();
        addressList.setAdapter(new AddressAdatpter(addressInfoList, this));

    }
}
