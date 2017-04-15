package wincity.litao.com.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import wincity.litao.com.R;

/**
 * Created by Chenlu on 2016/6/29 0029.
 *
 * @ClassName: ImageUtils
 * @Description: 图片展示工具类
 */

public class ImageUtils {
    private static final String TAG = "ImageUtils";

    /*************************
     * 加载图片
     ********************************/

    /*
    加载失败后默认显示的图片资源
     */
    public static int errorResId = R.mipmap.ic_launcher;


    /**
     * 从本地存储或网络加载图片
     *
     * @param context
     * @param imageView
     * @param pathOrUrl    可以是本地文件的路径、网络图片的url、uri
     * @param defaultResId 获取图片失败时显示的默认图片
     */
    public static void loadImageByString(Context context, ImageView imageView, String pathOrUrl, int defaultResId) {
        Glide.with(context).load(pathOrUrl).diskCacheStrategy(DiskCacheStrategy.ALL).error(defaultResId).crossFade().into(imageView);
    }

    /**
     * @param context
     * @param imageView
     * @param pathOrUrl
     * @param defaultResId
     * @param placeholder  占位图
     */
    public static void loadImageByString(Context context, ImageView imageView, String pathOrUrl, int defaultResId, int placeholder) {
        Glide.with(context).load(pathOrUrl).diskCacheStrategy(DiskCacheStrategy.ALL).error(defaultResId).placeholder(placeholder).crossFade().into(imageView);
    }


    public static void loadImageByString(Context context, ImageView imageView, String pathOrUrl) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        Glide.with(context).load(pathOrUrl).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }

    public static void loadImageByBitmap(Context context, ImageView imageView, Bitmap bitmap) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        //           bitmap.recycle();

        Glide.with(context).load(bytes).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param imageView
     * @param radiusDp  圆角的半径 （单位：dp）
     * @param url
     */
    public static void loadRoundImage(Context context, ImageView imageView, int radiusDp, String url) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .centerCrop()
                .transform(new GlideRoundTransform(context, radiusDp))
                .crossFade()
                .into(imageView);
    }


    public static void loadRoundImage(Context context, ImageView imageView, int radiusDp, int resId) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .centerCrop()
                .transform(new GlideRoundTransform(context, radiusDp))
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param imageView
     * @param resId
     */
    public static void loadCircleImage(Context context, ImageView imageView, int resId) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        Glide.with(context)
                .load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(resId)
                .fitCenter()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .into(imageView);



    }

    public static void loadCircleImage(Context context, ImageView imageView, String url, int defaultImgId) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        LogUtil.i(TAG, "loadCircleImage: url: " + url);
        DrawableRequestBuilder<String> builder = Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defaultImgId)
                .fitCenter()
                .centerCrop()
                .transform(new GlideCircleTransform(context))
                .error(defaultImgId)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        //                        e.printStackTrace();
                        LogUtil.i(TAG, "onException model ==" + model + " isFirstResource ==" + isFirstResource);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        LogUtil.i(TAG, "onResourceReady model ==" + model + " isFromMemoryCache ==" + isFromMemoryCache + " isFirstResource ==" + isFirstResource);
                        return false;
                    }
                })
                .crossFade();
        if (url != null && url.startsWith("content://")) {
            builder = builder.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        builder.into(imageView);


    }


    public static void loadCircleImage(Context context, ImageView imageView, Uri url, int defaultImgId) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        DrawableRequestBuilder<Uri> builder = Glide.with(imageView.getContext())
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(defaultImgId)
                .fitCenter()
                .centerCrop()
                .transform(new GlideCircleTransform(imageView.getContext()))
                .error(defaultImgId)
                .crossFade();
        if (url != null && url.toString().startsWith("content://")) {
            builder = builder.skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE);
        }
        builder.into(imageView);


    }


    public static void loadImageNoCache(Context context, ImageView imageView, String url, int defaultImgId) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        Glide.with(context)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(defaultImgId)
                .fitCenter()
                .centerCrop()
                .error(defaultImgId)
                .crossFade(500)
                .into(imageView);
    }

    public static void loadImage(Context context, ImageView imageView, String url, int defaultImgId) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (activity.isDestroyed())
                    return;
            } else {
                if (activity.isFinishing())
                    return;
            }
        }
        Glide.with(context)
                .load(url)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(defaultImgId)
                .fitCenter()
                .centerCrop()
                .error(defaultImgId)
                .crossFade(500)
                .into(imageView);
    }


    /******************************图片属性处理******************************************/

    /**
     * 读取图片属性：旋转的角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation =
                    exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;

    }

    /**
     * 旋转图片一定角度
     *
     * @param angle
     * @param bitmap
     * @return
     */
    public static Bitmap rotateBitmap(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        //如果返回的不是同一个bitmap对象，则将原来的回收掉
        if (resizedBitmap != bitmap) {
            bitmap.recycle();
        }
        return resizedBitmap;
    }

    /**
     * 将原图片按指定的宽高压缩
     *
     * @param filePath
     * @param reqHeight
     * @param reqWidth
     * @return
     * @throws IOException
     * @Description:压缩图片
     */
    public static Bitmap compressSource(String filePath, int reqHeight, int reqWidth) throws IOException {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = getSampleSize(options, reqHeight, reqWidth);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    /**
     * @param options
     * @param reqHeight
     * @param reqWidth
     * @return
     * @Description:获取图片压缩的比率
     */
    private static int getSampleSize(BitmapFactory.Options options, int reqHeight, int reqWidth) {
        int height = options.outHeight;
        int width = options.outWidth;
        int sampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            int heightRatio = Math.round((float) height / (float) reqHeight);
            int widthRatio = Math.round((float) width / (float) reqWidth);
            sampleSize = heightRatio > widthRatio ? widthRatio : heightRatio;
        }
        return sampleSize;
    }

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    public static Bitmap getFirstFrameBitmapFromVideo(String videpPath) {
        return ThumbnailUtils.createVideoThumbnail(videpPath, MediaStore.Images.Thumbnails.MINI_KIND);
    }
}
