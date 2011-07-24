package uk.co.brotherlogic.twitter;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Hello world!
 * 
 */
public class TwitterLists extends JPanel
{
   public TwitterLists() throws IOException
   {
      // Set up the grid display
      int numberOfLists = TwitterCore.getTwitterLists().size();
      this.setLayout(new GridLayout(2, 4));
      for (String list : TwitterCore.getTwitterLists())
         this.add(new JLabel(list));
   }

   public static void main(String[] args) throws IOException
   {
      TwitterLists lists = new TwitterLists();
      JFrame framer = new JFrame();
      framer.add(lists);
      framer.setSize(500, 500);
      framer.setLocationRelativeTo(null);
      framer.setVisible(true);
      framer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }
}
