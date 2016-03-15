
package com.foxlee;

import com.foxlee.module.FragmentLayoutModule;
import com.foxlee.module.FragmentModule;
import com.foxlee.module.IViewModule;
import com.foxlee.module.ModuleItem;
import com.foxlee.module.ModuleModule;
import com.foxlee.module.PresenterModule;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * @author shwenzhang
 */
public class Configuration {

    protected static final String TAG_ISSUE = "issue";
    protected static final String ATTR_VALUE = "value";
    protected static final String ATTR_NAME = "name";
    protected static final String ATTR_TYPE = "type";
    protected static final String TAG_PATH = "path";
    protected static final String TAG_MVP = "mvp";
    protected static final String TAG_FILE = "file";
    protected static final String TAG_ITEM = "item";
    protected static final String ATTR_DEFAULT_ITEM = "defualtitem";
    protected static final String ATTR_LIST_STYLE = "list_style";
    protected static final String ATTR_ID = "id";

    protected static final String ATTR_ACTIVE = "isactive";

    protected static final String PROPERTY_ISSUE = "property";
    protected static final String FRAGMENT_ISSUE = "fragment";
    protected static final String PRESENTER_ISSUE = "presenter";
    protected static final String IVIEW_ISSUE = "iview";
    protected static final String FRAGMENT_LAYOUT_ISSUE = "layout_fragment";
    protected static final String MODULE_ISSUE = "module";
    public boolean libenable;

    private Main mClient;

    private final File mConfigFile;


    public boolean mUseChannel;
    public ArrayList<String> mChannel;
    public ArrayList<String> mlibClass;
    public String moduleName;
    public String rootDir;
    public String srcDir;
    public String resDir;
    public String mvpPackage;
    public FragmentModule fragmentModule = new FragmentModule();
    public PresenterModule presenterModule = new PresenterModule();
    public IViewModule iViewModule = new IViewModule();
    public FragmentLayoutModule fragmentLayoutModule = new FragmentLayoutModule();
    public ModuleModule moduleModule = new ModuleModule();

    public Configuration(File config, Main m) {
        // TODO Auto-generated constructor stub
        mConfigFile = config;
        mClient = m;
        moduleName = m.moduleName;
        rootDir = m.rootDir;
        srcDir = rootDir + "src/";
        resDir = rootDir + "res/";
    }

    public File getConfigFile() {
        return mConfigFile;
    }


    private String convetToPatternString(String input) {
        //将.换成\\.
        if (input.contains(".")) {
            input = input.replaceAll("\\.", "\\\\.");
        }
        //将？换成.,将*换成.*
        if (input.contains("?")) {
            input = input.replaceAll("\\?", "\\.");
        }

        if (input.contains("*")) {
            input = input.replace("*", ".+");
        }

        return input;
    }


    public void readConfig() throws IOException, ParserConfigurationException, SAXException {
//		System.out.println(configFile.getAbsolutePath());
        if (!mConfigFile.exists()) {
            return;
        }
        System.out.printf("reading config file, %s\n", mConfigFile.getAbsolutePath());

        BufferedInputStream input = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            input = new BufferedInputStream(new FileInputStream(mConfigFile));
            InputSource source = new InputSource(input);
            factory.setNamespaceAware(false);
            factory.setValidating(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(source);
            NodeList issues = document.getElementsByTagName(TAG_ISSUE);
//            System.out.println(issues.getLength());
            for (int i = 0, count = issues.getLength(); i < count; i++) {
                Node node = issues.item(i);

                Element element = (Element) node;
                String id = element.getAttribute(ATTR_ID);
                String isActive = element.getAttribute(ATTR_ACTIVE);
                if (id.length() == 0) {
                    System.err.println("Invalid config file: Missing required issue id attribute");
                    continue;
                }

                boolean active = isActive != null ? isActive.equals("true") : false;

                if (id.equals(PROPERTY_ISSUE)) {
                    readProperty(node);
                } else if (id.equals(FRAGMENT_ISSUE)) {
                    fragmentModule.isActive = active;
                    if (active) {
                        readFragment(node);
                    }
                } else if (id.equals(PRESENTER_ISSUE)) {
                    presenterModule.isActive = active;
                    if (active) {
                        readPresenter(node);
                    }
                } else if (id.equals(IVIEW_ISSUE)) {
                    iViewModule.isActive = active;
                    if (active) {
                        readIView(node);
                    }
                } else if (id.equals(FRAGMENT_LAYOUT_ISSUE)) {
                    fragmentLayoutModule.isActive = active;
                    if (active) {
                        readFragmentLayout(node);
                    }
                } else if (id.equals(MODULE_ISSUE)) {
                    moduleModule.isActive = active;
                    if (active) {
                        readModule(node);
                    }
                }
            }
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
//					e.printStackTrace();
                    System.exit(-1);
                }
            }
        }
    }


    private void readProperty(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            for (int j = 0, n = childNodes.getLength(); j < n; j++) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element check = (Element) child;
                    String tagname = check.getTagName();
                    String value = check.getAttribute(ATTR_VALUE);
                    if (value.length() == 0) {
                        throw new IOException("Invalid config file: Missing required attribute " + ATTR_VALUE);
                    }
                    //先去掉空格
                    value = value.replace(" ", "");
                    if (TAG_MVP.equals(tagname)) {
                        mvpPackage = value;
                    }
                }
            }
        }
    }

    private String getLowerName(String param){
        if (param==null||"".equals(param.trim())){
            return "";
        }
        int len=param.length();
        StringBuilder sb=new StringBuilder(len);
        sb.append(Character.toLowerCase(param.charAt(0)));
        for (int i = 1; i < len; i++) {
            char c=param.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void readFragment(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            for (int j = 0, n = childNodes.getLength(); j < n; j++) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element check = (Element) child;
                    String tagname = check.getTagName();
                    String value = check.getAttribute(ATTR_VALUE);
                    if (value.length() == 0) {
                        throw new IOException("Invalid config file: Missing required attribute " + ATTR_VALUE);
                    }
                    //先去掉空格
                    value = value.replace(" ", "");
                    if (TAG_PATH.equals(tagname)) {
                        String path = value.replace(".", "/");
                        fragmentModule.path = srcDir + path;
                        fragmentModule.packagename = value;
                        fragmentModule.modulename = mClient.moduleName;
                        fragmentModule.l_modulename = getLowerName(mClient.moduleName);
                        fragmentModule.mvpPackage = mvpPackage;
                    } else if (TAG_FILE.equals(tagname)) {
                        fragmentModule.filename = moduleName + value + ".java";
                        fragmentModule.fragmentname = value;
                    } else if (ATTR_TYPE.equals(tagname)) {
                        if ("List".equals(value)) {
                            fragmentModule.type = FragmentModule.LIST;
                            fragmentModule.listItem.itemname = check.getAttribute(ATTR_NAME);
                            fragmentModule.listItem.stylename = check.getAttribute(ATTR_LIST_STYLE);
                            String item = check.getAttribute(TAG_ITEM);
                            String[] defaultitems = check.getAttribute(ATTR_DEFAULT_ITEM).split(";");
                            if ("".equals(item)) {
                                fragmentModule.listItem.items.add(fragmentModule.listItem.itemname + fragmentModule.l_modulename);
                                fragmentModule.listItem.undefaultitems.add(fragmentModule.listItem.itemname + fragmentModule.l_modulename);
                            } else {
                                String[] items = item.split(";");
                                for (int i = 0; i < items.length; i++) {
                                    fragmentModule.listItem.items.add(fragmentModule.listItem.itemname + fragmentModule.l_modulename + "_" + items[i]);
                                    fragmentModule.listItem.undefaultitems.add(fragmentModule.listItem.itemname + fragmentModule.l_modulename + "_" + items[i]);
                                }
                            }
                            for (int i = 0; i < defaultitems.length; i++) {
                                fragmentModule.listItem.items.add(fragmentModule.listItem.itemname + defaultitems[i]);
                            }
                        }
                    }
                }
            }
        }
    }

    private void readPresenter(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            for (int j = 0, n = childNodes.getLength(); j < n; j++) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element check = (Element) child;
                    String tagname = check.getTagName();
                    String value = check.getAttribute(ATTR_VALUE);
                    if (value.length() == 0) {
                        throw new IOException("Invalid config file: Missing required attribute " + ATTR_VALUE);
                    }
                    //先去掉空格
                    value = value.replace(" ", "");
                    if (TAG_PATH.equals(tagname)) {
                        String path = value.replace(".", "/");
                        presenterModule.path = srcDir + path;
                        presenterModule.packagename = value;
                        presenterModule.modulename = mClient.moduleName;
                        presenterModule.l_modulename = getLowerName(mClient.moduleName);
                        presenterModule.mvpPackage = mvpPackage;
                    } else if (TAG_FILE.equals(tagname)) {
                        presenterModule.filename = moduleName + value + ".java";
                        presenterModule.fragmentname = value;
                    }
                }
            }
        }
    }

    private void readIView(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            for (int j = 0, n = childNodes.getLength(); j < n; j++) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element check = (Element) child;
                    String tagname = check.getTagName();
                    String value = check.getAttribute(ATTR_VALUE);
                    if (value.length() == 0) {
                        throw new IOException("Invalid config file: Missing required attribute " + ATTR_VALUE);
                    }
                    //先去掉空格
                    value = value.replace(" ", "");
                    if (TAG_PATH.equals(tagname)) {
                        String path = value.replace(".", "/");
                        iViewModule.path = srcDir + path;
                        iViewModule.packagename = value;
                        iViewModule.modulename = mClient.moduleName;
                        iViewModule.l_modulename = getLowerName(mClient.moduleName);
                        iViewModule.mvpPackage = mvpPackage;
                    } else if (TAG_FILE.equals(tagname)) {
                        iViewModule.filename = "I" + moduleName + value + ".java";
                        iViewModule.fragmentname = value;
                    }
                }
            }
        }
    }

    private void readFragmentLayout(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            for (int j = 0, n = childNodes.getLength(); j < n; j++) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element check = (Element) child;
                    String tagname = check.getTagName();
                    String value = check.getAttribute(ATTR_VALUE);
                    if (value.length() == 0) {
                        throw new IOException("Invalid config file: Missing required attribute " + ATTR_VALUE);
                    }
                    //先去掉空格
                    value = value.replace(" ", "");
                    if (TAG_PATH.equals(tagname)) {
                        fragmentLayoutModule.path = resDir + value;
                        fragmentLayoutModule.modulename = mClient.moduleName;
                        fragmentLayoutModule.l_modulename = getLowerName(mClient.moduleName);
                    } else if (TAG_FILE.equals(tagname)) {
                        fragmentLayoutModule.filename = value + getLowerName(moduleName) + ".xml";
                    }
                }
            }
        }
    }

    private void readModule(Node node) throws IOException {
        NodeList childNodes = node.getChildNodes();
        if (childNodes.getLength() > 0) {
            ArrayList<ModuleItem> list = new ArrayList<>();
            for (int j = 0, n = childNodes.getLength(); j < n; j++) {
                Node child = childNodes.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    Element check = (Element) child;
                    String tagname = check.getTagName();
                    String value = check.getAttribute(ATTR_VALUE);
                    if (value.length() == 0) {
                        throw new IOException("Invalid config file: Missing required attribute " + ATTR_VALUE);
                    }
                    //先去掉空格
                    value = value.replace(" ", "");
                    if (TAG_PATH.equals(tagname)) {
                        String path = value.replace(".", "/");
                        moduleModule.path = srcDir + path;
                        moduleModule.packagename = value;
                        moduleModule.modulename = mClient.moduleName;
                        moduleModule.l_modulename = getLowerName(mClient.moduleName);
                        moduleModule.mvpPackage = mvpPackage;
                    } else if (TAG_FILE.equals(tagname)) {
                        moduleModule.filename = moduleName + value + ".java";
                        moduleModule.fragmentname = value;
                    } else if (TAG_ITEM.equals(tagname)) {
                        String type = check.getAttribute(ATTR_TYPE);
                        ModuleItem item = new ModuleItem();
                        item.type = type;
                        item.value = value;
                        list.add(item);
                    }
                }
            }
            moduleModule.list = list;
        }
    }

}

