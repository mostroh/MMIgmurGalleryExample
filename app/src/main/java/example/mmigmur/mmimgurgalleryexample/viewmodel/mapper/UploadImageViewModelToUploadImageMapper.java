package example.mmigmur.mmimgurgalleryexample.viewmodel.mapper;

import example.mmigmur.data.entities.mapper.Mapper;
import example.mmigmur.domain.model.UploadImage;
import example.mmigmur.mmimgurgalleryexample.viewmodel.UploadImageViewModel;

/**
 * Created by migarcma on 17/3/18.
 */

public class UploadImageViewModelToUploadImageMapper extends Mapper<UploadImageViewModel,UploadImage> {

    public UploadImageViewModelToUploadImageMapper() {
    }

    @Override
    public UploadImage map(UploadImageViewModel value) {
        UploadImage uploadImage = new UploadImage();
        uploadImage.setAlbumId(value.getAlbumId());
        uploadImage.setDescription(value.getDescription());
        uploadImage.setImage(value.getImage());
        uploadImage.setTitle(value.getTitle());

        return uploadImage;
    }

    @Override
    public UploadImageViewModel reverseMap(UploadImage value) {
        throw new UnsupportedOperationException();
    }
}
