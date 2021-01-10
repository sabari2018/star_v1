package fr.istic.mob.starv2;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import fr.istic.mob.starv2.Adapter.BusAdapterList;


/**
 * Sadou & Issa.
 */

public class BusPicker extends DialogFragment {
    ListView myList ;
    FragmentActivity onefragA ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.listbus, container,false);
        getDialog().setTitle("Bus List");
        myList = (ListView) rootView.findViewById(R.id.listViewbus) ;

        final BusAdapterList adapter = new BusAdapterList(this.getContext(),   FragmentOne.getBusRoute() );
        myList.setAdapter(adapter);
         onefragA =   this.getActivity();

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentOne onefrag = (FragmentOne)   onefragA.getSupportFragmentManager().findFragmentByTag("firstFragment");
                onefrag.updatebus(adapter.getItem(i));
                getDialog().cancel();

            }
        });

        return rootView;

    }


}
