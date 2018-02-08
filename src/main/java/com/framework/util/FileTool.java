package com.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileTool {

	   
    public static boolean DeleteFolder(String sPath) {  
        Boolean flag = false;  
        File file = new File(sPath);  
        // 判断目录或文件是否存在  
        if (!file.exists()) {  // 不存在返回 false  
            return flag;  
        } else {  
            // 判断是否为文件  
            if (file.isFile()) {  // 为文件时调用删除文件方法  
                return deleteFile(sPath);  
            } else {  // 为目录时调用删除目录方法  
                return deleteDirectory(sPath);  
            }  
        }  
    }  
    
    public static boolean deleteFile(String sPath) {  
    	Boolean flag = false;  
    	File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }
    
    public static boolean deleteDirectory(String sPath) {  
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符  
        if (!sPath.endsWith(File.separator)) {  
            sPath = sPath + File.separator;  
        }  
        File dirFile = new File(sPath);  
        //如果dir对应的文件不存在，或者不是一个目录，则退出  
        if (!dirFile.exists() || !dirFile.isDirectory()) {  
            return false;  
        }  
        Boolean flag = true;  
        //删除文件夹下的所有文件(包括子目录)  
        File[] files = dirFile.listFiles();  
        for (int i = 0; i < files.length; i++) {  
            //删除子文件  
            if (files[i].isFile()) {  
                flag = deleteFile(files[i].getAbsolutePath());  
                if (!flag) break;  
            } //删除子目录  
            else {  
                flag = deleteDirectory(files[i].getAbsolutePath());  
                if (!flag) break;  
            }  
        }  
        if (!flag) return false;  
        //删除当前目录  
        if (dirFile.delete()) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
    
    
    public static boolean moveFile(String srcFile, String destPath){ 
    	// File (or directory) to be moved 
    	File file = new File(srcFile); 
    	if (!file.exists()||!file.isFile()) {  
    		return false;  
    	}
    	// Destination directory 
    	File dir = new File(destPath); 
    	if (!dir.exists() || !dir.isDirectory()) {  
            return false;  
        }
	  
    	// Move file to new directory 
    	boolean success = file.renameTo(new File(dir, file.getName())); 
    	file.delete();//删除 
    	return success; 
     } 
    /**
	 * 创建文件夹
	 * @param destDirName
	 * @return
	 * @author dingyulei
	 */
	public static boolean createDir(String destDirName) {  
		 File dir = new File(destDirName);  
		 if(dir.exists()) {  
		 return false;  
		 }  
		 if(!destDirName.endsWith(File.separator))  
		 destDirName = destDirName + File.separator;  
		 // 创建单个目录   
		return dir.mkdirs(); 
		}  

	 /**
	  * 保存文件到硬盘
	  * @param filename
	  * @param file
	  * @return
	  */
	 public static boolean saveFile(String filename,File file){
		 FileOutputStream fos;
			try {
				fos = new FileOutputStream(filename);
			
			FileInputStream fis = new FileInputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while((len=fis.read(buf))>0){
				fos.write(buf,0,len);
			}
			if (fis!=null)fis.close();
			if (fos!=null)fos.close();
			return true;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	 }
	 /**
	  * 文件删除
	  * @param file
	  */
	 public static boolean deleteFile(File file){ 
		   if(file.exists()){                    //判断文件是否存在
		    if(file.isFile()){                    //判断是否是文件
		     file.delete(); 
		     return true;//delete()方法 你应该知道 是删除的意思;
		    }else if(file.isDirectory()){              //否则如果它是一个目录
		     File files[] = file.listFiles();               //声明目录下所有的文件 files[];
		     for(int i=0;i<files.length;i++){            //遍历目录下所有的文件
		      deleteFile(files[i]);             //把每个文件 用这个方法进行迭代
		     } 
		    } 
		    file.delete(); 
		   }else{ 
		    System.out.println("所删除的文件不存在！"+'\n'); 
		    return false;
		   } 
		   return true;
		}
}
