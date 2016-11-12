package com.yalantis.guillotine.sample.utls;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;
import com.yalantis.guillotine.sample.activity.MainActivity;


class SendfeedbackJob extends AsyncTask<String, Void, String> {

    private int error;
    private String msg;
    private OpenCellID openCellID;
    private String mcc;
    private String mnc;
    private int cid;
    private int lac;
    private service se;
    private String lat;
    private String lon;


    public SendfeedbackJob(String a, String b, int c, int d, service s) {
        this.mcc = a;
        this.mnc = b;
        this.cid = c;
        this.lac = d;
        this.se = s;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ///dialog = new SpotsDialog(activi.this);
    }

    @Override
    protected String doInBackground(String[] params) {
        openCellID = new OpenCellID();
        openCellID.setMcc(mcc);
        openCellID.setMnc(mnc);
        openCellID.setCallID(cid);
        openCellID.setCallLac(lac);
        try {
            openCellID.GetOpenCellID();

            if (!openCellID.isError()) {
                error = 1;
            } else {
                error = 2;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg = e.toString();
            error = 3;
        }
        return null;
    }

    @Override
    protected void onPostExecute(String message) {
        if (error == 1) {
            lat = openCellID.getLatitude();
            lon = openCellID.getLongitude();
            SessionManger s = new SessionManger(se.getApplicationContext());
            s.setLoc(lat, lon);
            se.Request(lat, lon);
//            getAdresseFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
//
//            textGeo.setText(openCellID.getLocation());
//            textRemark.setText("\n\n"
//                    + "URL sent: \n" + openCellID.getstrURLSent() + "\n\n"
//                    + "response: \n" + openCellID.GetOpenCellID_fullresult);
//        } else if (error == 2) {
//            textGeo.setText("Error");
//        } else if (error == 3) {
//            textGeo.setText("Exception: " + msg);
//        }

        }
    }

}