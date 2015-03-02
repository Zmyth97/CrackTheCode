package com.desitum.crackTheCode.android;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.desitum.crackTheCode.GooglePlayServicesInterface;
import com.desitum.crackTheCode.CrackTheCode;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

public class AndroidLauncher extends AndroidApplication implements GooglePlayServicesInterface,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    public static final int FIRST_CODE = 0;
    public static final int CODE_NOVICE = 1;
    public static final int CODE_EXPERT = 2;
    public static final int CODE_MASTER = 3;
    public static final int CODE_LEGEND = 4;

    public static final int ENDLESS_BEGINNER = 5;
    public static final int ENDLESS_NOVICE = 6;
    public static final int ENDLESS_EXPERT = 7;
    public static final int ENDLESS_MASTER = 8;
    public static final int ENDLESS_LEGEND = 9;

    private static final int REQUEST_CODE_RESOLVE_ERR = 9000;

    private static final String TAG = "GooglePlayServicesActivity";

    private static final String KEY_IN_RESOLUTION = "is_in_resolution";

    private ConnectionResult mConnectionResult;

    /**
     * Request code for auto Google Play Services error resolution.
     */
    protected static final int REQUEST_CODE_RESOLUTION = 1;

    /**
     * Google API client.
     */
    private GoogleApiClient mGoogleApiClient;

    /**
     * Determines if the client is in a resolution state, and
     * waiting for resolution intent to return.
     */
    private boolean mIsInResolution;

    private static final String AD_UNIT_ID = "ca-app-pub-6864829825268832/5300045101";
    private static final String END_AD_UNIT_ID = "ca-app-pub-6864829825268832/6776778301";
    protected AdView adView;
    protected View gameView;

    private AdView admobView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mIsInResolution = savedInstanceState.getBoolean(KEY_IN_RESOLUTION, false);
        }
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useImmersiveMode = true;
        config.useAccelerometer = false;
        config.useCompass = false;
        config.useWakelock = false;

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        RelativeLayout layout = new RelativeLayout(this);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(params);


        View gameView = createGameView(config);
        layout.addView(gameView);
        admobView = createAdView();
        layout.addView(admobView);

        setContentView(layout);
        startAdvertising(admobView);
    }
    private final int SHOW_ADS = 1;
    private final int HIDE_ADS = 0;

    protected Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            adView.setVisibility(View.VISIBLE); //change to visible
            switch(msg.what) {
                case SHOW_ADS:
                {
                    adView.setVisibility(View.VISIBLE); //change to visible
                    break;
                }
                case HIDE_ADS:
                {
                    adView.setVisibility(View.GONE);//change to not visible
                    // you should also disable the ad fetching here!
                    break;
                }
            }
        }
    };

    @Override
    public void getRegularLeaderboard() {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, "CgkIoOnTp_EOEAIQAA"), 100);
        }
        else{
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void getEndlessLeaderboard(){
        //
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            startActivityForResult(Games.Leaderboards.getLeaderboardIntent(mGoogleApiClient, "CgkIoOnTp_EOEAIQDA"), 100);
        }
        else{
            mGoogleApiClient.connect();
        }
    }

    @Override
    public void submitRegularScore(int score) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Leaderboards.submitScore(mGoogleApiClient, "CgkIoOnTp_EOEAIQAA", score);
        }
        else{
            //Nothing!
        }
    }

    @Override
    public  void submitEndlessScore(int score){
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            Games.Leaderboards.submitScore(mGoogleApiClient, "CgkIoOnTp_EOEAIQDA", score);
        }
        else{
            //Nothing!
        }
    }

    @Override
    public void unlockAchievement(int achievement) {
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            if (achievement == FIRST_CODE) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQAg");
            } else if (achievement == CODE_NOVICE) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQAw");
            } else if (achievement == CODE_EXPERT) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQBA");
            } else if (achievement == CODE_MASTER) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQBQ");
            } else if (achievement == CODE_LEGEND) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQBg");
            } else if (achievement == ENDLESS_BEGINNER) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQBw");
            } else if (achievement == ENDLESS_NOVICE) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQCA");
            } else if (achievement == ENDLESS_EXPERT) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQCQ");
            } else if (achievement == ENDLESS_MASTER) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQCg");
            } else if (achievement == ENDLESS_LEGEND) {
                Games.Achievements.unlock(mGoogleApiClient, "CgkIoOnTp_EOEAIQCw");
            } else {
                //Nothing!
            }
        }
    }

    @Override
    public void login() {

    }

    @Override
    public void logout() {

    }

    @Override
    public void showAd() {
        handler.sendEmptyMessage(SHOW_ADS);
    }

    @Override
    public void hideAd() {
        handler.sendEmptyMessage(HIDE_ADS);
    }

    @Override
    public void shareRegularScore(int score) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I just got " + score + " on Crack The Code! Try to beat me: https://play.google.com/store/apps/details?id=com.desitum.crackTheCode.android");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    public void shareEndlessScore(int score) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I just got " + score + " on Crack The Code Endless Mode! Try to beat me: https://play.google.com/store/apps/details?id=com.desitum.crackTheCode.android");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private AdView createAdView() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);
        //adView.setBackgroundColor(Color.BLACK);
        return adView;
    }

    private View createGameView(AndroidApplicationConfiguration cfg) {
        gameView = initializeForView(new CrackTheCode(this), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        gameView.setLayoutParams(params);
        return gameView;
    }

    private void startAdvertising(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }

    /**
     * Called when the Activity is made visible.
     * A connection to Play Services need to be initiated as
     * soon as the activity is visible. Registers {@code ConnectionCallbacks}
     * and {@code OnConnectionFailedListener} on the
     * activities itself.
     */
    @Override
    protected void onStart() {
        super.onStart();
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addApi(Games.API)
                    .addScope(Games.SCOPE_GAMES)
                            // Optionally, add additional APIs and scopes if required.
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .build();
        }
        mGoogleApiClient.connect();
    }

    /**
     * Called when activity gets invisible. Connection to Play Services needs to
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onStop() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onStop();
    }

    /**
     * Saves the resolution state.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_IN_RESOLUTION, mIsInResolution);
    }

    /**
     * Handles Google Play Services resolution callbacks.
     */
    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        if (requestCode == REQUEST_CODE_RESOLVE_ERR && responseCode == RESULT_OK) {
            mConnectionResult = null;
            mGoogleApiClient.connect();
        }
    }

    private void retryConnecting() {
        mIsInResolution = false;
        if (!mGoogleApiClient.isConnecting()) {
            mGoogleApiClient.connect();
        }
    }

    /**
     * Called when {@code mGoogleApiClient} is connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "GoogleApiClient connected");
        // TODO: Start making API requests.
    }

    /**
     * Called when {@code mGoogleApiClient} connection is suspended.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "GoogleApiClient connection suspended");
        retryConnecting();
    }

    /**
     * Called when {@code mGoogleApiClient} is trying to connect but failed.
     * Handle {@code result.getResolution()} if there is a resolution
     * available.
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, REQUEST_CODE_RESOLVE_ERR);
            } catch (IntentSender.SendIntentException e) {
                mGoogleApiClient.connect();
            }
        }
        // Save the result and resolve the connection failure upon a user click.
        mConnectionResult = result;
    }
}

