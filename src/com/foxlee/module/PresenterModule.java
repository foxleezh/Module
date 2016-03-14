package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class PresenterModule extends BaseModule{

    public String iviewname;
    public String iviewpath;
    public String modulemodulename;
    public String modulemodulepath;

    public PresenterModule(){

    }

    public PresenterModule(String path, String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
        StringBuilder str=new StringBuilder();

//        package com.dici.firstime.presenters;
//
//        import android.content.Context;
//
//        import com.dici.firstime.iviews.IMeView;
//        import com.dici.firstime.modules.MeModule;
//        import com.dici.firstime.mvp.MvpBasePresenter;
//
//
///**
// * Created by hehuajia on 15/8/21.
// */
//        public class MePrecenter extends MvpBasePresenter<IMeView,MeModule> {
//            /**
//             * must be Application context
//             *
//             * @param context must be Application context
//             */
//            public MePrecenter(Context context) {
//                super(context);
//            }
//
//        }


        str.append("package ");
        str.append(packagename);
        str.append(";");
        str.append("\n\n");

        str.append("import android.content.Context;");
        str.append("\n");

        str.append("import ");
        str.append(iviewpath);
        str.append(".I");
        str.append(modulename);
        str.append(iviewname);
        str.append(";");
        str.append("\n");

        str.append("import ");
        str.append(modulemodulepath);
        str.append(".");
        str.append(modulename);
        str.append(modulemodulename);
        str.append(";");
        str.append("\n");

        str.append("import ");
        str.append(mvpPackage);
        str.append(".MvpBasePresenter");
        str.append(";");
        str.append("\n\n");

        str.append("public class ");
        str.append(modulename);
        str.append(fragmentname);
        str.append(" extends MvpBasePresenter<I");
        str.append(modulename);
        str.append(iviewname);
        str.append(",");
        str.append(modulename);
        str.append(modulemodulename);
        str.append("> {");
        str.append("\n\n\t");

        str.append("public ");
        str.append(modulename);
        str.append(fragmentname);
        str.append("(Context context) {");
        str.append("\n\t\t");
        str.append("super(context);");
        str.append("\n");
        str.append("\t}");
        str.append("\n\n");

        str.append("}");
        content=str.toString();
    }
}
