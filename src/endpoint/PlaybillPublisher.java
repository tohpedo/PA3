package endpoint;

import javax.xml.ws.Endpoint;

import ws.PlaybillReviewImpl;


public class PlaybillPublisher {
	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:8887/ws/playbill", new PlaybillReviewImpl());
	}
}