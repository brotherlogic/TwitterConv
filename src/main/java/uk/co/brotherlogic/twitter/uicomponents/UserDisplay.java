package uk.co.brotherlogic.twitter.uicomponents;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import uk.co.brotherlogic.twitter.TwitterCore;
import uk.co.brotherlogic.twitter.core.TwitterList;
import uk.co.brotherlogic.twitter.core.TwitterUser;

public class UserDisplay extends JPanel
{
   TwitterUser user;

   public UserDisplay(TwitterUser user)
   {
      this.user = user;
      initDisplay();
   }

   private void initDisplay()
   {
      GridBagLayout gbl = new GridBagLayout();
      this.setLayout(gbl);

      // 50 x 50 pixel panel of the user image
      JPanel imagePanel = new JPanel()
      {
         @Override
         public void paint(Graphics g)
         {
            Image img = user.getImage(50, 50);
            g.drawImage(img, 0, 0, null);
         }

         @Override
         public Dimension getMaximumSize()
         {
            // TODO Auto-generated method stub
            return getPreferredSize();
         }

         @Override
         public Dimension getMinimumSize()
         {
            // TODO Auto-generated method stub
            return getPreferredSize();
         }

         @Override
         public Dimension getPreferredSize()
         {
            // TODO Auto-generated method stub
            return new Dimension(50, 50);
         }

      };
      gbl.setConstraints(imagePanel, new GridBagConstraints(0, 0, 1, 1, 0, 0,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
      this.add(imagePanel);

      JLabel usernameLabel = new JLabel(user.getName());
      gbl.setConstraints(usernameLabel, new GridBagConstraints(1, 0, 1, 1, 1, 0,
            GridBagConstraints.EAST, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0));
      this.add(usernameLabel);

      JPanel listsPanel = new JPanel();
      try
      {
         for (TwitterList list : TwitterCore.getTwitterLists())
         {
            JToggleButton toggler = new JToggleButton(list.getName());
            if (TwitterCore.subscribes(user, list))
               toggler.setSelected(true);
            listsPanel.add(toggler);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      gbl.setConstraints(listsPanel, new GridBagConstraints(0, 1, 2, 1, 1, 1,
            GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(2, 2, 2, 2), 0, 0));
      this.add(listsPanel);
   }

   public static void main(String[] args)
   {
      JFrame framer = new JFrame();
      framer.setSize(500, 500);
      framer.setLocationRelativeTo(null);

      UserDisplay disp = new UserDisplay(new TwitterUser("JeanetteLeech"));
      framer.add(disp);
      framer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      framer.pack();
      framer.setVisible(true);
   }
}
