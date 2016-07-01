package imbmob.test.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


/**
 * Created by Administrator on 2016/5/16.
 */
public class ImageManager {

    private static ImageManager imageManager;

    public ImageManager() {
    }

    public static ImageManager getInstace(){
        if(imageManager==null){
            synchronized (ImageManager.class){
                if(imageManager==null){
                    imageManager=new ImageManager();
                }
            }
        }
        return imageManager;
    }

    public void init(Context context){
        ImageLoaderConfiguration imageLoaderConfiguration=ImageLoaderConfiguration.createDefault(context);
        ImageLoader.getInstance().init(imageLoaderConfiguration);
    }

    public void displayImage(String url,ImageView imageView,int loading,int fail){
        ImageLoader.getInstance().displayImage(url, imageView, setOptions(loading, fail));
    }
    public void displayImage(String url,ImageView imageView){
        ImageLoader.getInstance().displayImage(url,imageView);
    }

    private DisplayImageOptions setOptions(int loading, int fail){
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(loading)
                .showImageOnFail(fail)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        return options;
    }




}
