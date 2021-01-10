package fr.istic.mob.starv2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fr.istic.mob.starv2.R;
import fr.istic.mob.starv2.model.BusRoute;


/**
 * Sadou & Issa.
 */

public class BusListAdapter extends ArrayAdapter<BusRoute> {

public BusListAdapter(Context context, List<BusRoute> buss) {
        super(context, 0, buss);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.itenspinner,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
        viewHolder = new TweetViewHolder();
        viewHolder.name = (TextView) convertView.findViewById(R.id.textSpinner);
        viewHolder.back = (ImageView) convertView.findViewById(R.id.imagebackSpinner);
        convertView.setTag(viewHolder);
        }

        BusRoute busRoute = getItem(position);

        viewHolder.name.setText(busRoute.getRoute_short_name());
        viewHolder.name.setTextColor(Color.parseColor("#"+busRoute.getRoute_text_color())) ;
        viewHolder.back.setBackgroundColor(Color.parseColor("#"+busRoute.getRoute_color()));

        return convertView;
        }

private class TweetViewHolder{
    public TextView name;
    public ImageView back;
}
}