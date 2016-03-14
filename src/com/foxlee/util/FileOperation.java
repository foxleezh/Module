
package com.foxlee.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileOperation {
	public static final boolean fileExists(String filePath) {
		if (filePath == null) {
			return false;
		}

		File file = new File(filePath);
		if (file.exists())
			return true;
		return false;
	}

	public static final boolean deleteFile(String filePath) {
		if (filePath == null) {
			return true;
		}

		File file = new File(filePath);
		if (file.exists()) {
			return file.delete();
		}
		return true;
	}

	/**
	 * 移动指定文件夹内的全部文件
	 *
	 * @param fromDir
	 * 要移动的文件目录
	 * @param toDir
	 * 目标文件目录
	 * @throws Exception
	 */
	public static void fileMove(String from, String to) {
		try {
			File dir = new File(from);
// 文件一览
			File[] files = dir.listFiles();
			if (files == null)
				return;
// 目标
			File moveDir = new File(to);
			if (!moveDir.exists()) {
				moveDir.mkdirs();
			}
// 文件移动
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					fileMove(files[i].getPath(), to + "\\" + files[i].getName());
// 成功，删除原文件
					files[i].delete();
				}
				File moveFile = new File(moveDir.getPath() + "\\"
						+ files[i].getName());
// 目标文件夹下存在的话，删除
				if (moveFile.exists()) {
					moveFile.delete();
				}
				files[i].renameTo(moveFile);
			}
			dir.delete();
		} catch (Exception e) {
			throw e;
		}
	}


//	public static void copyDirectiory(String sourceDir,String targetDir,boolean overlay) throws IOException {
//
//		//新建目标目录
//
//		(new File(targetDir)).mkdirs();
//
//		//获取源文件夹当下的文件或目录
//		File[] file = (new File(sourceDir)).listFiles();
//
//		for (int i = 0; i < file.length; i++) {
//			if (file[i].isFile()) {
//				//源文件
//				File sourceFile = file[i];
//				//目标文件
//				File targetFile = new File(new File(targetDir).getAbsolutePath() + File.separator + file[i].getName());
//
//				copyFile(sourceFile, targetFile, overlay);
//
//			}
//
//
//			if (file[i].isDirectory()) {
//				//准备复制的源文件夹
//				String dir1 = sourceDir + file[i].getName();
//				//准备复制的目标文件夹
//				String dir2 = targetDir + "/" + file[i].getName();
//
//				copyDirectiory(dir1, dir2, overlay);
//			}
//		}
//	}


	public static boolean copyFile(File srcFile, File destFile,
								   boolean overlay) {

		// 判断源文件是否存在
		if (!srcFile.exists()) {
			return false;
		} else if (!srcFile.isFile()) {
			return false;
		}

		// 判断目标文件是否存在
		if (destFile.exists()) {
			// 如果目标文件存在并允许覆盖
			if (overlay) {
				// 删除已经存在的目标文件，无论目标文件是目录还是单个文件
				destFile.delete();
			}
		} else {
			// 如果目标文件所在目录不存在，则创建目录
			if (!destFile.getParentFile().exists()) {
				// 目标文件所在目录不存在
				if (!destFile.getParentFile().mkdirs()) {
					// 复制文件失败：创建目标文件所在目录失败
					return false;
				}
			}
		}

		// 复制文件
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(destFile);
			byte[] buffer = new byte[1024];

			while ((byteread = in.read(buffer)) != -1) {
				out.write(buffer, 0, byteread);
			}
			return true;
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 复制文件
//	public static void copyFile(File sourceFile,File targetFile)
//			throws IOException{
//		// 新建文件输入流并对它进行缓冲
//		FileInputStream input = new FileInputStream(sourceFile);
//		BufferedInputStream inBuff=new BufferedInputStream(input);
//
//		// 新建文件输出流并对它进行缓冲
//		FileOutputStream output = new FileOutputStream(targetFile);
//		BufferedOutputStream outBuff=new BufferedOutputStream(output);
//
//		// 缓冲数组
//		byte[] b = new byte[1024 * 5];
//		int len;
//		while ((len =inBuff.read(b)) != -1) {
//			outBuff.write(b, 0, len);
//		}
//		// 刷新此缓冲的输出流
//		outBuff.flush();
//
//		//关闭流
//		inBuff.close();
//		outBuff.close();
//		output.close();
//		input.close();
//	}
	// 复制文件夹
	public static void copyDirectiory(String sourceDir, String targetDir)
			 {
		// 新建目标目录
		(new File(targetDir)).mkdirs();
		// 获取源文件夹当前下的文件或目录
		File[] file = (new File(sourceDir)).listFiles();
		for (int i = 0; i < file.length; i++) {
			if (file[i].isFile()) {
				// 源文件
				File sourceFile=file[i];
				// 目标文件
				File targetFile=new
						File(new File(targetDir).getAbsolutePath()
						+File.separator+file[i].getName());
				copyFile(sourceFile,targetFile,true);
			}
			if (file[i].isDirectory()) {
				// 准备复制的源文件夹
				String dir1=sourceDir + "/" + file[i].getName();
				// 准备复制的目标文件夹
				String dir2=targetDir + "/"+ file[i].getName();
				copyDirectiory(dir1, dir2);
			}
		}
	}

	public static List<String> getFileList(String dir) {
		List<String> listFile = new ArrayList<>();
		File dirFile = new File(dir);
		//如果不是目录文件，则直接返回
		if (dirFile.isDirectory()) {
			//获得文件夹下的文件列表，然后根据文件类型分别处理
			File[] files = dirFile.listFiles();
			if (null != files && files.length > 0) {
				for (File file : files) {
					//如果不是目录，直接添加
					if (!file.isDirectory()) {
						listFile.add(file.getName());
					} else {
						//对于目录文件，递归调用
						listFile.addAll(getFileList(file.getAbsolutePath()));
					}
				}
			}
		}
		return listFile;
	}

	public static boolean move(String origin,String dest) {
		File afile = new File(origin);
		FileOperation.deleteFile(dest);
		if (afile.renameTo(new File(dest))) {
			System.out.println("move success --> "+dest);
			return true;
		} else {
			System.out.println("move failed! --> "+dest);
			return false;
		}
	}

	public static long getlist(File f){
		if (f == null || (!f.exists())) {
			return 0;
		}
		if (!f.isDirectory()) {
			return 1;
		}
        long size = 0;
        File flist[] = f.listFiles();
        size = flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;
    }
	
	public static long getFileSizes(File f) {
        long size = 0;
        if (f.exists() && f.isFile()) {
            FileInputStream fis = null;
            try {
				fis = new FileInputStream(f);
				size = fis.available();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
        } 
        return size ;
    }

	public static final boolean deleteDir(File file) {
		if (file == null || (!file.exists())) {
			return false;
		}
		if (file.isFile()) {
			file.delete();
		} else if (file.isDirectory()) {
			File files[] = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		file.delete();
		return true;
	}

	
	private static final int BUFFER = 8192;
    
	public static void copyFileUsingStream(File source, File dest) throws IOException {
		FileInputStream  is = null;
		FileOutputStream  os = null;
		File parent = dest.getParentFile();    
        if(parent != null && (!parent.exists())){    
            parent.mkdirs();    
        }  
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest, false);

	        byte[] buffer = new byte[BUFFER];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
    		is.close();
    		os.close();
	    }
	}
    
	public static boolean checkDirectory(String dir) {
        File dirObj = new File(dir);
        deleteDir(dirObj);
        
        if (!dirObj.exists()) {
            dirObj.mkdirs();
        } 
        return true;
    }
    
    public static File checkFile(String dir) {
        deleteFile(dir);
        File file = new File(dir);
        try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return file;
    }
    
    
    @SuppressWarnings("rawtypes")  
    public static  HashMap<String, Integer> unZipAPk(String fileName, String filePath) throws IOException {    
    	checkDirectory(filePath);
        ZipFile zipFile = new ZipFile(fileName);     
		Enumeration emu = zipFile.entries();  
        HashMap<String, Integer> compress = new HashMap<String, Integer>(); 
        while(emu.hasMoreElements()){    
             ZipEntry entry = (ZipEntry) emu.nextElement();    
             if (entry.isDirectory()){    
                 new File(filePath+ File.separator + entry.getName()).mkdirs();    
                 continue;    
             }    
             BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));    
             
             File file = new File(filePath + File.separator + entry.getName());   
     		
             File parent = file.getParentFile();    
             if(parent != null && (!parent.exists())){    
                 parent.mkdirs();    
             }    
             //要用linux的斜杠
             String compatibaleresult = new String(entry.getName());
             if (compatibaleresult.contains("\\")) {
//         		 System.out.printf("name %s, compress  %d\n",entry.getName(), entry.getMethod());
            	 compatibaleresult = compatibaleresult.replace("\\", "/");
			 }
             compress.put(compatibaleresult, entry.getMethod());
//     		 System.out.printf("name %s, compress  %d\n",entry.getName(), entry.getMethod());

             FileOutputStream fos = new FileOutputStream(file);    
             BufferedOutputStream bos = new BufferedOutputStream(fos, BUFFER);    
         
             byte [] buf = new byte[BUFFER];    
             int len = 0;    
             while((len=bis.read(buf,0,BUFFER))!=-1){    
                 fos.write(buf,0,len);    
             }    
             bos.flush();    
             bos.close();    
             bis.close();    
            }    
            zipFile.close();    
            return compress;
     }    
    
    /**
	 * zip list of file
	 * 
	 * @param resFileList
	 *            file(dir) list
	 * @param zipFile
	 *            output zip file
	 * @throws IOException
	 * 
	 */
	public static void zipFiles(Collection<File> resFileList, File zipFile, HashMap<String, Integer> compressData) throws IOException {
		ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), BUFFER));
		for (File resFile : resFileList) {
			if (resFile.exists()) {
				zipFile(resFile, zipout, "", compressData);
			}
		}
		zipout.close();
	}

	private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath, HashMap<String, Integer> compressData) throws IOException {
		rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator) + resFile.getName();
		if (resFile.isDirectory()) {
			File[] fileList = resFile.listFiles();
			for (File file : fileList) {
				zipFile(file, zipout, rootpath, compressData);
			}
		} else {
//			byte buffer[] = new byte[BUFFER];
			final byte[] fileContents = readContents(resFile);
			//这里需要强转成linux格式，果然坑！！
			if (rootpath.contains("\\")) {
				rootpath = rootpath.replace("\\", "/");
			}
//			BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile), BUFFER);
			if (!compressData.containsKey(rootpath)) {
				throw new IOException(String.format(
						"do not have the compress data path=%s",
						rootpath));
				
			}
			
			int compressMethod = compressData.get(rootpath);
			
			
			ZipEntry entry = new ZipEntry(rootpath);
			
			if (compressMethod == ZipEntry.DEFLATED) {
				entry.setMethod(ZipEntry.DEFLATED);
			} else {
				entry.setMethod(ZipEntry.STORED);
				entry.setSize(fileContents.length);
				final CRC32 checksumCalculator = new CRC32();
				checksumCalculator.update(fileContents);
				entry.setCrc(checksumCalculator.getValue());
			}
			
//    		System.out.printf("name %s, compress  %d\n",entry.getName(), entry.getMethod());

			zipout.putNextEntry(entry);
			zipout.write(fileContents);
//			int realLength;
//			while ((realLength = in.read(buffer)) != -1) {
//				zipout.write(buffer, 0, realLength);
//			}
//			in.close();
			zipout.flush();
			zipout.closeEntry();
		}
	}
	
	private static byte[] readContents(final File file) throws IOException {
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		final int bufferSize = 4096;
		try {
			final FileInputStream in = new FileInputStream(file);
			final BufferedInputStream bIn = new BufferedInputStream(in);
			int length = 0;
			byte[] buffer = new byte[bufferSize];
			byte[] bufferCopy = new byte[bufferSize];
			while ((length = bIn.read(buffer, 0, bufferSize)) != -1) {
				bufferCopy = new byte[length];
				System.arraycopy(buffer, 0, bufferCopy, 0, length);
				output.write(bufferCopy);
			}
			bIn.close();
		} finally {
			output.close();
		}

		return output.toByteArray();
	}
}
