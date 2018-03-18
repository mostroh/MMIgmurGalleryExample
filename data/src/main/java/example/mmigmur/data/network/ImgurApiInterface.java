package example.mmigmur.data.network;

import example.mmigmur.data.entities.AuthorizationEntity;
import example.mmigmur.data.entities.response.AuthResponse;
import example.mmigmur.data.entities.response.DeleteResponse;
import example.mmigmur.data.entities.response.GalleryResponse;
import example.mmigmur.data.entities.response.UploadImageResponse;
import okhttp3.Callback;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
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
     * @param b64Image        image
     * @return            Callback used for success/failures
     */

    //RequestBody file = RequestBody.create(MediaType.parse("image/*"), path);
    @FormUrlEncoded
    @POST("/3/image")
    Call<UploadImageResponse> postImage(
            @Header("Authorization") String auth,
            @Field("image")String b64Image,
            @Field("title") String title,
            @Field("description") String description
    );

    /**
     *
     * @param auth          #Type of authorization for upload
     * @return              Callback used for success/failures
     */
    @GET("/3/account/me/images")
    Call<GalleryResponse> getAccountImages(
            @Header("Authorization") String auth);

    @DELETE("/3/image/{imagehash}")
    Call<DeleteResponse> deleteImage(
            @Header("Authorization") String auth,
            @Path("imagehash") String imagehash);


}
