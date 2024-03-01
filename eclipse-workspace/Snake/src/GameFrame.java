import java.awt.Component;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

GameFrame(){
	   GamePanel gamepanel = new GamePanel();
	   add(gamepanel);
	   setTitle("Snake");
	   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   setResizable(false);
	   pack();
	   setVisible(true);
	   setLocationRelativeTo(null);
	   
   }
}
