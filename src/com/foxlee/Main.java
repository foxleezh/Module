/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foxlee;

import com.foxlee.module.FragmentItemModule;
import com.foxlee.module.FragmentLayoutModule;
import com.foxlee.module.FragmentModule;
import com.foxlee.util.TypedValue;

import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.xml.parsers.ParserConfigurationException;

public class Main {
    /**
     * 运行的路径，.jar的路径
     */
    public static String mRunningLocation;
    private static final String ARG_HELP = "--help";
    private static final String ARG_ROOTDIR = "-rootdir";
    private static final String ARG_NAME = "-name";


    protected Configuration mConfiguration;
    protected File configFile;
    public String rootDir = "D:\\application\\Dici\\";        //要打包的工程路径
    public String configDir = rootDir + "tools\\";        //要打包的工程路径
    public String moduleName = "RecommendClose";


//    public String rootDir = "";        //要打包的工程路径
//    public String configDir = rootDir+"tools\\";        //要打包的工程路径
//    public String moduleName="";

    public static void main(String[] args) {
        Main main = new Main();
        getRunningLocation(main);
        main.run(args);
    }

    void run(String[] args) {
        parseArgs(args);
        readConfig();
        createModule();
    }

    private void createModule() {
        if (mConfiguration.fragmentModule.isActive) {
            mConfiguration.fragmentModule.presentername = mConfiguration.presenterModule.fragmentname;
            mConfiguration.fragmentModule.modulemodulename = mConfiguration.moduleModule.fragmentname;
            mConfiguration.fragmentModule.write();
            if (mConfiguration.fragmentModule.type == FragmentModule.LIST) {
                for (int i = 0; i < mConfiguration.fragmentModule.listItem.undefaultitems.size(); i++) {
                    FragmentItemModule itemModule = new FragmentItemModule();
                    itemModule.modulename = moduleName;
                    itemModule.path = mConfiguration.fragmentLayoutModule.path;
                    itemModule.l_modulename = moduleName.toLowerCase();
                    itemModule.filename = mConfiguration.fragmentModule.listItem.undefaultitems.get(i) + ".xml";
                    itemModule.write();
                }
            }
        }

        if (mConfiguration.presenterModule.isActive) {
            mConfiguration.presenterModule.iviewname = mConfiguration.iViewModule.fragmentname;
            mConfiguration.presenterModule.iviewpath = mConfiguration.iViewModule.packagename;

            mConfiguration.presenterModule.modulemodulename = mConfiguration.moduleModule.fragmentname;
            mConfiguration.presenterModule.modulemodulepath = mConfiguration.moduleModule.packagename;

            mConfiguration.presenterModule.write();
        }

        if (mConfiguration.iViewModule.isActive) {
            mConfiguration.iViewModule.write();
        }

        if (mConfiguration.moduleModule.isActive) {
            mConfiguration.moduleModule.write();
        }

        if (mConfiguration.fragmentLayoutModule.isActive) {
            if (mConfiguration.fragmentModule.type == FragmentModule.LIST) {
                mConfiguration.fragmentLayoutModule.type= FragmentLayoutModule.LIST;
                mConfiguration.fragmentLayoutModule.listItem= mConfiguration.fragmentModule.listItem;
            }
            mConfiguration.fragmentLayoutModule.write();
        }
    }

    private static void getRunningLocation(Main m) {
        mRunningLocation = m.getClass().getProtectionDomain().getCodeSource()
                .getLocation().getPath();
        try {
            mRunningLocation = URLDecoder.decode(mRunningLocation, "utf-8");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mRunningLocation.endsWith(".jar")) {
            mRunningLocation = mRunningLocation.substring(0,
                    mRunningLocation.lastIndexOf(File.separator) + 1);
        }
        File f = new File(mRunningLocation);
        mRunningLocation = f.getAbsolutePath();
    }

    private void readConfig() {
        if (configFile == null) {
            configFile = new File(configDir + TypedValue.CONFIG_FILE);
            if (!configFile.exists()) {
                System.err.printf("the config file %s does not exit", configFile.getAbsolutePath());
                usage();
            }
        }

        System.out.printf("resourceprpguard begin\n");

        mConfiguration = new Configuration(configFile, this);

        try {
            mConfiguration.readConfig();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            usage();
        } catch (ParserConfigurationException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            usage();
        } catch (SAXException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            usage();
        }

    }

    private void parseArgs(String[] args) {
        int index;

        for (index = 0; index < args.length; index++) {
            String arg = args[index];

            if (arg.equals(ARG_HELP)) {
                usage();
                break;
            } else if (arg.equals(ARG_ROOTDIR)) {
                if (index == args.length - 1) {
                    System.err.println("Missing rootdir argument");
                    usage();
                }
                rootDir = args[++index];
            } else if (arg.equals(ARG_NAME)) {
                if (index == args.length - 1) {
                    System.err.println("Missing name argument");
                    usage();
                }
                moduleName = args[++index];
            }
        }
    }

    private void usage() {
        System.err.print(
                "使用须知：\n" +
                        "1.在release.gradle的buildAPK中配置好对应目录\n"
        );
    }

}
