/**
 * 
 */
package supercars.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


/**
 * @author James Schneider
 *
 */
public class FileUtils {

	public static final String DBL_BAK_SLASH = "\\" + "\\";
	public static final String DBL_FWD_SLASH = "//";
	
	public static final String SINGLE_BAK_SLASH = "\\";
	public static final String SINGLE_FWD_SLASH = "/";
	
	
	public static void main(String[] args) {
		String str;

		
		//str = FileUtils.parseDirectoryFromPath("C:/FH_WORKSPACE/RAFT/raft.xmr");
		
		//str = FileUtils.convertFileSystemPath("C:/FH_WORKSPACE/RAFT\\dddd\\");
		
		//String osName = System.getProperty("os.name");
		//System.out.println("os.name = " + osName);
		
		
		File theFile;
		
		str = "C:/XP/AA/xpe/bin";
		theFile = new File(str);
		if (theFile.isDirectory()) {
			System.out.println(str + " is a directory");
		} else {
			System.out.println(str + " is not a directory");
		}
		
		if (theFile.isFile()) {
			System.out.println(str + " is a file");
		} else {
			System.out.println(str + " is not a file");
		}

		str = "C:/XP/AA/xpe/bin/";
		theFile = new File(str);
		if (theFile.isDirectory()) {
			System.out.println(str + " is a directory");
		} else {
			System.out.println(str + " is not a directory");
		}
		
		if (theFile.isFile()) {
			System.out.println(str + " is a file");
		} else {
			System.out.println(str + " is not a file");
		}
		
		str = "C:/XP/AA/xpe/bin/fh_run_control";
		
		theFile = new File(str);
		boolean exists = theFile.exists();
		
		if (!exists) {
			try {
				if (FileUtils.isWindows()) {
					exists = theFile.createNewFile();
					FileUtils.hideWindowsFile(theFile);
					
				} else {
					String fileName = FileUtils.parseFileNameFromPath(str);
					if (fileName != null) {
						if (!fileName.startsWith(".")) {
							
						}
					}
				}

				

			} catch (Throwable ex) {
				
			}
		}
		
		if (exists) {
			System.out.println(str + " does exist");
		} else {
			System.out.println(str + " does not exist");
		}

		if (theFile.isHidden()) {
			System.out.println(str + " is hidden");
		} else {
			System.out.println(str + " is not hidden");
		}
		
		
//		if (theFile.isDirectory()) {
//			System.out.println(str + " is a directory");
//		} else {
//			System.out.println(str + " is not a directory");
//		}
//		
//		if (theFile.isFile()) {
//			System.out.println(str + " is a file");
//		} else {
//			System.out.println(str + " is not a file");
//		}
		
		
		//FileUtils.parseFileNameFromPath("C:\\temp\\logs\\*test.*log");

		//System.out.println("Path = " + str);

//		List<String> files = FileUtils.findMatchingFileNamesInDirectory("C:\\temp\\FlightHub_Utilities_Adapter-2.43.2.jar", true, false);
//		if (files != null) {
//			for (String fileName : files) {
//				System.out.println("fileName=" + fileName);
//			}
//		} else {
//			System.out.println("No matching files found");
//		}
	}

	public static void copyFile(String orgFilePath, String newFilePath) {
		
		FileChannel src = null;
		FileChannel dest = null;
		try {
			
			src = new FileInputStream(new File(FileUtils.convertFileSystemPath(orgFilePath))).getChannel();
			dest = new FileOutputStream(FileUtils.convertFileSystemPath(newFilePath)).getChannel();
			dest.transferFrom(src, 0, src.size());	
			
		} catch (Throwable ex){
			
		} finally {
			try {src.close();} catch (IOException ioe2){}
			try {dest.close();} catch (IOException ioe3){}
		}
	}
	
    // create a directory path - string may contain subdirectories
	public static boolean createDir(String aDir) {
 		boolean success = (new File(aDir)).mkdirs();
        return success;
    }

    // create a directory path - string may contain subdirectories
    static public File createDirectory(String aDir) {
    	File newDir = new File(aDir);
 		newDir.mkdirs();
        return newDir;
    }

    // delete a directory path - string may contain subdirectories
    // if not empty, directory and subdirectory contents will be deleted
    public static boolean deleteDir(File dir)
	{
		if (dir.isDirectory()) {
			String[] children = dir.list();
			for (int i=0; i<children.length; i++) {
				
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}

		// The directory is now empty so delete it
		return dir.delete();
	}


	// deletes a file or directory - if directory, it must be empty
    public static boolean deleteFile(String filePathName) {
		boolean success = (new File(filePathName)).delete();
		return success;
	}

	// renames a file
    public static boolean renameFile(String oldFileName, String newFileName) {
	  	boolean success = false;
	  	try {
	  		File fileToRename = new File(oldFileName);
	  		success = fileToRename.renameTo(new File(newFileName));
	  	} catch (Exception e){
	  	}
	  	return success;
	}
	
	// compare two files for identical content
    public static boolean compareFileContents (String firstFilePathName, String secondFilePathName, int skipLines) {
		boolean equalContent = true;
		int skipLineCount = 0;

		try {
			BufferedReader in1 = new BufferedReader(new FileReader(firstFilePathName));
			BufferedReader in2 = new BufferedReader(new FileReader(secondFilePathName));
			String str1;
			String str2;
			if (skipLines < 0) skipLines = 0; // protect ourselves
			while ((str1 = in1.readLine()) != null) {
				if ((str2 = in2.readLine()) != null) {
					if (skipLines > 0 && skipLineCount < skipLines){
						skipLineCount++;
					} else {
						if (str1.equalsIgnoreCase(str2) == false){
							equalContent = false;
							break;
						}
					}
				}
			}
			in1.close();
			in2.close();
		} catch (IOException e) {
		}
		return equalContent;
	}
	
	/**
	 * Replaces double slashes and single back slashes in the path
	 * with single forward slashes which works on most platforms.
	 *
	 * @param fileSystemPath
	 * @return
	 */
	public static String convertFileSystemPath(String fileSystemPath) {
				
		if (fileSystemPath.indexOf(DBL_BAK_SLASH) > -1) {
			// replace double back slash
			fileSystemPath = StringUtils.replaceAll(fileSystemPath, DBL_BAK_SLASH, File.separator);
		} else if (fileSystemPath.indexOf(DBL_FWD_SLASH) > -1) {
			// replace double forward slash
			fileSystemPath = StringUtils.replaceAll(fileSystemPath, DBL_FWD_SLASH, File.separator);
		}
		
		String wrongSlash = SINGLE_FWD_SLASH;
		
		// determine what is the wrong type of single slash to look for
		if (File.separator.equals(SINGLE_FWD_SLASH)) {
			wrongSlash = SINGLE_BAK_SLASH;
		}
		
		if (fileSystemPath.indexOf(wrongSlash) > -1) {
			// replace single wrong slash
			fileSystemPath = StringUtils.replaceAll(fileSystemPath, wrongSlash, File.separator);
		}

		return fileSystemPath;
	}

	public static void hideWindowsFile(File src) throws InterruptedException, IOException {
	    // win32 command line variant
	    Process p = Runtime.getRuntime().exec("attrib +h " + src.getPath());
	    p.waitFor();
	}

	public static boolean isWindows() {
		boolean isWindows = false;
		String osName = System.getProperty("os.name");
		//System.out.println("os.name = " + osName);
		if (osName.startsWith("Windows")) {
			isWindows = true;
		}
		return isWindows;
	}
	
	/**
	 * Returns a list file paths plus names that match the
	 * pattern and attributes passed in.
	 *
	 * @param filePathWithNamePattern
	 * @param checkCanRead
	 * @param checkCanWrite
	 * @return
	 */
//	public static List<String> findMatchingFileNamesInDirectory(String filePathWithNamePattern, boolean checkCanRead, boolean checkCanWrite) {
//		if (filePathWithNamePattern != null) {
//			File[] files = findMatchingFilesInDirectory(filePathWithNamePattern);
//			if (files != null && files.length > 0) {
//				List<String> fileNames = new ArrayList<String>();
//				boolean validFile = true;
//				for (int i = 0; i < files.length; i++) {
//					validFile = true;
//					if (checkCanRead) {
//						if (!files[i].canRead()) {
//							validFile = false;
//						}
//					}
//					if (checkCanWrite) {
//						if (!files[i].canWrite()) {
//							validFile = false;
//						}
//					}
//					if (checkCanExecute) {
//						if (!files[i].canExecute()) {
//							validFile = false;
//						}
//					}
//					if (validFile) {
//						try {
//							fileNames.add(files[i].getCanonicalPath());
//							//fileNames.add(StringUtils.replaceAll(files[i].getCanonicalPath(), "\\", "/"));
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						}
//					}
//				}
//				if (fileNames.size() > 0) {
//					return fileNames;
//				} else {
//					return null;
//				}
//			} else {
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}

	/**
	 * Returns a list files that match the pattern passed in.
	 *
	 * Wrapper function
	 *
	 * @param filePathWithNamePattern
	 * @return
	 */
//	public static File[] findMatchingFilesInDirectory(String filePathWithNamePattern) {
//		if (filePathWithNamePattern != null) {
//			String directoryPath = FileUtils.parseDirectoryFromPath(filePathWithNamePattern);
//			String fileNamePattern = FileUtils.parseFileNameFromPath(filePathWithNamePattern);
//			//System.out.println("FileUtils.findMatchingFilesInDirectory(1 param) directoryPath = " + directoryPath);
//			//System.out.println("FileUtils.findMatchingFilesInDirectory(1 param) fileNamePattern = " + fileNamePattern);
//			return findMatchingFilesInDirectory(directoryPath, fileNamePattern);
//		} else {
//			return null;
//		}
//	}

	/**
	 * Returns a list files that match the pattern passed in.
	 *
	 * @param directoryPath
	 * @param fileNamePattern
	 * @return
	 */
	public static File[] findSubdirectoriesInDirectory(String directoryPath) {
		if (directoryPath != null) {
			File dir = new File(directoryPath);
			//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath);
			//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) fileNamePattern = " + fileNamePattern);

			if (!dir.exists()) {
				//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath + " does not exist");
				return null;
			}
			if (!dir.isDirectory()) {
				//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath + " was not a directory");
				return null;
			}
			if (!dir.canRead()) {
				//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath + " cannot read directory");
				return null;
			}
	
			
			if (dir.exists() && dir.isDirectory() && dir.canRead()) {
				
				File[] files = dir.listFiles();
				
				if (files != null && files.length > 0) {
					List<File> directories = new ArrayList<File>();
					
					for (int i = 0; i < files.length; i++) {
						if (files[i].isDirectory()) {
							directories.add(files[i]);
						} 
					}
					
					files = new File[directories.size()];
					int cntr = 0;
					for (File file : directories) {
						
						files[cntr] = file;
						cntr++;
					}
					
					return files;
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a list files that match the pattern passed in.
	 *
	 * @param directoryPath
	 * @param fileNamePattern
	 * @return
	 */
//	public static File[] findMatchingFilesInDirectory(String directoryPath, String fileNamePattern) {
//		if (directoryPath != null && fileNamePattern != null) {
//			File dir = new File(directoryPath);
//			//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath);
//			//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) fileNamePattern = " + fileNamePattern);
//
//			if (!dir.exists()) {
//				//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath + " does not exist");
//				return null;
//			}
//			if (!dir.isDirectory()) {
//				//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath + " was not a directory");
//				return null;
//			}
//			if (!dir.canRead()) {
//				//System.out.println("FileUtils.findMatchingFilesInDirectory(2 params) directoryPath = " + directoryPath + " cannot read directory");
//				return null;
//			}
//	
//			
//			if (dir.exists() && dir.isDirectory() && dir.canRead()) {
//				WildcardFileNameFilter filter = new WildcardFileNameFilter(fileNamePattern);
//				File[] files = dir.listFiles(filter);
//				if (files != null && files.length > 0) {
//					return files;
//				} else {
//					return null;
//				}
//			} else {
//				return null;
//			}
//		} else {
//			return null;
//		}
//	}

	/**
	 * Returns the first file matching the pattern passed in.
	 *
	 * Wrapper function
	 *
	 * @param filePathWithNamePattern
	 * @return
	 */
//	public static File findFirstMatchingFileInDirectory(String filePathWithNamePattern) {
//		if (filePathWithNamePattern != null) {
//			String directoryPath = FileUtils.parseDirectoryFromPath(filePathWithNamePattern);
//			String fileNamePattern = FileUtils.parseFileNameFromPath(filePathWithNamePattern);
//			return findFirstMatchingFileInDirectory(directoryPath, fileNamePattern);
//		} else {
//			return null;
//		}
//	}

	/**
	 * Returns the first file matching the pattern passed in.
	 *
	 * @param directoryPath
	 * @param fileNamePattern
	 * @return
	 */
//	public static File findFirstMatchingFileInDirectory(String directoryPath, String fileNamePattern) {
//		File[] files = findMatchingFilesInDirectory(directoryPath, fileNamePattern);
//		if (files != null && files.length > 0) {
//			return files[0];
//		} else {
//			return null;
//		}
//	}

	/**
	 * Returns the directory from the full path passed in.
	 *
	 * @param fullPathToFile
	 * @return
	 */
	public static String parseDirectoryFromPath(String fullPathToFile) {
		if (fullPathToFile != null) {
			String dirPath;
			int lastSlash = -1;

			fullPathToFile = convertFileSystemPath(fullPathToFile);
			
			//System.out.println("FileUtils.parseDirectoryFromPath(...) fullPathToFile = " + fullPathToFile);
			
			lastSlash = fullPathToFile.lastIndexOf(SINGLE_FWD_SLASH);

			if (lastSlash > -1) {
				dirPath = fullPathToFile.substring(0, lastSlash + 1);
				//System.out.println("FileUtils.parseDirectoryFromPath(...) dirPath = " + dirPath);
				return dirPath;
			} else {
				lastSlash = fullPathToFile.lastIndexOf(SINGLE_BAK_SLASH);
				if (lastSlash > -1) {
					dirPath = fullPathToFile.substring(0, lastSlash + 1);
					//System.out.println("FileUtils.parseDirectoryFromPath(...) dirPath = " + dirPath);
					return dirPath;
				} else {
					return null;
				}				
			}
		} else {
			return null;
		}
	}

	/**
	 * Returns the file name from the full path passed in.
	 *
	 * @param filePathWithNamePattern
	 * @return
	 */
	public static String parseFileNameFromPath(String filePathWithNamePattern) {
		if (filePathWithNamePattern != null) {
			String fileName;
			int lastSlash = -1;

			filePathWithNamePattern = convertFileSystemPath(filePathWithNamePattern);

			lastSlash = filePathWithNamePattern.lastIndexOf(SINGLE_FWD_SLASH);

			if (lastSlash > -1) {
				fileName = filePathWithNamePattern.substring((lastSlash + 1));
				System.out.println("FileUtils.parseFileNameFromPath(...) fileName = " + fileName);
				return fileName;
			} else {
				lastSlash = filePathWithNamePattern.lastIndexOf(SINGLE_BAK_SLASH);
				if (lastSlash > -1) {
					fileName = filePathWithNamePattern.substring(lastSlash + 1);
					System.out.println("FileUtils.parseFileNameFromPath(...) fileName = " + fileName);
					return fileName;
				} else {
					return null;
				}				
			}
		} else {
			return null;
		}
	}



}
