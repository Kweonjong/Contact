package com.example.kjong_8gahnmf.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AddressAdatpter extends BaseAdapter {
    private List<AdressInfo> addressInfoList;
    private Context context;

    public AddressAdatpter(List<AdressInfo> addressInfoList, Context context) {
        this.addressInfoList = addressInfoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return addressInfoList.size();
    }

    @Override
    public Object getItem(int position) {
        return addressInfoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AddressHolder holder = null;
        if(convertView == null){
            holder = new AddressHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.addressitem, parent, false);

            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvPhone = (TextView) convertView.findViewById(R.id.tvPhone);


        }
        else{
            holder = (AddressHolder) convertView.getTag();
        }

        AdressInfo addAdressInfo = (AdressInfo) getItem(position);

        holder.tvName.setText(addAdressInfo.getName());
        holder.tvPhone.setText(addAdressInfo.getPhone());


        convertView.setTag(holder);
        return convertView;
    }


}
