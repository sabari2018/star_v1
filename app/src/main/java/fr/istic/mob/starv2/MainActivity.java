package fr.istic.mob.starv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.istic.mob.starv2.model.BusRoute;
import fr.istic.mob.starv2.model.StarContract;

public class MainActivity extends FragmentActivity implements DeptListener{

    private Fragment one;
    private Fragment twoFragment;
    private Fragment threeFragment;
    private Fragment fourFragment;
    FragmentManager ft ;

    public static ProgressDialog mprogressDialog ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            one = FragmentOne.newInstance();
            twoFragment = FragmentTwo.newInstance();
            threeFragment = FragmentThree.newInstance();
            fourFragment = FragmentFour.newInstance();
            ft =  getSupportFragmentManager();

        }

        if (findViewById(R.id.fragment_container) != null) {

            if (savedInstanceState != null) {
                return;
            }

            FragmentOne firstFragment = new FragmentOne();
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            String formattedTime = tf.format(c.getTime());

            Bundle args = new Bundle();

            args.putString(FragmentOne.ARG_PARAM1, formattedTime);
            args.putString(FragmentOne.ARG_PARAM2, formattedDate);



            one.setArguments(args);
            ft.beginTransaction().add(R.id.fragment_container, one, "firstFragment").commit();



        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager() , "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showBusickerDialog(View v) {
        DialogFragment newFragment = new BusPicker();
        newFragment.show(getSupportFragmentManager(), "busPick");
    }

    public void gotoFrag2(View v){

    }

    public void switchFrag(Bundle args,int i){

        switch (i){
            case 1:
                one = (FragmentOne) ft.findFragmentByTag("firstFragment");
                if (one == null){
                    one = new FragmentOne();
                }
                one.setArguments(args);
                ft.beginTransaction().replace(R.id.fragment_container, one)
                        .disallowAddToBackStack()
                        .commit();
                break;
            case 2:

                if (one.isInLayout()){
                    Log.e("XXXX","one is on caontainer" ) ;
                }
                twoFragment.setArguments(args);
                ft.executePendingTransactions();
                ft.beginTransaction().replace(R.id.fragment_container,twoFragment,"twoFragment")
                        .addToBackStack("firstFragment")
                        .commit();

                break;
            case 3:
                threeFragment.setArguments(args);
                ft.beginTransaction().remove(twoFragment).commit();
                ft.executePendingTransactions();
                ft.beginTransaction().replace(R.id.fragment_container, threeFragment, "threeFragment")
                        .addToBackStack("firstFragment")
                        .commit();
                break;
            case 4:
                fourFragment = (FragmentFour) ft.findFragmentByTag("fourFragment");
                if (fourFragment == null)fourFragment = new FragmentFour();
                fourFragment.setArguments(args);
                ft.beginTransaction().replace(R.id.fragment_container, fourFragment)
                        .addToBackStack("firstFragment")
                        .commit();
                break;
        }

    }

    public List<BusRoute> GetMyBusroute(){

        Cursor cursor = getContentResolver().query(StarContract.BusRoutes.CONTENT_URI,
                null, null, null,
                StarContract.BusRoutes.BusRouteColumns.ROUTE_ID);
        List<BusRoute> busRoutes = new ArrayList<>();
        int i = 0;
        if (cursor.moveToFirst()) {
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
        return busRoutes ;

    }

    public static String dateFormat(String date){
        String [] d;
        try {
            d = date.split("-");
        }catch (Exception e){
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            Log.e("STARX 2",timeStamp);
            d = timeStamp.split("-");
        }
        int var  = 1 ;
        try {
            var = Integer.valueOf(d[1]);

        }catch (Exception e){
            switch (d[1]){
                case "Jan": d[1] = "01"  ;
                    break;
                case "Feb": d[1] = "02"  ;
                    break;
                case "Mar":d[1] = "03"  ;
                    break;
                case "Apr":d[1] = "04"  ;
                    break;
                case "May":d[1] = "05"  ;
                    break;
                case "Jun":d[1] = "06"  ;
                    break;
                case "Jul":d[1] = "07"  ;
                    break;
                case "Aug":d[1] = "08"  ;
                    break;
                case "Sep":d[1] = "09"  ;
                    break;
                case "Oct":d[1] = "10"  ;
                    break;
                case "Nov":d[1] = "11"  ;
                    break;
                case "Dec":d[1] = "12"  ;
                    break;


            }
        }
        for (int i = 0; i<d.length; i++){
            Integer in = Integer.valueOf(d[i]);
            if (in<10){
                d[i] = "0"+in;
            }


        }
        return d[2]+d[1]+d[0];
    }

    public static String HeurFormat(String date){
//        String [] d = date.split(":");
        String [] d;
        try {
            Log.e("STARX",date);
            d = date.split(":");
        }catch (Exception e){
            String timeStamp = new SimpleDateFormat("yyyy:MM:dd").format(new Date());
            Log.e("STARX 2",timeStamp);
            d = timeStamp.split(":");
        }

        for (int i = 0; i<d.length; i++){
            Integer in = Integer.valueOf(d[i]);
            if (in<10){
                d[i] = "0"+in;
            }
        }
        return d[0]+":"+d[1]+":00";
    }

    @Override
    public void onDeptSelected(Bundle b,int i) {
        switchFrag(b,i);

    }
}