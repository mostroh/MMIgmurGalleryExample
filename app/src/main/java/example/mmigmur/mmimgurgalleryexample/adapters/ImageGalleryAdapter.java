package example.mmigmur.mmimgurgalleryexample.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.viewmodel.ImageViewModel;

/**
 * Created by migarcma on 17/3/18.
 */

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.ImageViewHolder>  {

    private List<ImageViewModel> galleryList;

    private Context mContext;

    private OnImageClickedListener onImageClickedListener;

    public ImageGalleryAdapter(Context context, List<ImageViewModel> galleryList, OnImageClickedListener onImageClickedListener) {
        mContext = context;
        this.galleryList = galleryList;
        this.onImageClickedListener = onImageClickedListener;
    }

    @Override
    public ImageGalleryAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.image_item_view, parent, false);
        ImageGalleryAdapter.ImageViewHolder viewHolder = new ImageGalleryAdapter.ImageViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageGalleryAdapter.ImageViewHolder holder, int position) {

        ImageViewModel imageViewModel = galleryList.get(position);
        ImageView imageView = holder.mPhotoImageView;

        Picasso.get().load(imageViewModel.getLink())
                .resize(200, 200)
                .centerCrop()
                .into(imageView);
    }

    @Override
    public int getItemCount() {
        return (galleryList!=null ? galleryList.size(): 0);
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView mPhotoImageView;

        public ImageViewHolder(View itemView) {

            super(itemView);
            mPhotoImageView = itemView.findViewById(R.id.iv_photo);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int position = getAdapterPosition();
            if(position != RecyclerView.NO_POSITION) {
                ImageViewModel imageViewModel = galleryList.get(position);

                if (onImageClickedListener!=null){
                    onImageClickedListener.imageClicked(imageViewModel);
                }
            }
        }
    }

    public List<ImageViewModel> getGalleryList() {
        return galleryList;
    }

    public void setGalleryList(List<ImageViewModel> galleryList) {
        this.galleryList = galleryList;
    }

    public interface OnImageClickedListener{
        void imageClicked(ImageViewModel imageViewModelClicked);
    }

}
