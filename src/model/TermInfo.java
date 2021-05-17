package model;

public class TermInfo {

	private String term;
	private String content;
	private String pdfPath;
	
	public TermInfo(String term, String content, String pdfPath) {
		this.term = term;
		this.content = content;
		this.pdfPath = pdfPath;
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

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
	public String toString() {
		return term + " " + content.substring(0,content.length()/2+1) + "... " + pdfPath;
	}

	
}
