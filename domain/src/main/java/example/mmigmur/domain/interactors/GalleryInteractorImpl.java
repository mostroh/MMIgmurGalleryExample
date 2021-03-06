package example.mmigmur.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import example.mmigmur.domain.boundaries.AuthStorageRepoInterface;
import example.mmigmur.domain.boundaries.GalleryRepoInterface;
import example.mmigmur.domain.di.BaseInteractorComponent;
import example.mmigmur.domain.model.Image;

/**
 * Created by migarcma on 18/3/18.
 */

public class GalleryInteractorImpl implements GalleryInteractor {

    @Inject
    GalleryRepoInterface galleryRepoInterface;

    @Inject
    AuthStorageRepoInterface authStorageRepoInterface;

    public GalleryInteractorImpl(BaseInteractorComponent baseInteractorComponent) {
        baseInteractorComponent.inject(this);
    }

    @Override
    public void getAccountGallery(AccountGalleryInteractorResponse accountGalleryInteractorResponse) {
        AccountGalleryRunnable accountGalleryRunnable = new AccountGalleryRunnable(accountGalleryInteractorResponse);
        accountGalleryRunnable.run();
    }

    @Override
    public void deleteImage(String imageDeleteHash, ImageDeleteInteractorResponse imageDeleteInteractorResponse) {
        ImageDeleteRunnable imageDeleteRunnable = new ImageDeleteRunnable(imageDeleteHash, imageDeleteInteractorResponse);
        imageDeleteRunnable.run();
    }

    @Override
    public void uploadImage(String b64Image, String title, String description, ImageUploadInteractorResponse imageUploadInteractorResponse) {
        ImageUploadRunnable imageUploadRunnable = new ImageUploadRunnable(b64Image,title,description,imageUploadInteractorResponse);
        imageUploadRunnable.run();
    }

    private class AccountGalleryRunnable implements Runnable, GalleryRepoInterface.GalleryResponseListener {

        private AccountGalleryInteractorResponse accountGalleryInteractorResponse;

        private AccountGalleryRunnable(AccountGalleryInteractorResponse accountGalleryInteractorResponse) {
            this.accountGalleryInteractorResponse = accountGalleryInteractorResponse;
        }

        @Override
        public void run() {

            String refreshToken = authStorageRepoInterface.getRefreshToken();
            String accessToken = authStorageRepoInterface.getAccessToken();
            galleryRepoInterface.getAccountGallery(refreshToken, accessToken,this);
        }

        @Override
        public void onAccountGallerySuccesResponse(List<Image> gallery) {
            accountGalleryInteractorResponse.onAccountGallerySuccess(gallery);
        }

        @Override
        public void onAccountGalleryFailureConnection() {
            accountGalleryInteractorResponse.onAccountGalleryConnectionFailure();
        }

        @Override
        public void onAccountGalleryFailure() {
            accountGalleryInteractorResponse.onAccountGalleryFailure();
        }
    }

    private class ImageDeleteRunnable implements Runnable, GalleryRepoInterface.DeleteResponseListener {

        private String deleteHash;
        private ImageDeleteInteractorResponse imageDeleteInteractorResponse;

        private ImageDeleteRunnable(String deleteHash, ImageDeleteInteractorResponse imageDeleteInteractorResponse) {
            this.deleteHash = deleteHash;
            this.imageDeleteInteractorResponse = imageDeleteInteractorResponse;
        }

        @Override
        public void run() {
            String accessToken = authStorageRepoInterface.getAccessToken();
            galleryRepoInterface.deleteImage(deleteHash, accessToken,this);
        }


        @Override
        public void onDeleteSuccessResponse() {
            imageDeleteInteractorResponse.onDeleteSuccess();
        }

        @Override
        public void onDeleteFailureResponse() {
            imageDeleteInteractorResponse.onDeleteFailure();
        }
    }

    private class ImageUploadRunnable implements Runnable, GalleryRepoInterface.UploadResponseListener {

        private String b64Image;
        private String title;
        private String description;
        private ImageUploadInteractorResponse imageUploadInteractorResponse;

        private ImageUploadRunnable(String b64Image,
                                    String title,
                                    String description,
                                    ImageUploadInteractorResponse imageUploadInteractorResponse) {
            this.b64Image = b64Image;
            this.title = title;
            this.description = description;
            this.imageUploadInteractorResponse = imageUploadInteractorResponse;
        }

        @Override
        public void run() {
            String accessToken = authStorageRepoInterface.getAccessToken();
            galleryRepoInterface.uploadImage(b64Image,title, description, accessToken,this);
        }


        @Override
        public void onUploadSuccessResponse() {
            imageUploadInteractorResponse.onUploadSuccess();
        }

        @Override
        public void onUploadFailureResponse() {
            imageUploadInteractorResponse.onUploadFailure();
        }
    }
}
