package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class FragmentModule extends BaseModule{

    public String presentername;

    public FragmentModule(){

    }

    public FragmentModule(String path,String filename){
        this.path=path;
        this.filename=filename;
    }

    @Override
    public void initContent() {
        StringBuilder str=new StringBuilder();
        str.append("package ");
        str.append(packagename);
        str.append(";");
        str.append("\n");


//        str.append("import ");
//        str.append(mvpPackage);
//        str.append(".R");
//        str.append(";");
//        str.append("\n");



        str.append("public class ");
        str.append(modulename);
        str.append(fragmentname);
        str.append(" extends BaseFragment<");
        str.append(modulename);
        str.append(presentername);
        str.append("> implements I");
        str.append(modulename);
        str.append("View {");
        str.append("\n\n");

//        @Override
//        protected int getLayoutRes() {
//            return R.layout.fragment_me;
//        }
//
//        @Override
//        public MePrecenter getPresenter() {
//            return new MePrecenter(this.getActivity().getApplicationContext());
//        }
//
//        @Override
//        protected MvpView getMvpView() {
//            return this;
//        }

        str.append("@Override");
        str.append("\n");
        str.append("protected int getLayoutRes() {");
        str.append("\n");
        str.append("return R.layout.fragment_");
        str.append(l_modulename);
        str.append(";");
        str.append("\n");
        str.append("}");
        str.append("\n\n");


        str.append("@Override");
        str.append("\n");
        str.append("public ");
        str.append(modulename);
        str.append(presentername);
        str.append(" get");
        str.append(presentername);
        str.append("(){");
        str.append("\n");
        str.append("return new ");
        str.append(modulename);
        str.append(presentername);
        str.append("(this.getActivity().getApplicationContext());");
        str.append("\n");
        str.append("}");
        str.append("\n\n");


        str.append("@Override");
        str.append("\n");
        str.append("protected MvpView getMvpView() {");
        str.append("\n");
        str.append("return this;");
        str.append("\n");
        str.append("}");
        str.append("\n\n");

//        @Override
//        protected void init() {


        str.append("@Override");
        str.append("\n");
        str.append("protected void init() {");
        str.append("\n");
        str.append("\n");
        str.append("}");
        str.append("\n");

        str.append("}");
        content=str.toString();
    }
}
