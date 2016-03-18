package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class FragmentItemModule extends BaseModule{

    public final static int TEXT_IMAGE=0;
    public final static int TEXT=1;

    public int type;


    public FragmentItemModule(){

    }

    public FragmentItemModule(String path, String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
        StringBuilder str=new StringBuilder();

        switch (type){
            case TEXT_IMAGE:
                str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<RelativeLayout\n" +
                        "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "    xmlns:skin=\"http://schemas.android.com/android/skin\"\n" +
                        "    android:layout_width=\"fill_parent\"\n" +
                        "    android:layout_height=\"wrap_content\"\n" +
                        "    android:background=\"@drawable/tr_listview_touchback\"\n" +
                        "    android:gravity=\"center_vertical\"\n" +
                        "    android:orientation=\"horizontal\"\n" +
                        "    skin:enable=\"true\">\n" +
                        "\n" +
                        "    <TextView\n" +
                        "        android:id=\"@+id/tv_name\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"@dimen/tr_item_height\"\n" +
                        "        android:layout_weight=\"1\"\n" +
                        "        android:gravity=\"center_vertical\"\n" +
                        "        android:paddingLeft=\"@dimen/tr_main_h_padding\"\n" +
                        "        android:paddingRight=\"@dimen/tr_main_h_padding\"\n" +
                        "        android:textColor=\"@color/sk_main_text\"\n" +
                        "        android:textSize=\"@dimen/tr_sub_text_size\"\n" +
                        "        skin:enable=\"true\">\n" +
                        "    </TextView>\n" +
                        "    <ImageView\n" +
                        "        android:id=\"@+id/iv_arrow\"\n" +
                        "        android:layout_width=\"@dimen/tr_item_height\"\n" +
                        "        android:layout_height=\"@dimen/tr_item_height\"\n" +
                        "        android:layout_alignParentRight=\"true\"\n" +
                        "        android:layout_marginRight=\"4dp\"\n" +
                        "        android:padding=\"5dp\"\n" +
                        "        />\n" +
                        "\n" +
                        "    <View style=\"@style/tr_line\"\n" +
                        "          android:background=\"@color/sk_card_line\"\n" +
                        "          skin:enable=\"true\"/>\n" +
                        "\n" +
                        "</RelativeLayout>");
                break;
            default:
                str.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                        "<RelativeLayout\n" +
                        "    xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                        "    xmlns:skin=\"http://schemas.android.com/android/skin\"\n" +
                        "    android:layout_width=\"fill_parent\"\n" +
                        "    android:layout_height=\"wrap_content\"\n" +
                        "    android:background=\"@drawable/tr_listview_touchback\"\n" +
                        "    android:gravity=\"center_vertical\"\n" +
                        "    android:orientation=\"horizontal\"\n" +
                        "    skin:enable=\"true\">\n" +
                        "\n" +
                        "    <TextView\n" +
                        "        android:id=\"@+id/tv_name\"\n" +
                        "        android:layout_width=\"wrap_content\"\n" +
                        "        android:layout_height=\"@dimen/tr_item_height\"\n" +
                        "        android:layout_weight=\"1\"\n" +
                        "        android:gravity=\"center_vertical\"\n" +
                        "        android:paddingLeft=\"@dimen/tr_main_h_padding\"\n" +
                        "        android:paddingRight=\"@dimen/tr_main_h_padding\"\n" +
                        "        android:textColor=\"@color/sk_main_text\"\n" +
                        "        android:textSize=\"@dimen/tr_sub_text_size\"\n" +
                        "        skin:enable=\"true\">\n" +
                        "    </TextView>\n" +
                        "    \n" +
                        "    <View style=\"@style/tr_line\"\n" +
                        "          android:background=\"@color/sk_card_line\"\n" +
                        "          skin:enable=\"true\"/>\n" +
                        "\n" +
                        "</RelativeLayout>");
                break;
        }
        content=str.toString();
    }
}
