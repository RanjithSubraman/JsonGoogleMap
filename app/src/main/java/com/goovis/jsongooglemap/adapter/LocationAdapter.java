package com.goovis.jsongooglemap.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.goovis.jsongooglemap.MainActivity;
import com.goovis.jsongooglemap.R;
import com.goovis.jsongooglemap.bean.Location;

import java.util.List;

/**
 * Created by Ranjith Subramaniam on 3/30/17.
 */

public class LocationAdapter extends BaseAdapter {
    private static LayoutInflater inflater;
    private Context context;
    private List<Location> locationList;

    public LocationAdapter(MainActivity mainActivity, List<Location> locationList) {
        this.locationList = locationList;
        context = mainActivity;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return locationList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        View listRowView = inflater.inflate(R.layout.list_item, null);

        CustomViewHolder customViewHolder = new CustomViewHolder();
        customViewHolder.nameTextView = (TextView) listRowView.findViewById(R.id.name);
        customViewHolder.addressTextView = (TextView) listRowView.findViewById(R.id.address);
        customViewHolder.timeTextView = (TextView) listRowView.findViewById(R.id.time);

        customViewHolder.nameTextView.setText(locationList.get(position).getName());
        customViewHolder.addressTextView.setText(locationList.get(position).getAddress());
        customViewHolder.timeTextView.setText(locationList.get(position).getTime());

        return listRowView;
    }

    public class CustomViewHolder {
        TextView nameTextView;
        TextView addressTextView;
        TextView timeTextView;
    }
}
