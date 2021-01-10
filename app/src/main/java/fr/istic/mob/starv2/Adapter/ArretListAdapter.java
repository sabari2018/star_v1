package fr.istic.mob.starv2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

import fr.istic.mob.starv2.R;
import fr.istic.mob.starv2.model.ArretTime;


/**
 * Sadou & Issa.
 */

public class ArretListAdapter extends ArrayAdapter<ArretTime> {

//tweets est la liste des models à afficher
public ArretListAdapter(Context context, List<ArretTime> arretTimes) {
        super(context, 0, arretTimes);
        }


        @Nullable
        @Override
        public ArretTime getItem(int position) {
                return super.getItem(position);
        }

        @Override
public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.arretpassage,parent, false);
        }

        TweetViewHolder viewHolder = (TweetViewHolder) convertView.getTag();
        if(viewHolder == null){
        viewHolder = new TweetViewHolder();
        viewHolder.name = (TextView) convertView.findViewById(R.id.textView7);
        viewHolder.time = (TextView) convertView.findViewById(R.id.textView11);
        convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        ArretTime arretTime = getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.name.setText(arretTime.nom);
        viewHolder.time.setText(arretTime.heur);

        return convertView;
        }

private class TweetViewHolder{
    public TextView name;
    public TextView time;
}
}