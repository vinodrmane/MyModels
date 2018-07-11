package com.example.vinod.mymodels;

import android.net.Uri;
import android.widget.Toast;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by Vinod on 4/7/2018.
 */

public class UtilityModel {
    public static void  showMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
    public static void retrieveImageFromCacheWithPicasso(){
 /*       Picasso.with(context)
                .load(Uri.parse(getItem(position).getStoryBigThumbUrl()))
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(holder.storyBigThumb, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        // Try again online if cache failed
                        Picasso.with(context)
                                .load(Uri.parse(getItem(position)
                                        .getStoryBigThumbUrl()))
                                .placeholder(R.drawable.user_placeholder)
                                .error(R.drawable.user_placeholder_error)
                                .into(holder.storyBigThumb);
                    }
                });*/
    }
}
