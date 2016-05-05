package ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import rest.Review;

import java.io.*;
import java.text.ParseException;

@WebService
@SOAPBinding(style = Style.RPC)
public interface PlaybillReview {
	@WebMethod
	public Review[] parseInfo() throws IOException, ParseException, FileNotFoundException;
	@WebMethod
	public void printReview(Review r);
	@WebMethod
	public Review[] reviewsByDate(Review[] rlist);
	@WebMethod
	public Review[] reviewsByGenre(Review[] rlist, String genre);
	@WebMethod
	public Review[] reviewsByName(Review[] rlist, String name);
	@WebMethod
	public Review[] populate() throws ParseException;
}