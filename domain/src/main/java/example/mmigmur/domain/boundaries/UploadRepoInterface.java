package example.mmigmur.domain.boundaries;

import example.mmigmur.domain.model.Image;
import example.mmigmur.domain.model.UploadImage;

/**
 * Created by migarcma on 17/3/18.
 */

public interface UploadRepoInterface {

    void uploadImage(UploadImage uploadImageDto,
                          UploadImageRepoListener uploadImageRepoListener);

    interface UploadImageRepoListener {

        void onUploadSuccess(Image uploadedImage);
    }
}
