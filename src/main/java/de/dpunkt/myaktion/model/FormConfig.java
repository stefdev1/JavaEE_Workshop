package de.dpunkt.myaktion.model;

public class FormConfig {
	
	private String bgColor;
	private String textColor;
	private String title;
	
	public FormConfig(String bgColor,String textColor, String title) {
		this.bgColor = bgColor;
		this.textColor = textColor;
		this.title =title;
	}
	
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
	
	public String getTextColor() {
		return textColor;
	}
	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
