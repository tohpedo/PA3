package rest;
import java.util.Date;
import javax.xml.bind.annotation.*;
@XmlRootElement(name = "review")
@XmlType(propOrder ={"reviewSource", "playTitle","reviewTitle", "playGenre", "reviewDate", "fullReview"})
public class Review {
	
	private String reviewSource;
	private String playTitle;
	private String reviewTitle;
	private String playGenre;
	private Date reviewDate;
	private String fullReview;
	
	public Review(){}
	
	public Review(String rSource, String pTitle, String rTitle, String pGenre, Date rDate, String fReview)
	{
		setReviewSource(rSource);
		setPlayTitle(pTitle);
		setReviewTitle(rTitle);
		setPlayGenre(pGenre);
		setReviewDate(rDate);
		setFullReview(fReview);
	}

	public String getReviewSource() {
		return this.reviewSource;
	}

	public void setReviewSource(String reviewSource) {
		this.reviewSource = reviewSource;
	}

	public String getPlayTitle() {
		return this.playTitle;
	}

	public void setPlayTitle(String playTitle) {
		this.playTitle = playTitle;
	}

	public String getReviewTitle() {
		return this.reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getPlayGenre() {
		return this.playGenre;
	}

	public void setPlayGenre(String playGenre) {
		this.playGenre = playGenre;
	}

	public Date getReviewDate() {
		return this.reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getFullReview() {
		return this.fullReview;
	}

	public void setFullReview(String fullReview) {
		this.fullReview = fullReview;
	}
	
	public String printReview()
	{
		return "<h3>" + this.getReviewSource() + "|" +  this.getPlayTitle() + "|" + this.getReviewTitle() + "|" + this.getReviewDate() + "|" + this.getPlayGenre() + "|" + this.getFullReview() + "</h3><br>";
		
	}
	
	
	
	
	
}