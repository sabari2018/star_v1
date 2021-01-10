package fr.istic.mob.starv2;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import fr.istic.mob.starv2.model.BusRoute;
import fr.istic.mob.starv2.model.StarContract;
import fr.istic.mob.starv2.model.Stop;

/**
 * Sadou & Issa.
 */
public class FragmentTwo extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3 = "param3";
    public static final String ARG_PARAM4 = "param4";
    public static final String ARG_PARAM5 = "param5";
    public static final String ARG_PARAM6 = "param6";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;
    private String mParam5;
    private String mParam6;

    TextView labeBus ;
    TextView dateHeur ;
    TextView direction ;
    ListView listViewArret ;
    List<Stop> stops = new ArrayList<>();

    ArrayList<String> idtrips = new ArrayList<>();

    Cursor cursorone ;

   public static Bundle agrsone = new Bundle() ;



    private MainActivity mListener;

    public FragmentTwo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTwo.
     */
    public static FragmentTwo newInstance(BusRoute param1, int param2) {
        FragmentTwo fragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, (Serializable) param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static FragmentTwo newInstance() {
        FragmentTwo fragment = new FragmentTwo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
            mParam5 = getArguments().getString(ARG_PARAM5);
            mParam6 = getArguments().getString(ARG_PARAM6);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_two,container, false);

        labeBus = view.findViewById(R.id.textView6);
        dateHeur = view.findViewById(R.id.textView9);
        direction = view.findViewById(R.id.textView8);
        listViewArret = view.findViewById(R.id.listeArretRoute);

        ArrayList<String> values = new ArrayList<>();
        for (Stop stop: getArret(mParam5,mParam6)){
            values.add(stop.getName());
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(),R.layout.arretlayout, values);

        listViewArret.setAdapter(adapter);

        dateHeur.setText(mParam1+" / "+ mParam2);
        labeBus.setText(""+mParam3);
        direction.setText(mParam4);

        listViewArret.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //stop ie - trip id - heur date
                agrsone.putString(FragmentThree.ARG_PARAM1, ""+mParam1); // heure
                agrsone.putString(FragmentThree.ARG_PARAM2, ""+mParam2); // date
                agrsone.putString(FragmentThree.ARG_PARAM3, ""+mParam3); //destination
                agrsone.putString(FragmentThree.ARG_PARAM4, ""+adapter.getItem(i)); // arret
                agrsone.putString(FragmentThree.ARG_PARAM5, ""+mParam5); // id route
                agrsone.putString(FragmentThree.ARG_PARAM6, ""+stops.get(i).getId()); //  id trip

                Log.e("XXXX"," ->  " + agrsone.toString())  ;
                mListener.switchFrag(agrsone,3);




            }
        });
        // Inflate the layout for this fragment
        return view;

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
          //  mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MainActivity) {
            mListener = (MainActivity) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        agrsone.clear();
        mListener = null;
        mParam1 = null;
        mParam3 = null;
        mParam2 = null;
        mParam4 = null;
        mParam5 = null;
        mParam6 = null;
        stops.clear();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public List<Stop> getArret(String idBus, String direnctionss){

        Uri stopsUri;
        stopsUri = Uri.withAppendedPath(StarContract.AUTHORITY_URI, "route_stops");
        String[] selargs = {idBus, direnctionss};
        cursorone = mListener.managedQuery(stopsUri,
                null, null, selargs,
                StarContract.Stops.StopColumns.STOP_ID);
        if (cursorone.moveToFirst()) {
            do {
                Stop item = new Stop(
                        cursorone.getString(cursorone.getColumnIndex(StarContract.Stops.StopColumns.STOP_ID)),
                        cursorone.getString(cursorone.getColumnIndex(StarContract.Stops.StopColumns.NAME)),
                        cursorone.getString(cursorone.getColumnIndex(StarContract.Stops.StopColumns.DESCRIPTION)),
                        cursorone.getFloat(cursorone.getColumnIndex(StarContract.Stops.StopColumns.LATITUDE)),
                        cursorone.getFloat(cursorone.getColumnIndex(StarContract.Stops.StopColumns.LONGITUDE)),
                        cursorone.getString(cursorone.getColumnIndex(StarContract.Stops.StopColumns.WHEELCHAIR_BOARDING))


                );
                stops.add(item);
                idtrips.add(cursorone.getString(cursorone.getColumnIndex(StarContract.Trips.TripColumns.TRIP_ID))) ;
            } while (cursorone.moveToNext());
        }
        return  stops ;
    }
}
