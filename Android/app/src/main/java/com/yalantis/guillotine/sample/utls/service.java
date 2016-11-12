package com.yalantis.guillotine.sample.utls;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.yalantis.guillotine.sample.R;

import java.util.Map;
import java.util.HashMap;


import org.json.JSONException;

/**
 * Created by WiKi on 21/02/2016.
 */
public class service extends Service {

    private String mcc;
    private String mnc;
    private int cid;
    private int lac;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        GsmCellLocation cellLocation = (GsmCellLocation) telephonyManager.getCellLocation();

        String networkOperator = telephonyManager.getNetworkOperator();
        mcc = networkOperator.substring(0, 3);
        mnc = networkOperator.substring(3);

        cid = cellLocation.getCid();
        lac = cellLocation.getLac();
        SendfeedbackJob job = new SendfeedbackJob(mcc, mnc, cid, lac, this);
        job.execute();


        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void Request(String a, String b) {
        SessionManger s = new SessionManger(getApplicationContext());

        String url = "http://"+ getResources().getString(R.string.ip) +":3000/change.json?id="+s.getUserDetails().get(SessionManger.KEY_ID)+"&lat=" + a + "&lon=" + b;

        //DialogGenerator.getInstance().showProgressDialog(this);

        StringRequest myReq = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("test", response);

            }

        }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(), "Veuillez verifiez votre connexion internet", Toast.LENGTH_LONG).show();
            }
        }

        );


        MySingleton.getInstance(this).addToRequestQueue(myReq);


    }


}