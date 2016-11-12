package com.yalantis.guillotine.sample.utls;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@Table(name = "History")
public class History extends Model {

    @Column(name = "date")
    public String date;
    @Column(name = "longitude")
    public String longitude;
    @Column(name = "latitude")
    public String latitude;
    @Column(name = "addresse")
    public String addresse;

    public History() {
    }

    public History(String date, String longitude, String latitude, String addresse) {
        this.date = date;
        this.longitude = longitude;
        this.latitude = latitude;
        this.addresse = addresse;
    }

    public static List<History> getAll() {
        return new Select()
                .from(History.class)
                .execute();
    }


    public String getAddresse() {
        return addresse;
    }

    public void setAddresse(String addresse) {
        this.addresse = addresse;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void parseHistory(JSONObject prof) throws JSONException {
        this.longitude = prof.getString("longitude");
        this.date = prof.getString("date");
        this.latitude = prof.getString("latitude");
        this.addresse = prof.getString("addresse");
        this.save();
    }


}