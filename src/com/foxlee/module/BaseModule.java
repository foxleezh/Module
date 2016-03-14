package com.foxlee.module;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2016/3/14.
 */
public abstract class BaseModule {
    public String filename;
    public String path;
    public String content;
    public String mvpPackage;
    public String packagename;
    public String fragmentname;

    public String modulename;
    public String l_modulename;
    public boolean isActive;

    public abstract void initContent();

    public void write() {
        initContent();
        File filepath=new File(path);
        File file=new File(path,filename);
        if(!filepath.exists()){
            filepath.mkdirs();
        }
        if(file.exists()){
            file.delete();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(file);
            fos.write(content.getBytes("utf-8"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
