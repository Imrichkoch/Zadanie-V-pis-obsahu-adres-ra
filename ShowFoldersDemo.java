package showfolders;

import java.io.File;

public class ShowFoldersDemo {
	
	public static  void showMeDirectories(File [] files){
		
		for(File file:files){
		    
			if(file.isDirectory()){ // file vo file poli je directory vtedy metoda zavola samu seba
				System.out.println("DIR:"+file.getAbsolutePath());	
				showMeDirectories(file.listFiles());
			}else{
				System.out.println("       -------"+file.getAbsolutePath());	
			}
				
		}
		
		
		}
		
	

	public static void main(String[] args) {
		File files []= new File("C:\\1").listFiles();
		showMeDirectories(files);
		
		
		
		}
	}


