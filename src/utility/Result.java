package utility;

public class Result {
	private String title;
	private String imgUrl; 
	private String linkRef; 
	private String plattformTitle;
	private double price;
	private String availability;
	



	/**
	 * Costruttore per i risltati di amazon;
	 * @param title
	 * @param imgUrl
	 * @param linkRef
	 * @param plattformTitle
	 * @param price
	 */
	public Result(String title, String imgUrl, String linkRef, String plattformTitle, double price) {
		this.title = title;
		this.imgUrl = imgUrl;
		this.linkRef = linkRef;
		this.plattformTitle = plattformTitle;
		this.price = price;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}


	public String getLinkRef() {
		return linkRef;
	}


	public void setLinkRef(String linkRef) {
		this.linkRef = linkRef;
	}


	public String getPlattformTitle() {
		return plattformTitle;
	}


	public void setPlattformTitle(String plattformTitle) {
		this.plattformTitle = plattformTitle;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
	
	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}
	
	
	
	
	
	
}
