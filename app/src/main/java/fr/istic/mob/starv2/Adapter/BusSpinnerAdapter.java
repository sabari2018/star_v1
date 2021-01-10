package fr.istic.mob.starv2.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fr.istic.mob.starv2.R;
import fr.istic.mob.starv2.model.BusRoute;


/**
 * Sadou & Issa.
 */

public class BusSpinnerAdapter extends ArrayAdapter<BusRoute> {

    private final LayoutInflater mInflater;
    private final Context mContext;
    private final List<BusRoute> busLists;
    private final int mResource;
    BusRoute busData;

    public BusSpinnerAdapter(@NonNull Context context, @LayoutRes int resource,
                             @NonNull List busList) {
        super(context, resource, 0, busList);

        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = resource;
        busLists = busList;
    }

    @Nullable
    @Override
    public BusRoute getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView,
                                @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    @Override
    public @NonNull
    View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createItemView(position, convertView, parent);
    }

    private View createItemView(int position, View convertView, ViewGroup parent){
        View view;

        if (convertView == null) {
            view = mInflater.inflate(mResource, parent, false);
        } else {
            view = convertView;
        }



        TextView name = (TextView) view.findViewById(R.id.textSpinner);
        ImageView imageback = (ImageView) view.findViewById(R.id.imagebackSpinner);

        busData = busLists.get(position);

        name.setText(busData.getRoute_short_name());
        name.setTextColor(Color.parseColor("#"+busData.getRoute_text_color())) ;
        imageback.setBackgroundColor(Color.parseColor("#"+busData.getRoute_color()));

        return view;
    }
}