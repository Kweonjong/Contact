package com.example.kjong_8gahnmf.contact;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static SQLiteDatabase db;
    private Context context;

    public DatabaseHelper(Context context){
        this.context = context;
        if(DatabaseHelper.db == null){
            this.db = context.openOrCreateDatabase("address",context.MODE_PRIVATE, null);
            this.createTable();
        }

        this.createTable();
    }

    public void chaneContext(Context context){
        this.context = context;
    }

    private void createTable() {
        if (!isExistsTable()) {
            String query = " CREATE TABLE ADDRESS ( ";
            query += " _ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
            query += " NAME VARCHAR, ";
            query += " PHONE VARCHAR, ";

            db.execSQL(query);
        }

    }

    //테이블이 존재하는지 안하는지
    private boolean isExistsTable(){
        String query = " SELECT COUNT(NAME) " +
                " FROM sqlite_master  " +
                " WHERE type='table' " +
                " AND name='ADDRESS'; ";

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToNext()){
            return cursor.getInt(0) > 0;
        }
        return false;
    }

    public void inserAddressData(AdressInfo addressInfo){
        String query = context.getString(android.R.string.INSERT_QUERY);
        query = String.format(query, "'" + addressInfo.getName() + "'",  "'" + addressInfo.getPhone() + "'");

        Log.d("QUERY", query);
        db.execSQL(query);
    }
    public List<AdressInfo> getAllAddress(){
        String query = context.getString(android.R.string.SELECT_QUERY);

        Cursor cursor = db.rawQuery(query, null);
        List<AdressInfo> addressList = new ArrayList<AdressInfo>();
        AdressInfo addressInfo = null;

        while (cursor.moveToNext()){
            addressInfo = new AdressInfo();
            addressInfo.setName(cursor.getString(0));
            addressInfo.setPhone(cursor.getString(1));

            addressList.add(addressInfo);
        }
        return addressList;
    }

}
//http://souning.tistory.com/61