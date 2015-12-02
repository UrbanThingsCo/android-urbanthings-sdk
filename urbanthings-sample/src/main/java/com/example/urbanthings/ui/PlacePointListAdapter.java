package com.example.urbanthings.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.urbanthings.R;

import io.urbanthings.datamodel.PlacePoint;
import io.urbanthings.helpers.CoreUtils;

import java.util.List;

public class PlacePointListAdapter extends ArrayAdapter<PlacePoint> {
    public PlacePointListAdapter(Context context, List<PlacePoint> objects) {
        super(context, -1, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        final View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.place_point_list_item, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        final PlacePoint placePoint = getItem(position);
        // update the view (via ViewHolder) for this PlacePoint (remember cell may be being re-used)
        holder.titleTextView.setText(CoreUtils.emptyStringIfNull(placePoint.name));
        holder.subtitleTextView.setText(placePoint.primaryCode);

        return view;
    }

    public static class ViewHolder {
        public final TextView titleTextView;
        public final TextView subtitleTextView;

        public ViewHolder(View view) {
            titleTextView = (TextView) view.findViewById(R.id.titleTextView);
            subtitleTextView = (TextView) view.findViewById(R.id.subtitleTextView);
        }
    }
}
