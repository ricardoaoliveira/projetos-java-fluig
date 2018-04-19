package rss.tests;

import rss.model.Feed;
import rss.model.FeedMessage;
import rss.read.RSSFeedParser;

public class ReadTest {
	
    public static void main(String[] args) {
        
    	RSSFeedParser parser = new RSSFeedParser(
                "http://conteudo.bcb.gov.br/api/feed/pt-br/PAINEL_INDICADORES/cambio");
        
        Feed feed = parser.readFeed();
        
        System.out.println(feed);
        
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);
        }
    }    
}