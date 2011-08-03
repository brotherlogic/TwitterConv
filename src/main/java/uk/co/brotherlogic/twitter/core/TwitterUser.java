package uk.co.brotherlogic.twitter.core;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import twitter4j.ProfileImage;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import uk.co.brotherlogic.twitter.TwitterCore;

public class TwitterUser
{
   String username;
   Image cachedImage;

   public TwitterUser(String name)
   {
      username = name;
   }

   public long getID()
   {
      try
      {
         return TwitterCore.getTwitter().showUser(username).getId();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (TwitterException e)
      {
         e.printStackTrace();
      }

      return -1;
   }

   public String getName()
   {
      return username;
   }

   public Image getImage(int width, int height)
   {
      try
      {
         if (cachedImage == null)
         {
            Twitter twitter = TwitterCore.getTwitter();
            ProfileImage image = twitter.getProfileImage(username, ProfileImage.BIGGER);
            cachedImage = ImageIO.read(new URL(image.getURL()));
         }
         return cachedImage.getScaledInstance(width, height, Image.SCALE_FAST);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      catch (TwitterException e)
      {
         e.printStackTrace();
      }

      // Default to empty
      return null;
   }
}
