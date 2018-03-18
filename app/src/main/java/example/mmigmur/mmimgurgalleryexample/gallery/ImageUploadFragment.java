package example.mmigmur.mmimgurgalleryexample.gallery;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import example.mmigmur.mmimgurgalleryexample.R;
import example.mmigmur.mmimgurgalleryexample.base.BaseFragment;
import example.mmigmur.mmimgurgalleryexample.di.activity.ActivityComponent;
import example.mmigmur.mmimgurgalleryexample.di.fragment.BaseFragmentModule;
import example.mmigmur.mmimgurgalleryexample.utils.DialogManager;
import example.mmigmur.mmimgurgalleryexample.utils.ImagePicker;
import example.mmigmur.mmimgurgalleryexample.utils.ImageUtils;

/**
 * Created by migarcma on 18/3/18.
 */

public class ImageUploadFragment extends BaseFragment implements ImageUploadView, AdapterView.OnItemClickListener {


    /**
     * Tag name
     */
    public static final String TAG = ImageUploadFragment.class.getName();


    private static final int REQUEST_CODE_SOME_FEATURES_PERMISSIONS = 200;
    private static final int REQUEST_IMAGE_GALLERY = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int GALLERY = 0;
    private static final int CAMERA = 1;
    private static final int CANCEL = 2;
    private static final int REQUEST_CODE = 123;

    @Inject
    UploadPresenter presenter;

    @BindView(R.id.iv_image_placeholder)
    ImageView ivPreview;

    @BindView(R.id.button_confirm_upload)
    Button bConfirmUpload;

    @BindView(R.id.et_title)
    EditText etTitle;

    @BindView(R.id.et_description)
    EditText etDescription;

    @BindString(R.string.MSG_UPLOADED)
    String msgUploaded;

    private DialogManager.MenuOptionsAdapter adapter;

    private OnImageUploadListener onImageUploadListener;

    private String selectedImagePath = null;

    private String selectedImageb64 = null;

    public static ImageUploadFragment newInstance(OnImageUploadListener onImageUploadListener) {

        Bundle args = new Bundle();

        ImageUploadFragment fragment = new ImageUploadFragment();
        fragment.setArguments(args);
        fragment.onImageUploadListener = onImageUploadListener;
        return fragment;
    }

    @Override
    public void initViews() {
        int[] iconList = new int[]{R.mipmap.ic_gallery, R.mipmap.ic_camera, R.mipmap.ic_close};
        CharSequence[] imageType = getResources().getTextArray(R.array.image_types);
        adapter = new DialogManager.MenuOptionsAdapter(imageType, iconList, getActivity());
    }

    @Override
    protected int getXMLToInflate() {
        return R.layout.fragment_image_upload;
    }

    @Override
    protected void initPresenter() {
        presenter.init();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void initDIComponent() {
        fragmentComponent = (getParentComponent(ActivityComponent.class)).with(new BaseFragmentModule(this));
        fragmentComponent.inject(this);
    }

    @Override
    public void hideKeyboard() {
        if (getActivity() != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void showLoading() {
        DialogManager.getProgressDialog(getActivity());
    }

    @Override
    public void hideLoading() {
        DialogManager.hideCurrentDialog();
    }

    @Override
    public void showErrorUploading() {
        DialogManager.showSnackBarMessage(getView(), getString(R.string.ERROR_UPLOADING), Snackbar.LENGTH_LONG);
    }

    @OnClick(R.id.iv_image_placeholder)
    void selectImage(){
        hideKeyboard();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestWriteStoragePermissions();
            }
            else{
                DialogManager.getBottomOptionMenu(getActivity(), adapter, this);
            }
        }
    }

    @OnClick(R.id.button_confirm_upload)
    void uploadSelectedImage(){
        String title = etTitle.getText().toString();
        String description = etDescription.getText().toString();
        presenter.uploadImage(selectedImageb64,title, description);
    }

    private void requestWriteStoragePermissions() {

        List<String> permissions = new ArrayList<>();

        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        requestPermissions(permissions.toArray(new String[permissions.size()]), REQUEST_CODE_SOME_FEATURES_PERMISSIONS);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {

            case GALLERY:
                dispatchOpenGalleryIntent();
                break;

            case CAMERA:
                dispatchTakePictureIntent();
                break;

            default:
            case CANCEL:
                DialogManager.hideCurrentDialog();
                break;
        }
    }

    private void dispatchTakePictureIntent() {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE);
            }
            else{
                openCamera();
            }
        }
        else{
            openCamera();
        }


    }

    private void dispatchOpenGalleryIntent() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_IMAGE_GALLERY);
        }

        DialogManager.hideCurrentDialog();
    }

    private void openCamera(){

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = ImagePicker.createImageFile();
                selectedImagePath = "file:" + photoFile.getAbsolutePath();
            } catch (IOException ex) {
                // Error occurred while creating the File
                Log.i("", "IOException");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        }

        DialogManager.hideCurrentDialog();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        Bitmap bmPhoto = null;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK && selectedImagePath != null) {

            Log.d("Camera result","Estoy en foto");
            try {

                bmPhoto = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(selectedImagePath));
                bmPhoto = ImageUtils.scaleDown(bmPhoto,512,true);
                updateImage(bmPhoto);
                selectedImageb64 = ImageUtils.bitmapToB64(bmPhoto);
                bConfirmUpload.setEnabled(true);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_IMAGE_GALLERY && resultCode == Activity.RESULT_OK && data != null) {

            Log.d("Gallery result","Estoy en galeria");
            bmPhoto = ImagePicker.getImageFromResult(getActivity(),resultCode ,data);
            bmPhoto = ImageUtils.scaleDown(bmPhoto,512,true);
            updateImage(bmPhoto);
            selectedImageb64 = ImageUtils.bitmapToB64(bmPhoto);
            bConfirmUpload.setEnabled(true);
        }


    }

    @Override
    public void updateImage(Bitmap bmPhoto) {
        ivPreview.setImageBitmap(bmPhoto);
    }

    @Override
    public void notifyUploadSuccess() {
        Toast.makeText(getActivity(),msgUploaded,Toast.LENGTH_SHORT).show();
        if (onImageUploadListener!=null){
            onImageUploadListener.imageUploaded();
            getActivity().onBackPressed();
        }
    }

    public interface OnImageUploadListener{

        void imageUploaded();
    }
}
