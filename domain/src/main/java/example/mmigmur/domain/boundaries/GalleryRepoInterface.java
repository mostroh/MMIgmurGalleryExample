package example.mmigmur.domain.boundaries;

import java.util.List;

import example.mmigmur.domain.model.Image;

/**
 * Created by migarcma on 18/3/18.
 */

public interface GalleryRepoInterface {

    void getAccountGallery(String refreshToken, String accessToken, GalleryResponseListener galleryResponseListener);

    interface GalleryResponseListener{

        void onAccountGallerySuccesResponse(List<Image> gallery);

        void onAccountGalleryFailureConnection();

        void onAccountGalleryFailure();
    }
}
