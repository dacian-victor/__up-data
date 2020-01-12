package com.rss.feeder.feeder.service;


import com.rometools.rome.feed.synd.SyndContent;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import com.rss.feeder.feeder.domain.FeedItem;
import com.rss.feeder.feeder.domain.FeedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FeederService {


    @Autowired
    FeedItemRepository feedItemRepository;

    public List<FeedItem> getFeed() {
        return StreamSupport.stream(
                feedItemRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
    }


    public void makeTheRequest() {
        SyndFeedInput input = new SyndFeedInput();
        int pageNumber = 1;

        try{
                URL feedSource = new URL("http://feeds.feedburner.com/descopera");//"https://altaonline.com/feed?paged=" + pageNumber);
                SyndFeed feed = input.build(new XmlReader(feedSource));
                System.out.println(">>>> \n"  +
                        feed.getEntries().stream().map(f -> f.getTitle() + "\n").collect(Collectors.joining()));
                feed.getEntries().stream().forEach(item -> {
                    FeedItem feedItem = new FeedItem(item.getLink(), item.getTitle(), item.getDescription().getValue(), item.getAuthor());
                    //feedItem.setArticlePicture(generateImage(item.getContents().get(0).getValue()));
                    feedItemRepository.save(feedItem);
                });
                pageNumber++;

        } catch (IOException ex){
            System.out.println("IO exception occurred due to: "+ ex);
            //Handle this exception accordingly
        } catch (FeedException ex) {
            System.out.println("Feed exception occurred due to: "+ ex);
            //Handle this exception accordingly
        }
    }

    public byte[] generateImage(String content) {
        try {
            // Regex need to extract image url from content
            // <img class="alignnone size-medium wp-image-6625" src="https://www.descopera.org/wp-content/uploads/2019/08/detector-de-fum-400x267.jpg" alt="" width="400" height="267"
            URL url = new URL("https://www.descopera.org/wp-content/uploads/2019/08/detector-de-fum-400x267.jpg");
            BufferedImage image = ImageIO.read(url);
            ByteArrayOutputStream pngContent = new ByteArrayOutputStream();
            ImageIO.write(image, "png", pngContent);
            return pngContent.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
