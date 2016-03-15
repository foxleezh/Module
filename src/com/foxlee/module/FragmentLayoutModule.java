package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class FragmentLayoutModule extends BaseModule{

    public final static int NORMAL=0;
    public final static int LIST=1;

    public int type;
    public ListItem listItem;

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

//        <ListView
//        android:id="@+id/lv_me"
//        style="@style/list_style"
//        android:layout_width="match_parent"
//        android:layout_height="match_parent"
//        android:listSelector="#00000000"
//        android:scrollbars="none" />

        switch (type) {
            case LIST:
                str.append("<ListView\n");
                str.append("android:id=\"@+id/lv_");
                str.append(l_modulename);
                str.append("\"\n");
                if("".equals(listItem.stylename)){
                    str.append("android:layout_width=\"match_parent\"\n");
                    str.append("android:layout_height=\"match_parent\"\n");
                    str.append("android:listSelector=\"#00000000\"/>\n");
                }else{
                    str.append("style=\"@style/");
                    str.append(listItem.stylename);
                    str.append("\"/>\n");
                }
                break;
            default:
                break;
        }
        str.append("</LinearLayout>");
        content=str.toString();
    }
}
