package model;

public class TermSelection {

	private static TermSelection termSelection = null;
	
	private String term;
	private String textContent;
	private String pdfPath;
	
	private TermSelection(String term, String textContent, String pdfPath) {
		this.term = term;
		this.textContent = textContent;
		this.pdfPath = pdfPath;
	}
	
	public static TermSelection getTermSelectionInstance(String term, String textContent, String pdfPath) {
		if (TermSelection.termSelection != null) {
			return TermSelection.termSelection;
		} else {
			TermSelection.termSelection = new TermSelection(term, textContent, pdfPath);
			return TermSelection.termSelection;
		}
		
	}

	public static TermSelection changeTermSelection(String term, String textContent, String pdfPath) {
		termSelection.setTerm(term);
		termSelection.setTextContent(textContent);
		termSelection.setPdfPath(pdfPath);
		return TermSelection.termSelection;
	}
	public String getTerm() {
		return term;
	}

	private void setTerm(String term) {
		this.term = term;
	}

	public String getTextContent() {
		return textContent;
	}

	private void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	private void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	
}
