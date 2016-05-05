package endpoint;

import javax.xml.ws.Endpoint;

import ws.NYTimesReviewImpl;

public class NYTimesPublisher {
	public static void main(String[] args)
	{
		Endpoint.publish("http://localhost:8889/ws/nytimes", new NYTimesReviewImpl());
	}
}