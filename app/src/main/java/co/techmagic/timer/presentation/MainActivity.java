package co.techmagic.timer.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import co.techmagic.timer.R;
import co.techmagic.timer.TimerApp;

public class MainActivity extends AppCompatActivity implements MainView {

    @Inject
    MainPresenter mMainPresenter;

    private TextView mTimeTv;
    private ImageView mIncreaseTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        inject();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTimeTv = findViewById(R.id.time_tv);

        mIncreaseTimeBtn = findViewById(R.id.increase_time_btn);
        mIncreaseTimeBtn.setOnClickListener(view -> mMainPresenter.onIncreaseRemainingTimeClick());

        mMainPresenter.setView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mMainPresenter.onStart();
    }

    @Override
    protected void onStop() {
        mMainPresenter.onStop();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mMainPresenter.setView(null);
        release();
        super.onDestroy();
    }

    @Override
    public void setTime(String time) {
        mTimeTv.setText(time);
    }

    private void inject() {
        ((TimerApp)getApplication()).getMainComponent().inject(this);
    }

    private void release() {
        ((TimerApp)getApplication()).releaseMainComponent();
    }

}
