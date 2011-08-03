package uk.co.brotherlogic.twitter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.UserList;
import twitter4j.conf.ConfigurationBuilder;
import uk.co.brotherlogic.twitter.core.TwitterList;
import uk.co.brotherlogic.twitter.core.TwitterUser;

public class TwitterCore
{
   static long userID = 6029552L;
   static String username = "BrotherLogic";
   static Twitter twitter = null;

   private static void loadTwitter() throws IOException
   {
      Properties props = new Properties();
      props.load(new FileInputStream(new File("twitterint.conf")));

      // Authenticate
      ConfigurationBuilder cb = new ConfigurationBuilder();
      cb.setDebugEnabled(true).setOAuthConsumerKey(props.getProperty("cons_key"))
            .setOAuthConsumerSecret(props.getProperty("cons_secret"))
            .setOAuthAccessToken(props.getProperty("access_token"))
            .setOAuthAccessTokenSecret(props.getProperty("access_secret"));
      TwitterFactory tf = new TwitterFactory(cb.build());
      Twitter twitter = tf.getInstance();
   }

   public static Twitter getTwitter() throws IOException
   {
      if (twitter == null)
      {
         Properties props = new Properties();
         props.load(new FileInputStream(new File("twitterint.conf")));

         // Authenticate
         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true).setOAuthConsumerKey(props.getProperty("cons_key"))
               .setOAuthConsumerSecret(props.getProperty("cons_secret"))
               .setOAuthAccessToken(props.getProperty("access_token"))
               .setOAuthAccessTokenSecret(props.getProperty("access_secret"));
         TwitterFactory tf = new TwitterFactory(cb.build());
         twitter = tf.getInstance();
      }

      return twitter;
   }

   public static boolean subscribes(TwitterUser user, TwitterList list)
   {
      try
      {
         Twitter twit = getTwitter();
         return twit.showUserListMembership(list.getID(), user.getID()).getId() == user.getID();
      }
      catch (TwitterException e)
      {
         // e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      return false;
   }

   public static List<TwitterList> getTwitterLists() throws IOException
   {
      List<TwitterList> lists = new LinkedList<TwitterList>();

      try
      {
         PagableResponseList<UserList> resp = getTwitter().getUserLists(userID, -1);
         for (UserList list : resp)
            lists.add(new TwitterList(list));
      }
      catch (TwitterException e)
      {
         throw new IOException(e);
      }

      return lists;
   }

}
