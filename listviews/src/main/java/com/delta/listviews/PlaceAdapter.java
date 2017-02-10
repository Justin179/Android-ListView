package com.delta.listviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.delta.listviews.R.id.zipcodeTextView;

/**
 * Created by Justin179 on 2017/2/9.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    Context mContext;
    int mLayoutResourceId;
    Place[] mData = null;

    public PlaceAdapter(Context context, int resource, Place[] data) {
        super(context, resource, data);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.mData = data;

    }

    @Override
    public Place getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View row = convertView;
        // inflate the layout for a single row
        LayoutInflater inflater = LayoutInflater.from(this.mContext);
        row = inflater.inflate(mLayoutResourceId,parent,false);

        // get a reference to te different view elements we wish to update
        TextView nameView = (TextView) row.findViewById(R.id.nameTextView);
        TextView zipView = (TextView) row.findViewById(R.id.zipcodeTextView);
        ImageView imageView = (ImageView)  row.findViewById(R.id.imageView);

        // get the data from data Array
        Place place = mData[position];

        // setting the view to reflect the data
        nameView.setText(place.mNameOfPlace);
        zipView.setText(String.valueOf(place.mZipCode));

        int resId = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable",mContext.getPackageName());
        imageView.setImageResource(resId);

        //
        return row;
    }

    private static class PlaceHolder {
        TextView nameView;
        TextView zipView;
        ImageView imageView;
    }
}
