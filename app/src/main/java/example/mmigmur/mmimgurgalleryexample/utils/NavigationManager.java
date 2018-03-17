package example.mmigmur.mmimgurgalleryexample.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;


import example.mmigmur.mmimgurgalleryexample.gallery.GalleryActivity;

/**
 * Created by migarcma on 12/9/17.
 */

public class NavigationManager {

    public static void goToGallery(Context context, boolean finishActivity, String username){

        if (context == null) {
            return;
        }

        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(Constants.USER_NAME_KEY, username);
        context.startActivity(intent);

        if (finishActivity){
            ((AppCompatActivity)context).finish();
        }
    }

}
