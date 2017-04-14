package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.JokeLib;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.tincio.example.jokeandroidlib.ShowJokeActivity;
import com.udacity.gradle.builditbigger.service.EndpointsAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements View.OnClickListener  {

    ProgressBar progressBar;
    InterstitialAd mInterstitialAd;
    Button btnShowJoke;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        progressBar = (ProgressBar)root.findViewById(R.id.progressBar);
        this.btnShowJoke = (Button)root.findViewById(R.id.btn_show_joke);
        this.btnShowJoke.setOnClickListener(this);
        //add anuncion intersticiales
        mInterstitialAd = new InterstitialAd(getActivity());
        mInterstitialAd.setAdUnitId(getString(R.string.banner_ad_unit_id));
        requestNewInterstitial();
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                showJoke();
            }
        });
        return root;
    }

    void activeButtonJoke(Boolean active){
        this.btnShowJoke.setEnabled(active);
    }
    //obtener jokes
    public void showJoke() {
        this.activeButtonJoke(false);
        JokeLib libJoke = new JokeLib();
        EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
        progressBar.setVisibility(View.VISIBLE);
        asyncTask.setListenerResponse(new EndpointsAsyncTask.ListenerResponse() {
            @Override
            public void getDownloadResponse(String mError, String mResponse) {
                activeButtonJoke(true);
                progressBar.setVisibility(View.GONE);
                Intent mIntent = new Intent(getActivity(), ShowJokeActivity.class);
                mIntent.putExtra(ShowJokeActivity.PARAM_TEXT, mResponse);
                startActivity(mIntent);
            }
        });
        asyncTask.execute(libJoke.getJoke());
    }

    public void tellJoke() {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            showJoke();
        }
    }

    /**Anuncios intersticiales**/
    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(getString(R.string.test_device))//AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void onClick(View v) {
        tellJoke();
    }
}

