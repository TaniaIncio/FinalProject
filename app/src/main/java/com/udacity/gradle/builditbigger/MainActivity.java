package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.JokeLib;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.tincio.example.jokeandroidlib.ShowJokeActivity;

import com.udacity.gradle.builditbigger.service.EndpointsAsyncTask;



public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        //add anuncion intersticiales
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {

                showJoke();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //obtener jokes
    public void showJoke() {

        JokeLib libJoke = new JokeLib();
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        progressBar.setVisibility(View.VISIBLE);
        asyncTask.setListenerResponse(new EndpointsAsyncTask.ListenerResponse() {
            @Override
            public void getDownloadResponse(String mError, String mResponse) {
                progressBar.setVisibility(View.GONE);
                Intent mIntent = new Intent(MainActivity.this, ShowJokeActivity.class);
                mIntent.putExtra(ShowJokeActivity.PARAM_TEXT, mResponse);
                startActivity(mIntent);
            }
        });
        asyncTask.execute(libJoke.getJoke());
    }
    //obtener jokes
    public void tellJoke(View view) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            showJoke();
        }
    }

    /**Anuncios intersticiales**/
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("B1B30FBDDBEF32919AF6C5099897C2FA")//AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

}
