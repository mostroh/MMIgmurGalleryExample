package example.mmigmur.data.services;

import javax.inject.Inject;

import example.mmigmur.data.entities.UploadImageEntity;
import example.mmigmur.data.entities.mapper.UploadImageToUploadImageEntityMapper;
import example.mmigmur.data.network.ImgurApiInterface;
import example.mmigmur.domain.boundaries.UploadRepoInterface;
import example.mmigmur.domain.model.UploadImage;
import retrofit2.Retrofit;


public class UploadService implements UploadRepoInterface{

    @Inject
    ImgurApiInterface imgurApiInterface;

    @Inject
    Retrofit retrofit;

    UploadImageRepoListener uploadImageRepoListener;

    @Override
    public void uploadImage(UploadImage uploadImage, UploadImageRepoListener uploadImageRepoListener) {
        this.uploadImageRepoListener = uploadImageRepoListener;
        UploadImageToUploadImageEntityMapper uploadImageToUploadImageEntityMapper = new UploadImageToUploadImageEntityMapper();
        UploadImageEntity uploadImageEntity = uploadImageToUploadImageEntityMapper.map(uploadImage);
    }
}
