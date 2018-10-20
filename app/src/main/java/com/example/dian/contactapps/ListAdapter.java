package com.example.dian.contactapps;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dian.contactapps.Entity.ProfileArray;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    Context mContext;
    ArrayList<ProfileArray> itemsProfile = new ArrayList<ProfileArray>();

    //public constructor
    public ListAdapter(Context context, ArrayList<ProfileArray> items) {
        this.mContext = context;
        this.itemsProfile = items;
    }

    @Override
    public int getCount() {
        return itemsProfile.size();
    }

    @Override
    public Object getItem(int position) {
        return itemsProfile.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        ProfileArray currentItem = itemsProfile.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.star = (ImageView) convertView.findViewById(R.id.star);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(currentItem.getFirstname()+" "+currentItem.getLastname());

        String url = currentItem.getAvatar();
        Boolean favorite = currentItem.getIsfavorite();
        if(favorite){
            viewHolder.star.setVisibility(View.VISIBLE);
        }else {
            viewHolder.star.setVisibility(View.INVISIBLE);
        }
        if(url.equalsIgnoreCase("")){
            Glide.with(mContext).load("https://www.shareicon.net/data/512x512/2016/09/15/829459_man_512x512.png")
                    //.placeholder(R.drawable.loading_spinner)
                    .into(viewHolder.image);
        }else{
            Glide.with(mContext).load(url)
                    //.placeholder(R.drawable.loading_spinner)
                    .into(viewHolder.image);
        }

        return convertView;
    }
    private class ViewHolder {
        TextView name;
        ImageView image,star;
    }
}
