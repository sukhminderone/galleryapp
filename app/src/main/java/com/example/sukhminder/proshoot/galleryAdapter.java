package com.example.sukhminder.proshoot;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.FieldPosition;
import java.util.ArrayList;

public class galleryAdapter extends BaseAdapter {
    private Context pic;
    private int layout;
    private ArrayList<gallery> gallerylist;
    public galleryAdapter(Context context, int layout, ArrayList<gallery> gallerylist) {
        this.pic= context;
        this.layout = layout;
        this.gallerylist =gallerylist;
    }



    @Override
    public int getCount() {//DEFAULT METHODS
        return gallerylist.size();

    }

    @Override
    public Object getItem(int position) {
        return gallerylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class viewHolder{
        ImageView imageView;
        TextView textView;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row=view;
        viewHolder holder=new viewHolder();//holds reference to id;s of view resource
        if(row==null){
            LayoutInflater inflater=(LayoutInflater) pic.getSystemService(Context.LAYOUT_INFLATER_SERVICE);//initiating xml files to views
            row=inflater.inflate(layout,null);//dynamically creating view
            holder.textView=(TextView)row.findViewById(R.id.textView);
            holder.imageView=(ImageView)row.findViewById(R.id.imageView);
            row.setTag(holder);
        }//DEFAULT METHODS
        else{
            holder=(viewHolder)row.getTag();
        }
        gallery gridgallery=gallerylist.get(position);
        holder.textView.setText(gridgallery.getName());
        byte[] saveimage=gridgallery.getImage();
        Bitmap bitmap=BitmapFactory.decodeByteArray(saveimage,0,saveimage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }

}
