package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class FragmentLayoutModule extends BaseModule{


    public FragmentLayoutModule(){

    }

    public FragmentLayoutModule(String path, String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
        StringBuilder str=new StringBuilder();
        str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");
        str.append("<LinearLayout xmlns:android=\"http://schemas.android.com/apk/res/android\"\n");
        str.append("android:layout_width=\"match_parent\"\n");
        str.append("android:layout_height=\"match_parent\"\n");
        str.append("android:background=\"@color/sk_whole_bg\">\n");
        str.append("\n");
        str.append("<include android:id=\"@+id/rlyt_title\" layout=\"@layout/titlebar_right\" />\n");
        str.append("</LinearLayout>");

        content=str.toString();
    }
}
