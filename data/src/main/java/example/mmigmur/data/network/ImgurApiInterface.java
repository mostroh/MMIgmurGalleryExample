package example.mmigmur.data.network;

import example.mmigmur.data.entities.AuthorizationEntity;
import example.mmigmur.data.entities.response.AuthResponse;
import example.mmigmur.data.entities.response.GalleryResponse;
import example.mmigmur.data.entities.response.UploadImageResponse;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by migarcma on 16/3/18.
 */

public interface ImgurApiInterface {

        @FormUrlEncoded
        @POST("/oauth2/token")
        Call<AuthorizationEntity> login(
                @Field("refresh_token") String refreshToken,
                @Field("client_id") String clientId,
                @Field("client_secret") String clientSecret,
                @Field("grant_type") String grantType
        );


        /****************************************
         * Upload
         * Image upload API
         */

        /**
         * @param auth        #Type of authorization for upload
         * @param title       #Title of image
         * @param description #Description of image
         * @param albumId     #ID for album (if the user is adding this image to an album)
         * @param username    username for upload
         * @param file        image
         * @return            Callback used for success/failures
         */

        //RequestBody file = RequestBody.create(MediaType.parse("image/*"), path);
        @POST("/3/image")
        Call<UploadImageResponse> postImage(
                @Header("Authorization") String auth,
                @Query("title") String title,
                @Query("description") String description,
                @Query("album") String albumId,
                @Query("account_url") String username,
                @Part("image")RequestBody file
        );

        /**
         *
         * @param auth          #Type of authorization for upload
         * @return              Callback used for success/failures
         */
        @GET("/3/account/me/images")
        Call<GalleryResponse> getAccountImages(
                @Header("Authorization") String auth);

}
