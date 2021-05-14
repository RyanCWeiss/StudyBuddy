package model;

public class QuestionSelection {

	private static QuestionSelection questionSelection = null;
	
	private String term;
	private String textContent;
	private String pdfPath;
	
	private QuestionSelection(String term, String textContent, String pdfPath) {
		this.term = term;
		this.textContent = textContent;
		this.pdfPath = pdfPath;
	}
	
	public static QuestionSelection getQuestionSelectionInstance(String term, String textContent, String pdfPath) {
		if (QuestionSelection.questionSelection != null) {
			return QuestionSelection.questionSelection;
		} else {
			QuestionSelection.questionSelection = new QuestionSelection(term, textContent, pdfPath);
			return QuestionSelection.questionSelection;
		}
		
	}

	public QuestionSelection updateQuestionSelection(String term, String textContent, String pdfPath) {
		questionSelection.setTerm(term);
		questionSelection.setTextContent(textContent);
		questionSelection.setPdfPath(pdfPath);
		return QuestionSelection.questionSelection;
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
