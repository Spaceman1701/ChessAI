package chess.ai;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AITurnWindow {
	private JFrame frame;
	private JPanel p;
	
	public AITurnWindow(final AIPlayer ai) {
		frame = new JFrame();
		p = new JPanel();
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setLayout(new BorderLayout());
		JButton nextTurn = new JButton();
		p.add(nextTurn);
		nextTurn.setText("Make Turn");
		nextTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ai.makeTurn();
			}
		});
		frame.setVisible(true);
	}
}
