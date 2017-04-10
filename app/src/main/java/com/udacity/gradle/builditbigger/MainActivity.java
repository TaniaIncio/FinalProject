package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.JokeLib;
import com.tincio.example.jokeandroidlib.ShowJokeActivity;

import com.udacity.gradle.builditbigger.service.EndpointsAsyncTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
public void tellJoke(View view) {
    JokeLib libJoke = new JokeLib();
    EndpointsAsyncTask asyncTask = new EndpointsAsyncTask();
    asyncTask.setListenerResponse(new EndpointsAsyncTask.ListenerResponse() {
        @Override
        public void getDownloadResponse(String mError, String mResponse) {
            Intent mIntent = new Intent(MainActivity.this, ShowJokeActivity.class);
            mIntent.putExtra(ShowJokeActivity.PARAM_TEXT, mResponse);
            startActivity(mIntent);
        }
    });
    asyncTask.execute(libJoke.getJoke());

}



}
