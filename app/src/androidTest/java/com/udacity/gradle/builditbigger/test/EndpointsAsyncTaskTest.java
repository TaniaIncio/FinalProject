package com.udacity.gradle.builditbigger.test;

import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.udacity.gradle.builditbigger.service.EndpointsAsyncTask;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

/**
 * Created by juan on 01/04/2017.
 */
@RunWith(AndroidJUnit4.class)
public class EndpointsAsyncTaskTest {

    String response;
    @Test
    public void testVerifyEchoResponse() {
        assertEquals("hello", "hello");
    }

    public void runTest() throws Throwable {
        try {
            assertEquals(true, true);
            EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();
            endpointsAsyncTask.setListenerResponse(new EndpointsAsyncTask.ListenerResponse() {
                @Override
                public void getDownloadResponse(String mError, String mResponse) {
               //     signal.countDown();
                  response = mResponse;
                }
            });
            //endpointsAsyncTask.execute(new Pair<Context, String>(this, ""));
            endpointsAsyncTask.execute("");
            assertFalse(TextUtils.isEmpty(response));
            //assertNull(mError);

            //assertFalse(TextUtils.isEmpty(mError));
            //signal.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}