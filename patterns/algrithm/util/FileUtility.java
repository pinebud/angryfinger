package util;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class FileUtility {

	public static boolean copyDir(File srcdir, File destdir) {
		if (srcdir == null || destdir == null)
			return false;

		if (!srcdir.exists()) {
			System.out.println("Please specify a existed directory");
			return false;
		}
		if (!srcdir.isDirectory()) {
			System.out.println("Please specify a directory, but not a file");
			return false;
		}
		if (!destdir.exists() && !destdir.mkdirs()) {
			System.out.println("Please specify a valid directory name");
			return false;
		}

		Stack<File> stack = new Stack<File>();
		String parentPath = destdir.getAbsolutePath();
		stack.push(srcdir);
		while (!stack.empty()) {
			File node = stack.pop();
			File target = new File(parentPath + File.separator + node.getName());
			target.mkdir();
			parentPath = target.getAbsolutePath();
			for (File file : node.listFiles()) {
				if (file.isDirectory()) {
					stack.push(file);
				}
				if (file.isFile()) {
					copy(file, new File(target.getAbsolutePath()
							+ File.separator + file.getName()));
				}
			}
		}
		return true;
	}

	/**
	 * This function can just be used to copy file
	 */
	public static boolean copy(File source, File dest) {
		if (source == null || dest == null || !source.exists())
			return false;
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			if (!dest.getParentFile().exists())
				dest.getParentFile().mkdirs();
			if (!dest.exists())
				dest.createNewFile();
			if (dest.isFile()) {
				fr = new FileReader(source);
				fw = new FileWriter(dest);
				br = new BufferedReader(fr);
				bw = new BufferedWriter(fw);
				String data = null;
				while ((data = br.readLine()) != null) {
					bw.write(data);
					bw.write(System.getProperty("line.separator"));
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static Object[] readFileAsIntegerArray(File source){
		if (source == null || !source.exists())
			return new Integer[0];
		List<Integer> list = new ArrayList<Integer>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			
				fr = new FileReader(source);
				br = new BufferedReader(fr);
				String data = "";
				while ((data = br.readLine()) != null) {
					String trimdata = data.trim();
					if(!trimdata.equals(""))
						list.add(Integer.valueOf(data));
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list.toArray();
	}

	/**
	 * This function can just be used to copy file
	 */
	public static String readFileAsString(File source) {
		if (source == null || !source.exists())
			return "";
		String str = "";
		FileReader fr = null;
		BufferedReader br = null;
		try {
			
				fr = new FileReader(source);
				br = new BufferedReader(fr);
				String data = "";
				while ((data = br.readLine()) != null) {
					str+=data+"\n\r";
				}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fr != null)
					fr.close();
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * 
	 */
	public static boolean appendLine(File dest, String newline) {
		if (dest == null)
			return false;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			if (!dest.getParentFile().exists())
				dest.getParentFile().mkdirs();
			if (!dest.exists())
				dest.createNewFile();
			if (dest.isFile()) {
				fw = new FileWriter(dest, true);
				bw = new BufferedWriter(fw);
				bw.write(newline+"\n");
//				bw.write(newline+System.getProperty("Line.Separater"));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public static boolean cut(File source, File directory, String destname) {
		if (source == null || directory == null || destname == null)
			return false;
		copy(source, new File(directory.getAbsoluteFile() + File.separator
				+ destname));
		return source.delete();
	}

	public static boolean cut(File source, File directory) {
		return true;
	}

	
	/**
	 * 
	 * @param folder
	 * @param postfix: .combined
	 * @return
	 */
	public static File zipFolder(File folder, String postfix){
		if(!folder.isDirectory()){
			return null;
		}
		File zipFile = null;
		if(postfix!=null)
			zipFile = new File(folder.getAbsolutePath()+postfix);
		else
			zipFile = new File(folder.getAbsolutePath()+".zip");
		final int BUFFER = 4096;
		try {
            BufferedInputStream origin = null;
            FileOutputStream dest = new FileOutputStream(zipFile);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(
                    dest));
            byte data[] = new byte[BUFFER];
            Stack<File> stack = new Stack<File>();
            stack.push(folder);
            while(!stack.empty()){
            	File ele = stack.pop();
            	File files[] = ele.listFiles();
            	for(File file: files){
            	if(file.isDirectory())
            	{
            		stack.push(file);         		
            	}else{
            		FileInputStream fi = new FileInputStream(file);
                    origin = new BufferedInputStream(fi, BUFFER);
                    ZipEntry entry = new ZipEntry(file.getAbsolutePath().replace(folder.getParent()+File.separator, ""));
                    out.putNextEntry(entry);
                    int count;
                    while ((count = origin.read(data, 0, BUFFER)) != -1) {
                        out.write(data, 0, count);
                    }
                    origin.close();	
            	}
            	}
            }           
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return zipFile;
	}
	
	public void unzip(File destFile){
		 
		 final int BUFFER = 2048;
		 try {
		            String fileName = destFile.getAbsolutePath();
		            String filePath = fileName+".zip";
		            ZipFile zipFile = new ZipFile(fileName);
		            Enumeration emu = zipFile.entries();
		            int i=0;
		            while(emu.hasMoreElements()){
		                ZipEntry entry = (ZipEntry)emu.nextElement();
		                if (entry.isDirectory())
		                {
		                    new File(filePath + entry.getName()).mkdirs();
		                    continue;
		                }
		                BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));
		                File file = new File(filePath + entry.getName());
		                File parent = file.getParentFile();
		                if(parent != null && (!parent.exists())){
		                    parent.mkdirs();
		                }
		                FileOutputStream fos = new FileOutputStream(file);
		                BufferedOutputStream bos = new BufferedOutputStream(fos,BUFFER);           
		                
		                int count;
		                byte data[] = new byte[BUFFER];
		                while ((count = bis.read(data, 0, BUFFER)) != -1)
		                {
		                    bos.write(data, 0, count);
		                }
		                bos.flush();
		                bos.close();
		                bis.close();
		            }
		            zipFile.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }

	}
	
	public static boolean deleteFileEntityRecurtively(File destFile){
		if(!destFile.isDirectory()){
			return destFile.delete();
		}
		Stack<File> stack = new Stack<File>();
		stack.push(destFile);
		while(!stack.empty()){
			File ele = stack.pop();
			File[] files = ele.listFiles();
			for(File file:files){
				if(file.isDirectory()){
					stack.push(file);
				}
				else{
					if(!file.delete())
						return false;
				}
			}			
		}
		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(deleteFileEntityRecurtively(new File("D:\\case3")));
	}

}
