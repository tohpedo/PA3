package ws;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import rest.Review;

import java.io.*;
import java.nio.*;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebService(endpointInterface = "ws.BroadwayReview")
public class BroadwayReviewImpl implements BroadwayReview{
	//Does the brunt of the work parsing the text files into strings.
	//parseInfo() is different for each source, but this can be changed.
	public Review[] parseInfo() throws FileNotFoundException, ParseException{
		File text = new File("Broadway.com.txt");
		Scanner s = new Scanner(text);
		double numReviews = 0;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date dateTemp;
		while (s.hasNextLine())
		{
			numReviews += 0.5;
		}
		if (numReviews % 2 == 1)
		{
			numReviews = numReviews + 0.5;
		}
		s.reset();
		Review[] allReviews = new Review[(int) numReviews];
		String delims = "[|]";
		String[] temp;
		int reviewCounter = 0;
		while (s.hasNextLine())
		{
			temp = s.nextLine().split(delims);
			if (temp.length > 2)
			{
				allReviews[reviewCounter].setReviewSource("Broadway.com");
				allReviews[reviewCounter].setPlayTitle(temp[0]);
				allReviews[reviewCounter].setReviewTitle(temp[1]);
				dateTemp = formatter.parse(temp[2]);
				allReviews[reviewCounter].setReviewDate(dateTemp);
				allReviews[reviewCounter].setPlayGenre(temp[3]);
				allReviews[reviewCounter].setFullReview(temp[4]);
			}
			else
			{
				continue;
			}
		}
		s.close();
		return allReviews;
	}
	public Review[] populate() throws ParseException{
		Review[] output = new Review[2];
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		Date dateTemp = formatter.parse("11/19/1998");
		Review r = new Review("Broadway.com", "The Titanic", "Awesome Movie!", "Drama", dateTemp, "I really loved this movie" );
		Review x = new Review("Broadway.com", "The Departed", "Leo Does Great", "Drama", dateTemp, "Leonardo DiCaprio does a fabulous job in this movie" );
		output[0] = r;
		output[1] = x;
		return output;
		
	}
	
	//Simply prints all the elements of the review listed in the
	//review class. Primarily for testing.
	@Override
	public void printReview(Review r)
	{
		System.out.println(r.getReviewSource());
		System.out.println(r.getPlayTitle());
		System.out.println(r.getReviewTitle());
		System.out.println(r.getReviewDate());
		System.out.println(r.getPlayGenre());
		System.out.println(r.getFullReview());
	}
	

	//Takes a review array and returns the same array but
	//listed in order of date. This is done in order from
	//oldest to most recent.
	//This was done using Bubble Sort which, while not the
	//fastest, gets the reviews sorted without the need for a pivot.
	public Review[] reviewsByDate(Review[] rlist)
	{
		int n = rlist.length;
		int k;
		for (int m = n; m >= 0; m--)
		{
			for (int i = 0; i < n - 1; i++)
			{
				k = i + 1;
				if (rlist[i].getReviewDate().after(rlist[k].getReviewDate()))
				{
					Review holder;
					holder = rlist[i];
					rlist[i] = rlist[k];
					rlist[k] = holder;
				}
			}
		}
		return rlist;
	}
	
	//Reads through an array of Reviews and returns an array of ONLY
	//plays in the given genre
	public Review[] reviewsByGenre(Review[] rlist, String genre)
	{
		Review[] results = new Review[rlist.length];
		int tracker = 0;
		for (int i = 0; i < rlist.length; i++)
		{
			if(rlist[i].getPlayGenre().equalsIgnoreCase(genre))
			{
				results[tracker] = rlist[i];
				tracker++;
			}
		}
		return results;
	}
	
	//Returns an array of all reviews about a play by its name.
	public Review[] reviewsByName(Review[] rlist, String name)
	{
		Review[] results = new Review[rlist.length];
		int tracker = 0;
		for (int i = 0; i < rlist.length; i++)
		{
			if(rlist[i].getPlayTitle().equalsIgnoreCase(name))
			{
				
				results[tracker] = rlist[i];
				tracker++;
			}
		}
		return results;
	}
	
	//Main method for testing above methods.
	public void main() throws IOException, ParseException
	{
		Review[] myReviews = parseInfo();
		for (int i = 0; i < myReviews.length; i++)
			printReview(myReviews[i]);
	}

}