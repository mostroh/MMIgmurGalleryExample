package example.mmigmur.data.services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import example.mmigmur.data.di.BaseRepositoryComponent;
import example.mmigmur.data.entities.ImageEntity;
import example.mmigmur.data.entities.mapper.ImageToImageEntityMapper;
import example.mmigmur.data.entities.response.DeleteResponse;
import example.mmigmur.data.entities.response.GalleryResponse;
import example.mmigmur.data.entities.response.UploadImageResponse;
import example.mmigmur.data.network.ImgurApiInterface;
import example.mmigmur.domain.boundaries.GalleryRepoInterface;
import example.mmigmur.domain.model.Image;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by migarcma on 18/3/18.
 */

public class GalleryService implements GalleryRepoInterface {

    @Inject
    ImgurApiInterface imgurApiInterface;


    public GalleryService(BaseRepositoryComponent baseRepositoryComponent) {
        baseRepositoryComponent.inject(this);
    }

    @Override
    public void getAccountGallery(String refreshToken, String accessToken, final GalleryResponseListener galleryResponseListener) {
        imgurApiInterface.getAccountImages("Bearer "+accessToken)
                .enqueue(new Callback<GalleryResponse>() {
                    @Override
                    public void onResponse(Call<GalleryResponse> call, Response<GalleryResponse> response) {
                        if (response.isSuccessful()){
                            List<Image> gallery =  new ArrayList<>();
                            List<ImageEntity> galleryEntity = response.body().getData();
                            if (galleryEntity !=null && !galleryEntity.isEmpty()){
                                ImageToImageEntityMapper imageToImageEntityMapper = new ImageToImageEntityMapper();
                                for (ImageEntity imageEntity: galleryEntity){
                                    Image image = imageToImageEntityMapper.reverseMap(imageEntity);
                                    gallery.add(image);
                                }
                            }
                            galleryResponseListener.onAccountGallerySuccesResponse(gallery);

                        } else {
                            galleryResponseListener.onAccountGalleryFailure();
                        }
                    }

                    @Override
                    public void onFailure(Call<GalleryResponse> call, Throwable t) {
                        galleryResponseListener.onAccountGalleryFailureConnection();
                    }
                });
    }

    @Override
    public void deleteImage(String deleteHash, String accessToken, final DeleteResponseListener deleteResponseListener) {
        imgurApiInterface.deleteImage("Bearer "+accessToken, deleteHash)
                .enqueue(new Callback<DeleteResponse>() {

                    @Override
                    public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                        if (response.isSuccessful()){
                            deleteResponseListener.onDeleteSuccessResponse();
                        } else {
                            deleteResponseListener.onDeleteFailureResponse();
                        }
                    }

                    @Override
                    public void onFailure(Call<DeleteResponse> call, Throwable t) {
                        deleteResponseListener.onDeleteFailureResponse();
                    }
                });
    }

    @Override
    public void uploadImage(String b64Image, String title, String description, String accessToken, final UploadResponseListener uploadResponseListener) {
        imgurApiInterface.postImage("Bearer "+accessToken,b64Image,title,description)
                .enqueue(new Callback<UploadImageResponse>() {
                    @Override
                    public void onResponse(Call<UploadImageResponse> call, Response<UploadImageResponse> response) {
                        if (response.isSuccessful()){
                            uploadResponseListener.onUploadSuccessResponse();
                        } else {
                            uploadResponseListener.onUploadFailureResponse();
                        }
                    }

                    @Override
                    public void onFailure(Call<UploadImageResponse> call, Throwable t) {
                        uploadResponseListener.onUploadFailureResponse();
                    }
                });
    }
}
