package com.delta.listviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        PlaceHolder holder = null;

        if(row==null){
            // inflate the layout for a single row
            LayoutInflater inflater = LayoutInflater.from(this.mContext);
            row = inflater.inflate(mLayoutResourceId,parent,false);

            holder = new PlaceHolder();
            // get a reference to te different view elements we wish to update
            holder.nameView = (TextView) row.findViewById(R.id.nameTextView);
            holder.zipView = (TextView) row.findViewById(R.id.zipcodeTextView);
            holder.imageView = (ImageView)  row.findViewById(R.id.imageView);

            // the magic line
            row.setTag(holder);
        } else {
            // otherwise use an existing one (直接用既有的，速度就差在這裡)
            holder = (PlaceHolder) row.getTag();
        }

        // getting the data from data array
        Place place = mData[position];

        // setup and reuse the same listener for each row
        holder.imageView.setOnClickListener(PopupListener);
        Integer rowPosition = position;
        holder.imageView.setTag(rowPosition);

        // setting the view to reflect the data we need to display
        holder.nameView.setText(place.mNameOfPlace);
        holder.zipView.setText(String.valueOf(place.mZipCode));
        // for getting the image
        int resID = mContext.getResources().getIdentifier(place.mNameOfImage,"drawable", mContext.getPackageName());
        holder.imageView.setImageResource(resID);

        //
        return row;
    }

    View.OnClickListener PopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer viewPosition = (Integer) v.getTag();
            Place p = mData[viewPosition];
            Toast.makeText(getContext(),p.mPopup,Toast.LENGTH_SHORT).show();
        }
    };

    private static class PlaceHolder {
        TextView nameView;
        TextView zipView;
        ImageView imageView;
    }
}
