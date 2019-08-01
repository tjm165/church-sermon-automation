import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class SermonCreator {
	
	public static File create(SermonInfo info) throws IOException {
		String templatePath = info.getTemplatePath();
		String vegasProFilePath = info.getVegasProPath();
		
		File mediaFolder = new File(info.getMediaFolderPath());

		copyWithStreams(templatePath, vegasProFilePath, true);
		mediaFolder.mkdir();

		return new File(info.getFolderPath());
	}
	
	
	//http://www.javapractices.com/topic/TopicAction.do?Id=246
	private static void copyWithStreams(String sourcePath, String targetPath, boolean append) {
		File source = new File(sourcePath);
		File target = new File(targetPath);
		
	    log("Copying files with streams.");
	    ensureTargetDirectoryExists(target.getParentFile());
	    InputStream inStream = null;
	    OutputStream outStream = null;
	    try{
	      try {
	        byte[] bucket = new byte[32*1024];
	        inStream = new BufferedInputStream(new FileInputStream(source));
	        outStream = new BufferedOutputStream(new FileOutputStream(target, append));
	        int bytesRead = 0;
	        while(bytesRead != -1){
	          bytesRead = inStream.read(bucket); //-1, 0, or more
	          if(bytesRead > 0){
	            outStream.write(bucket, 0, bytesRead);
	          }
	        }
	      }
	      finally {
	        if (inStream != null) inStream.close();
	        if (outStream != null) outStream.close();
	      }
	    }
	    catch (FileNotFoundException ex){
	      log("File not found: " + ex);
	    }
	    catch (IOException ex){
	      log(ex);
	    }
	  }
	  
	  private static void ensureTargetDirectoryExists(File targetDir){
	    if(!targetDir.exists()){
	      targetDir.mkdirs();
	    }
	  }
	  
	  private static void log(Object thing){
	    System.out.println(String.valueOf(thing));
	  }
	}


