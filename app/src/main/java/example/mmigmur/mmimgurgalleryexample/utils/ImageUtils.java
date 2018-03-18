package example.mmigmur.mmimgurgalleryexample.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by migarcma on 26/1/17.
 */

public class ImageUtils {


        public static Bitmap scaleDown(Bitmap realImage, float maxImageSize,
                                       boolean filter) {
            float ratio = Math.min(
                    (float) maxImageSize / realImage.getWidth(),
                    (float) maxImageSize / realImage.getHeight());
            int width = Math.round((float) ratio * realImage.getWidth());
            int height = Math.round((float) ratio * realImage.getHeight());

            return Bitmap.createScaledBitmap(realImage, width, height, filter);
        }



        public static String bitmapToB64(Bitmap bitmap){

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream .toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        public static String pathToB64(String path){

            Bitmap bm = BitmapFactory.decodeFile(path);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
            byte[] b = baos.toByteArray();

            return Base64.encodeToString(b, Base64.DEFAULT);
        }


        public static Bitmap b64ToBitmap(String b64){

            if(b64 == null){
                return null;
            }

            byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);

            Bitmap bmNotScaled = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
            if (bmNotScaled!=null){
                return scaleDown(bmNotScaled, 512, true);
            } else {
                return null;
            }

        }

        public static boolean checkImageInCache(String fileName, Context context){

            String image = getCachePath(context) + File.separator + fileName;
            File file = new File(image);
            return file.exists() && file.canRead();
        }

        public static File getImageCache(Context context, String fileName){
            String image = getCachePath(context) + File.separator + fileName;
            return new File(image);
        }


        public static boolean saveImageBitmap(Bitmap bitmap, String fileName, Context context){

            boolean status = false;

            FileOutputStream out = null;
            try {

                File file = new File(getCachePath(context) + File.separator + fileName);
                if(file.exists()){
                    file.delete();
                }

                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (out != null) {
                        out.close();
                        status = true;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return status;
        }

        public static String getCachePath(Context context){
            if(context.getExternalCacheDir() != null) {
                return context.getExternalCacheDir().getAbsolutePath();
            }

            return null;
        }


}
