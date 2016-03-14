package com.foxlee.module;

/**
 * Created by Administrator on 2016/3/14.
 */
public class FragmentModule extends BaseModule{

    public final static int NORMAL=0;
    public final static int LIST=1;

    public String presentername;
    public String modulemodulename;
    public int type=1;
    public String[] items;
    public String[] defaultitems;
    public String itemname;

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



        str.append("@Bind(R.id.tv_title)\n");
        str.append("TextView tvTitle;\n");
        str.append("@Bind(R.id.iv_back)\n");
        str.append("ImageView ivBack;\n");
        str.append("@Bind(R.id.iv_right)\n");
        str.append("ImageView ivRight;\n\n");

        switch (type){
            case LIST:
                str.append("@Bind(R.id.lv_");
                str.append(l_modulename);
                str.append(")\n");
                str.append("ListView lv");
                str.append(modulename);
                str.append(";\n");
                str.append("MyAdapter adapter;\n");
                break;
            default:
                break;
        }

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

//        ivBack.setVisibility(View.GONE);
//        tvTitle.setText(getString(R.string.main_tab_me));
//        ivRight.setImageResource(R.drawable.ic_add);

        str.append("@Override");
        str.append("\n");
        str.append("protected void init() {\n");
        str.append("ivBack.setVisibility(View.GONE);\n");
        str.append("tvTitle.setText(\"\");\n");
        str.append("ivRight.setImageResource(R.drawable.ic_icon);\n");


//        ArrayList<MeModule> list=new ArrayList<MeModule>();
//        for (int i = 0; i < 10; i++) {
//            MeModule meModule=new MeModule();
//            meModule.time="2015.08.09 9:00";
//            meModule.title="我第一次去厦门";
//            list.add(meModule);
//        }
//        adapter = new MyAdapter(this.getActivity(), list, new MultiItemTypeSupport<MeModule>() {
//            @Override
//            public int getLayoutId(int position, MeModule messageBean) {
//                if(messageBean.itemType==0)
//                    return R.layout.listitem_me_image;
//                return R.layout.listitem_no_data;
//            }
//
//            @Override
//            public int getViewTypeCount() {
//                return 2;
//            }
//
//            @Override
//            public int getItemViewType(int position, MeModule messageBean) {
//                if(messageBean.itemType==0)
//                    return 0;
//                return 1;
//            }
//        });
//        lvMe.setAdapter(adapter);
//        adapter.notifyDataSetChanged();

        switch (type){
            case LIST:
                str.append("ArrayList<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append("> list=new ArrayList<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append(">();\n");
                str.append("for (int i = 0; i < 10; i++) {\n");
                str.append(modulename);
                str.append(modulemodulename);
                str.append(" ");
                str.append(l_modulename);
                str.append(modulemodulename);
                str.append("=new ");
                str.append(modulename);
                str.append(modulemodulename);
                str.append("();\n");
                str.append("list.add(");
                str.append(l_modulename);
                str.append(modulemodulename);
                str.append(");\n");
                str.append("}\n");


                str.append("adapter = new MyAdapter(this.getActivity(), list, new MultiItemTypeSupport<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append(">() {\n\n");
                str.append("@Override\n");
                str.append("public int getLayoutId(int position, ");
                str.append(modulename);
                str.append(modulemodulename);
                str.append(" messageBean) {\n");

                if(items.length==0){
                    

                }else{

                }

                str.append("\n");
                str.append("\n");
                str.append("\n");
                str.append("\n");
                str.append("\n");
                break;
            default:
                break;
        }

        str.append("}");
        str.append("\n");



        switch (type){
            case LIST:
                str.append("class MyAdapter extends CommonAdapter<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append("> {\n\n");
                str.append("public MyAdapter(Context context, List<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append("> datas, MultiItemTypeSupport<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append("> multiItemTypeSupport) {\n");
                str.append("super(context, datas, multiItemTypeSupport);\n");
                str.append("}\n\n");
                str.append("public void setData(List<");
                str.append(modulename);
                str.append(modulemodulename);
                str.append("> datas) {\n");
                str.append(" mDatas = datas;\n");
                str.append("}\n\n");
                str.append("@Override\n");
                str.append("public void convert(int position, CommonHolder holder, ");
                str.append(modulename);
                str.append(modulemodulename);
                str.append(" item) {\n");
                str.append(" switch (holder.mLayoutId) {\n");
                for (int i = 0; i < items.length; i++) {
                    str.append("case R.layout.");
                    str.append(itemname);
                    str.append(l_modulename);
                    str.append("_");
                    str.append(items[i]);
                    str.append(":\n");
                    str.append("break;\n");
                }
                str.append("case R.layout.listitem_no_data:\n");
                str.append("break;\n");
                str.append("}\n");
                str.append("}\n");
                str.append("}\n");
                break;
            default:
                break;
        }

        str.append("}");
        content=str.toString();
    }
}
