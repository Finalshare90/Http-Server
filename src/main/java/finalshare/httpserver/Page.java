package finalshare.httpserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Page {
	
	HeaderType status;
	private ArrayList<String> content = new ArrayList<>();
	private File file;
	
	public Page(String fileName, HeaderType status){
		file = new File(fileName);
		this.status = status;
	}
	
	public List<String> getContent() {
		createContent(content);
		return content;
	}
	
	public void setHeader(HeaderType header) {
		status = header;
	}
	
	private void createContent(List<String> content) {
		try {
		System.out.println(file.getAbsolutePath());
		Scanner in = new Scanner(file);
		content.add(RequestHandler.HEADERS.get(status));
		content.add("Content-Type: text/html \n");
		content.add("\n");
		while(in.hasNext()){
			content.add(in.nextLine());
		}
		in.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
