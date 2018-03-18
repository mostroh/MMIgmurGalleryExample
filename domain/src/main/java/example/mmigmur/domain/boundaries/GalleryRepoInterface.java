package example.mmigmur.domain.boundaries;

import java.util.List;

import example.mmigmur.domain.interactors.GalleryInteractorImpl;
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

    void deleteImage(String deleteHash, String accessToken, DeleteResponseListener deleteResponseListener);

    interface DeleteResponseListener {

        void onDeleteSuccessResponse();

        void onDeleteFailureResponse();
    }

    void uploadImage(String b64Image, String title, String description, String accessToken, UploadResponseListener uploadResponseListener);

    interface UploadResponseListener {

        void onUploadSuccessResponse();

        void onUploadFailureResponse();
    }
}
