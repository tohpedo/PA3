package rest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import ws.*;


@Path("/rest")
public class Reviews{
	
	ArrayList<Review> reviews;
	PlaybillReview playbill;
	NYTimesReview nytimes;
	BroadwayReview broadway;
	boolean initialized = false;
	
	@GET
	@Produces({ MediaType.TEXT_PLAIN })
	@Path("/plays/all")
	public Response getAllPlays() throws ParseException, IOException {
		init();
		String output = "";
		ArrayList<String> play_names = new ArrayList<String>();
		for (Review i : reviews){
			String title = i.getPlayTitle();
			if (!play_names.contains(title)){
				play_names.add(title);
			}
		}
			output = String.join("|", play_names);
		
		return Response.ok(output, "text/plain").build();
	}
	
	@GET
	@Produces({ MediaType.TEXT_HTML })
	@Path("/plays/{name}")
	public Response getReviewsByName(@PathParam("name") String playName) throws ParseException, IOException {
		System.out.println("In GET REVIEWS BY NAME");
		init();
		String output = "";
		ArrayList<String> play_reviews = new ArrayList<String>();
		for (Review i : reviews){
			String title = i.getPlayTitle();
			if (title.equals(playName)){
				output += i.printReview();
			}
		}
		return Response.ok(output,"text/html").build();
	}
	
	
	

	
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
	
	public void init() throws ParseException, IOException{
		if (initialized){
			System.out.println("initialized");
			return;
		}
		
		reviews = new ArrayList<Review>();
		
		System.out.println("initializing");
		
		URL url = new URL("http://localhost:8888/ws/broadway?wsdl");
        QName qname = new QName("http://ws/", "BroadwayReviewImplService");
        Service service = Service.create(url, qname);
        broadway = service.getPort(BroadwayReview.class);
        
        URL url_1 = new URL("http://localhost:8889/ws/nytimes?wsdl");
        QName qname_1 = new QName("http://ws/", "NYTimesReviewImplService");
        Service service_1 = Service.create(url_1, qname_1);
        nytimes = service_1.getPort(NYTimesReview.class);
        
        URL url_2 = new URL("http://localhost:8887/ws/playbill?wsdl");
        QName qname_2 = new QName("http://ws/", "PlaybillReviewImplService");
        Service service_2 = Service.create(url_2, qname_2);
        playbill = service_2.getPort(PlaybillReview.class);
     
        Review[] tmp_1 = broadway.populate();
        Review[] tmp_2 = nytimes.populate();
        Review[] tmp_3 = playbill.populate();
        for ( Review r : tmp_1){
        	reviews.add(r);
        }
        for ( Review r : tmp_2){
        	reviews.add(r);
        }
        for ( Review r : tmp_3){
        	reviews.add(r);
        }
        initialized = true;
        
	}
	

}
