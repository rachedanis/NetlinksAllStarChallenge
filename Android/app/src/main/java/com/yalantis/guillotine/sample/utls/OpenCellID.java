package com.yalantis.guillotine.sample.utls;

import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WiKi on 21/02/2016.
 */
class OpenCellID {
    String mcc;  //Mobile Country Code
    String mnc;  //mobile network code
    String cellid; //Cell ID
    String lac;  //Location Area Code

    Boolean error;
    String strURLSent;
    String GetOpenCellID_fullresult;
    private String latitude;
    String longitude;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public Boolean isError() {
        return error;
    }

    public void setMcc(String value) {
        mcc = value;
    }

    public void setMnc(String value) {
        mnc = value;
    }

    public void setCallID(int value) {
        cellid = String.valueOf(value);
    }

    public void setCallLac(int value) {
        lac = String.valueOf(value);
    }

    public String getLocation() {
        return (latitude + " : " + longitude);
    }

    public void groupURLSent() {
        strURLSent =
                "http://www.opencellid.org/cell/get?key=bd141b05-5ac7-435c-a507-4626c1a011a8&mcc=" + mcc
                        + "&mnc=" + mnc
                        + "&cellid=" + cellid
                        + "&lac=" + lac
                        + "&fmt=txt&format=json";
    }

    public String getstrURLSent() {
        return strURLSent;
    }

    public String getGetOpenCellID_fullresult() {
        return GetOpenCellID_fullresult;
    }

    public void GetOpenCellID() throws Exception {
        groupURLSent();
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(strURLSent);
        HttpResponse response = client.execute(request);
        GetOpenCellID_fullresult = EntityUtils.toString(response.getEntity());
        spliteResult();
    }


    private void spliteResult() {
        if (GetOpenCellID_fullresult.equalsIgnoreCase("err")) {
            error = true;
        } else {
            error = false;
            Log.d("aaaaaa", String.valueOf(GetOpenCellID_fullresult));
            try {
                JSONObject json = new JSONObject(GetOpenCellID_fullresult);
                latitude = json.getString("lat");
                longitude = json.getString("lon");
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


    }
}
