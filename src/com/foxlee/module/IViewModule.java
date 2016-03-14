package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class IViewModule extends BaseModule{

    public IViewModule(){

    }

    public IViewModule(String path, String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
//        package com.dici.firstime.iviews;
//
//
//        import com.dici.firstime.mvp.MvpView;
//
///**
// * Created by hehuajia on 15/8/21.
// */
//        public interface IMeView extends MvpView {
//        }

        StringBuilder str=new StringBuilder();
        str.append("package ");
        str.append(packagename);
        str.append(";");
        str.append("\n\n");

        str.append("import ");
        str.append(mvpPackage);
        str.append(".MvpView");
        str.append(";");
        str.append("\n\n");

        str.append("public interface I");
        str.append(modulename);
        str.append(fragmentname);
        str.append(" extends MvpView {");
        str.append("\n\n");
        str.append("}");
        content=str.toString();
    }
}
