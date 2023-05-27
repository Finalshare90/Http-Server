package finalshare.httpserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.kogaplanet.lunarlatteMarkupLanguage.api.ParserProtocol;

public class RequestHandler {
	
	ArrayList<String> processedData = new ArrayList<>();
	static HashMap<HeaderType, String> HEADERS = new HashMap<>();
	HashMap<String, Page> filesTable = new HashMap<>();
	ParserProtocol tagProtocol;
	
	
	public RequestHandler(char[] data, ParserProtocol config){
		
		tagProtocol = config;
		createHeaders();
		initFiles();
		
		String tmpLine = "";
		
		for(int count2 = 0, count = 0; count < data.length; count++){
			if(data[count] != ' '){
				tmpLine = tmpLine + Character.toString(data[count]);
			}else {
				
				processedData.add(tmpLine);
				System.out.println(count2+": "+processedData.get(count2));
				count2++;
				tmpLine = "";
			}
			
		}
		
	}
	
	private void createHeaders() {
		HEADERS.put(HeaderType.OK, "HTTP/1.1 200 OK ");
		HEADERS.put(HeaderType.CONTINUE, "HTTP/1.1 100 Continue ");
		HEADERS.put(HeaderType.FORBIDDEN, "HTTP/1.1 403 Forbidden ");
		HEADERS.put(HeaderType.NOTFOUND, "HTTP/1.1 404 Not Found ");
	}
	
	private void initFiles() {
		
		List<String>notFoundtag, indexTag, filesTag, pageTag = tagProtocol.call("pages").data;
		indexTag = tagProtocol.call("index").data;
		notFoundtag = tagProtocol.call("notfound").data;
		filesTag = tagProtocol.call("files").data;
		
		filesTable.put("/404", new Page(notFoundtag.get(0), HeaderType.NOTFOUND));
		filesTable.put("/", new Page(indexTag.get(0), HeaderType.OK));
		
		
		insertFiles(pageTag, HeaderType.OK);
		insertFiles(filesTag, HeaderType.OK);
		
	}
	
	private void insertFiles(List<String> tag, HeaderType header) {

		for(int count = 0; count < tag.size(); count++) {
			
			String filePath, prefix;
			
			prefix = (String)tag.get(count);
		
			if(count != tag.size())
				count++;
			
			filePath = tag.get(count);
			
			filesTable.put("/" + prefix, new Page(filePath, header));
		}
		
	}
	
	public List<String> parseRequest() {
		List<String> content;
		
		try {
			content = filesTable.get(processedData.get(1)).getContent();
		}catch (Exception e) {		
			return filesTable.get("/404").getContent();
		}
		
		return content;
	}
	
	
}
