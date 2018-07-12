package com.example.eltobgy.teleprompterapp.activities.TeleprompterMainActivities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.eltobgy.teleprompterapp.R;
import com.example.eltobgy.teleprompterapp.models.Teleprompter;
import com.example.eltobgy.teleprompterapp.models.TeleprompterFile;
import com.example.eltobgy.teleprompterapp.utlis.SlideScrollView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Eltobgy on 10-Jul-18.
 */
public class TeleprompterActivity extends AppCompatActivity {

    private static final String TAG = TeleprompterActivity.class.getSimpleName();
    private TeleprompterFile mTeleprompterFile;
    private static final int SCROLL_START_DELAY_MILLIS = 6000;
    private Handler animationHandler;
    private int animationDelayMillis;
    private int scrollOffset;
    private boolean isPlaying;
    private String content;
    Teleprompter teleprompter;

    private AnimationRunnable animationRunnable;
    private int scrollSpeed;
    @BindView(R.id.scrollView)
    SlideScrollView scrollView;
    @BindView(R.id.slide_show_play)
    Button buttonPlay;
    @BindView(R.id.slide_show_pause)
    Button buttonPause;
    @BindView(R.id.teleprompter_content)
    TextView tvContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleprompter_view);
        ButterKnife.bind(this);
        ActionBar actionBar = this.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        if (getIntent() != null) {
            mTeleprompterFile = getIntent().getParcelableExtra("EXTRA FILE DATA");
            setTitle(mTeleprompterFile.getTitle());
            //setupSharedPreferences();
        }
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScrollAnimation();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopScrollAnimation();
            }
        });

        scrollView.setScrollViewListener((SlideScrollView.ScrollViewListener) this);
        setAnimationSpeed(teleprompter.getScrollSpeed());
        content = teleprompter.getContent();
        tvContent.setText(content);
        setFontSize(teleprompter.getFontSize());

    }
        private void startScrollAnimation(){
//        scrollView.fullScroll(ScrollView.FOCUS_UP);
            int y = scrollView.getScrollY();
            scrollView.setScrollable(false);
            animationHandler = new Handler();
            animationRunnable = new AnimationRunnable(y);
            animationHandler.postDelayed(animationRunnable,SCROLL_START_DELAY_MILLIS);
            showPauseButton();



        }
    public void showPauseButton() {
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScrollAnimation();
            }
        });}
        private void stopScrollAnimation(){
            animationHandler.removeCallbacks(animationRunnable);
            scrollView.setScrollable(true);
            showPlayButton();
        }
    public void showPlayButton() {
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startScrollAnimation();
            }
        });}
        private class AnimationRunnable implements Runnable {
            private int scrollTo;

            AnimationRunnable(int to){
                scrollTo = to;
            }

            @Override
            public void run() {
                scrollView.smoothScrollTo(0,scrollTo);
                animationHandler = new Handler();
                animationRunnable = new AnimationRunnable(scrollTo+scrollOffset);
                animationHandler.postDelayed(animationRunnable,animationDelayMillis);
            }
        }

    @Override
    public void onScrollChanged(SlideScrollView slideScrollView, int x, int y, int oldx, int oldy) {
        View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
        int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));
        if(diff <=40) {
            if(animationHandler!=null) {
                stopScrollAnimation();
            }
        }
    }


    private void setAnimationSpeed(int scrollSpeed) {

        switch (scrollSpeed) {
            case 0:
                animationDelayMillis = 25;
                scrollOffset = 1;
                break;
            case 1:
                animationDelayMillis = 30;
                scrollOffset = 2;
                break;

            case 2:
                animationDelayMillis = 30;
                scrollOffset = 3;
                break;

            case 3:
                animationDelayMillis = 25;
                scrollOffset = 3;
                break;

            case 4:
                animationDelayMillis = 30;
                scrollOffset = 4;
                break;
        }

    }


    private void setFontSize(int fontSize) {

        int size = 16;
        switch (fontSize) {


            case 0:
                size = R.integer.font_size_small_sp;
                break;

            case 1:
                size = R.integer.font_size_medium_sp;
                break;

            case 2:
                size = R.integer.font_size_large_sp;
                break;

            case 3:
                size = R.integer.font_size_xlarge_sp;
                break;

        }

        tvContent.setTextSize(TypedValue.COMPLEX_UNIT_SP, getResources().getInteger(size));

    }
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.main_menu, menu);
       return true;
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(TeleprompterActivity.this, SettingsActivity.class));
                finish();

            case R.id.add_file:
                //TODO add file activity
                startActivity(new Intent(TeleprompterActivity.this, SettingsActivity.class));
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
}}
