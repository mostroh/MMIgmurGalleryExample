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
    public static final String MY_IMGUR_CLIENT_SECRET = "c70b37a9bafc2a6b6d64a524145544957a835672";

    public static final String BASE_URL = "https://api.imgur.com";

    /*
      Redirect URL for android.
     */
    public static final String MY_IMGUR_REDIRECT_URL = "https://android-mmimgur";
    public static final String USER_NAME_KEY = "user_name_key";
    public static final String EMPTY = "";
    public static final String IMAGE_MODEL = "image_model";

    /*
      Client Auth
     */
    public static String getClientAuth() {
        return "Client-ID " + MY_IMGUR_CLIENT_ID;
    }


}
