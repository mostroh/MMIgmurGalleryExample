package example.mmigmur.mmimgurgalleryexample.utils;

/**
 * Created by migarcma on 16/3/18.
 */

public class Constants {

    /*
      Logging flag
     */
    public static final boolean LOGGING = false;

    /*
      Your imgur client id. You need this to upload to imgur.

      More here: https://api.imgur.com/
     */
    public static final String MY_IMGUR_CLIENT_ID = "d19d2490763d0fb";
    public static final String MY_IMGUR_CLIENT_SECRET = "d7071742542d8d4676a6802db90e7c8a79ec36c5";

    public static final String BASE_URL = "https://api.imgur.com";

    /*
      Redirect URL for android.
     */
    public static final String MY_IMGUR_REDIRECT_URL = "https://android-mmimgur";

    /*
      Client Auth
     */
    public static String getClientAuth() {
        return "Client-ID " + MY_IMGUR_CLIENT_ID;
    }


}
