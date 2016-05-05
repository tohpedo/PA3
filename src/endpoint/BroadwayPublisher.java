package endpoint;

import javax.xml.ws.Endpoint;

import ws.BroadwayReviewImpl;


public class BroadwayPublisher {
	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:8888/ws/broadway", new BroadwayReviewImpl());
	}
}