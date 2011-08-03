package uk.co.brotherlogic.twitter.core;

import twitter4j.UserList;

public class TwitterList
{
   UserList tul;

   public TwitterList(UserList in)
   {
      tul = in;
   }

   public String getName()
   {
      return tul.getName();
   }

   public int getID()
   {
      return tul.getId();
   }
}
