package utility;

public class Result {
	
	private String title;
	private String imgUrl; 
	private String linkRef; 
	private String plattformTitle;
	private double price;
	private String availability;
	private String vendor;
	private String description;
	private String category;
	private int feedback;
	private String releaseDate;
	private String developer;
	private String requisitiMinimi;
	private String requisitiConsigliati;
	
	



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
	
	/**
	 * Costruttore risultati Steam
	 * @param title
	 * @param imgUrl
	 * @param linkRef
	 * @param price
	 * @param description
	 * @param category
	 * @param feedback
	 * @param releaseDate
	 */


	public Result(String title, String imgUrl, String linkRef, double price, String description, String category,
			int feedback, String releaseDate, String developer) {
		super();
		this.title = title;
		this.imgUrl = imgUrl;
		this.linkRef = linkRef;
		this.price = price;
		this.description = description;
		this.category = category;
		this.feedback = feedback;
		this.releaseDate = releaseDate;
		this.developer = developer;
	}
	


	/**
	 * Construttore instant gaming
	 * @param title
	 * @param imgUrl
	 * @param linkRef
	 * @param plattformTitle
	 * @param price
	 * @param availability
	 * @param description
	 * @param feedback
	 * @param releaseDate
	 * @param requisitiMinimi
	 * @param requisitiConsigliati
	 */

	public Result(String title, String imgUrl, String linkRef, String plattformTitle, double price, String availability,
			String description, int feedback, String releaseDate, String requisitiMinimi, String requisitiConsigliati) {
		super();
		this.title = title;
		this.imgUrl = imgUrl;
		this.linkRef = linkRef;
		this.plattformTitle = plattformTitle;
		this.price = price;
		this.availability = availability;
		this.description = description;
		this.feedback = feedback;
		this.releaseDate = releaseDate;
		this.requisitiMinimi = requisitiMinimi;
		this.requisitiConsigliati = requisitiConsigliati;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}

	public String getRequisitiMinimi() {
		return requisitiMinimi;
	}

	public void setRequisitiMinimi(String requisitiMinimi) {
		this.requisitiMinimi = requisitiMinimi;
	}

	public String getRequisitiConsigliati() {
		return requisitiConsigliati;
	}

	public void setRequisitiConsigliati(String requisitiConsigliati) {
		this.requisitiConsigliati = requisitiConsigliati;
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


	public String getVendor() {
		return vendor;
	}


	public void setVendor(String vendor) {
		this.vendor = vendor;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public int getFeedback() {
		return feedback;
	}


	public void setFeedback(int feedback) {
		this.feedback = feedback;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	
	public String steamToString() {
		return "Result [title=" + title + "\n imgUrl=" + imgUrl + "\n linkRef=" + linkRef + "\n price=" + price
				+ "\n description=" + description + "\n category=" + category + "\n feedback=" + feedback
				+ "\n releaseDate=" + releaseDate + "\n developer=" + developer + "]";
	}

	
	public String amazonToString() {
		return "Result [title=" + title + "\n imgUrl=" + imgUrl + "\n linkRef=" + linkRef + "\n plattformTitle="
				+ plattformTitle + "\n price=" + price + "\n availability=" + availability + "\n vendor=" + vendor + "]";
	}

	
	public String instantGamingToString() {
		return "Result [title=" + title + "\n imgUrl=" + imgUrl + "\n linkRef=" + linkRef + "\n plattformTitle="
				+ plattformTitle + "\n price=" + price + "\n availability=" + availability + "\n description="
				+ description + "\n feedback=" + feedback + "\n releaseDate=" + releaseDate + "\n requisitiMinimi="
				+ requisitiMinimi + "\n requisitiConsigliati=" + requisitiConsigliati + "\n]"+"\n------------------------\n";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
