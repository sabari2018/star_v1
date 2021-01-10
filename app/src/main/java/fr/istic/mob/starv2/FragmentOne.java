package fr.istic.mob.starv2;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.List;

import fr.istic.mob.starv2.model.BusRoute;
import fr.istic.mob.starv2.model.StarContract;


/**
 * Sadou & Issa.
 */

public class FragmentOne extends Fragment {
    static Bundle args = new Bundle();
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_PARAM2 = "param2";
    public static final String ARG_PARAM3 = "param3";
    Activity master;
    FragmentActivity onefragO;

    TextView dateTextview;
    TextView timerTextview;

    private Spinner spinnerBus;
    private TextView selectedBus;
    private String selectedBusinfo;
    private RadioButton direction1;
    private RadioButton direction2;

    static public Button continuer;
    static public Button annuler;

    private RadioGroup radioGroupDestination;


    private String mParam1;
    private String mParam2;
    private BusRoute mParam3;

    private static MainActivity mListener;

    public FragmentOne() {
        // Required empty public constructor
    }


    public static FragmentOne newInstance() {
        FragmentOne fragment = new FragmentOne();
        return fragment;
    }

    public static FragmentOne newInstance(String param1, String param2) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentOne newInstance1(String param1) {
        Log.d("XXXX", " passed" + param1 + " -*- " + mParam2);
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, mParam2);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentOne newInstance2(String param2) {
        FragmentOne fragment = new FragmentOne();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mParam1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        onefragO = this.getActivity();

        timerTextview = view.findViewById(R.id.textView4);
        dateTextview = view.findViewById(R.id.textView3);
        radioGroupDestination = (RadioGroup) view.findViewById(R.id.radiodestination);
        direction1 = view.findViewById(R.id.radioButton);

        direction2 = view.findViewById(R.id.radioButton2);
        selectedBus = view.findViewById(R.id.spinner);
        continuer = (Button) view.findViewById(R.id.button4);
        annuler = view.findViewById(R.id.button3);

        continuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((direction1.isChecked() || direction2.isChecked()) && mParam3 != null) {
                    mListener.switchFrag(goFrag2(), 2);
                } else if (mParam3 == null) {
                    Toast.makeText(FragmentOne.super.getContext(), "Selectionnez une ligne", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FragmentOne.super.getContext(), "Selectionnez une direction", Toast.LENGTH_SHORT).show();
                }

            }
        });

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            dateTextview.setText(mParam2);
            timerTextview.setText(mParam1);
        }

        return view;
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
        mListener = null;
        mParam1 = null;
        mParam3 = null;
        mParam2 = null;
        args = null;
    }

    public void updatetimer(String s) {
        mParam1 = s;
        timerTextview.setText(mParam1);
    }

    public void updatedate(String s) {
        mParam2 = s;
        dateTextview.setText(mParam2);
    }

    public void updatebus(BusRoute s) {
        mParam3 = s;
        String[] direction = s.getLongName().split("<>", 2);
        direction1.setText(direction[0] + " VERS " + direction[1]);
        direction2.setText(direction[1] + " VERS " + direction[0]);
        selectedBus.setText(s.getShortName());
        selectedBus.setTextColor(Color.parseColor("#" + s.getTextColor()));
        selectedBus.setBackgroundColor(Color.parseColor("#" + s.getColor()));

        args.putString(FragmentTwo.ARG_PARAM1, "" + mParam2);
        args.putString(FragmentTwo.ARG_PARAM2, "" + mParam1);
        args.putString(FragmentTwo.ARG_PARAM3, "" + mParam3.getShortName());


    }

    public static List<BusRoute> getBusRoute() {

        Cursor cursor = mListener.getContentResolver().query(Uri.withAppendedPath(StarContract.AUTHORITY_URI, StarContract.BusRoutes.CONTENT_PATH),
                null, null, null,
                StarContract.BusRoutes.BusRouteColumns.ROUTE_ID);
        List<BusRoute> busRoutes = new ArrayList<>();
        int i = 0;
        if (cursor != null && cursor.moveToFirst()) {
            do {
                BusRoute item = new BusRoute(
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.ROUTE_ID)),
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.SHORT_NAME)),
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.LONG_NAME)),
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.TYPE)),
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.COLOR)),
                        cursor.getString(cursor.getColumnIndex(StarContract.BusRoutes.BusRouteColumns.TEXT_COLOR))
                );
                i++;
                busRoutes.add(item);
            } while (cursor.moveToNext());
        }

        return busRoutes;
    }

    public Bundle goFrag2() {
        Log.d("XXXX", "okm+ hhj");
        Log.e("XXXX", " .>" + args.getString(FragmentTwo.ARG_PARAM3) + " ---" + mParam3.getShortName());
        String[] direction = mParam3.getLongName().split("<>", 2);
        int idbt = 0;
        if (!direction1.isChecked()) {
            idbt = 1;
        }
        ;
        Log.e("XXXX", " .>" + idbt + " ---" + mParam3.getShortName());

        args.putString(FragmentTwo.ARG_PARAM1, "" + mParam2);
        args.putString(FragmentTwo.ARG_PARAM2, "" + mParam1);
        args.putString(FragmentTwo.ARG_PARAM3, "" + mParam3.getShortName());
        args.putString(FragmentTwo.ARG_PARAM4, "" + direction[idbt]);
        args.putString(FragmentTwo.ARG_PARAM5, "" + mParam3.getRoute_id());
        args.putString(FragmentTwo.ARG_PARAM6, "" + idbt);


        return args;
    }


}
