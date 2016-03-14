package com.foxlee.module;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/14.
 */
public class ModuleModule extends BaseModule{

    public ArrayList<ModuleItem> list;

    public ModuleModule(){

    }

    public ModuleModule(String path, String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
//        package com.dici.firstime.modules;
//
//
//        import com.dici.firstime.mvp.BaseModel;
//
///**
// * Created by hehuajia on 15/8/21.
// */
//        public class MeModule extends BaseModel {
//            public int itemType;
//            public String title;
//            public String time;
//        }

        StringBuilder str=new StringBuilder();
        str.append("package ");
        str.append(packagename);
        str.append(";");
        str.append("\n\n");

        str.append("import ");
        str.append(mvpPackage);
        str.append(".BaseModel");
        str.append(";");
        str.append("\n\n");

        str.append("public class ");
        str.append(modulename);
        str.append(fragmentname);
        str.append(" extends BaseModel {");
        str.append("\n");

        ModuleItem item=null;
        for (int i = 0; i < list.size(); i++) {
            item=list.get(i);
            str.append("\tpublic ");
            str.append(item.type);
            str.append(" ");
            str.append(item.value);
            str.append(";\n");
        }

        str.append("}");
        content=str.toString();
    }
}
