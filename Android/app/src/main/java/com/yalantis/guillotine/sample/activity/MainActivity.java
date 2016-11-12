package com.yalantis.guillotine.sample.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.guillotine.animation.GuillotineAnimation;
import com.yalantis.guillotine.sample.HelpActivity;
import com.yalantis.guillotine.sample.HistoryActivity;
import com.yalantis.guillotine.sample.R;
import com.yalantis.guillotine.sample.SettingsActivity;
import com.yalantis.guillotine.sample.utls.MyReceiver;
import com.yalantis.guillotine.sample.utls.SessionManger;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
public class MainActivity extends AppCompatActivity {
    private static final long RIPPLE_DURATION = 250;


    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.root)
    FrameLayout root;
    @InjectView(R.id.content_hamburger)
    View contentHamburger;
    private GuillotineAnimation gg;

    public void onClickSettings(View v) {
        SessionManger sm = new SessionManger(this);
        sm.logoutUser();
        finish();
    }

    public void onClickHistory(View v) {
        Intent historyIntent = new Intent(this, HistoryActivity.class);
        startActivity(historyIntent);

    }

    public void onClickHelp(View v) {
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);

    }

    public void onClickMyPosition(View v) {
        Intent myPositionIntent = new Intent(this, MapsActivity.class);
        gg.close();
        startActivity(myPositionIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        ButterKnife.inject(this);
        SessionManger session = new SessionManger(this);
        if (!session.checkLogin()) finish();
        else {
            startAlert();
        }
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
    }

    View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
    root.addView(guillotineMenu);

    gg = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
            .setStartDelay(RIPPLE_DURATION)
    .setActionBarViewForAnimation(toolbar)
    .setClosedOnStart(false)
    .build();


    }

    public void startAlert() {
        Intent intent = new Intent(this, MyReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
  /*alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
    + (i * 1000), (i * 1000), pendingIntent);*/
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                , 10000, pendingIntent);

    }


}
