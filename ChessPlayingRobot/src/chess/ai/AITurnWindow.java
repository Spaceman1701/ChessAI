package chess.ai;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

public class AITurnWindow {
	private JFrame frame;
	private JPanel p;
	private JProgressBar progressBar;
	private JLabel label;
	private JLabel mouseOver;
	private static AITurnWindow window;
	
	public AITurnWindow(final AIPlayer ai) {
		window = this;
		frame = new JFrame();
		p = new JPanel();
		frame.add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		p.setLayout(new BorderLayout());
		JButton nextTurn = new JButton();
		p.add(nextTurn, BorderLayout.NORTH);
		nextTurn.setText("Make Turn");
		nextTurn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!AIPlayer.isInProgress()) {
					ai.makeTurn(window);
				}
			}
		});
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		p.add(progressBar, BorderLayout.CENTER);
		
		label = new JLabel("----------------------------------------------------------------------------------------------------------------------");
		p.add(label, BorderLayout.SOUTH);
		
		mouseOver = new JLabel("Tile: 00");
		p.add(mouseOver,BorderLayout.EAST);
		
		frame.pack();
		frame.setVisible(true);
	}

	public void setProgressMax(int pow) {
		progressBar.setMaximum(pow);
	}

	public void updateProgressBar(int i) {
	//	progressBar.setIndeterminate(true);
		if (progressBar.getValue() >= progressBar.getMaximum()) {
			progressBar.setMaximum(progressBar.getMaximum() * 2);
		}
		if (progressBar.getMaximum() >= Integer.MAX_VALUE) {
			progressBar.setIndeterminate(true);
			progressBar.setStringPainted(false);
		}
		progressBar.setValue(progressBar.getValue() + i);
	}

	public void resetProgress() {
		progressBar.setIndeterminate(false);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);
		progressBar.setMaximum(100);
	}
	
	public void setSouthLabelText(String text) {
		label.setText(text);
	//	frame.pack();
	}
	
	public static void setMouseOver(int tile) {
		window.mouseOver.setText("Tile: " + tile);
	}
}
