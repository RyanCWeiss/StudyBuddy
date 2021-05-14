package sqliteUtil;


public class ColumnInfo {

	String term;
	String content;
	String image;
	
	
	public ColumnInfo(String term, String content, String image) {
		if (!image.isBlank()) {
			this.image = image;
		}
		this.term = term;
		this.content = content;
	}
	
	


	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
	
}
