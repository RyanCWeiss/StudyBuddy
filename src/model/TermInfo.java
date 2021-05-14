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

	public String getContent() {
		return content;
	}

	public String getPdfPath() {
		return pdfPath;
	}
}
