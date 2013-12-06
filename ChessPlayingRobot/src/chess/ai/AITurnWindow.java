package chess.ai;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class AITurnWindow {
	private JFrame frame;
	private JPanel p;
	private JProgressBar progressBar;
	private AITurnWindow window = this;
	
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
				if (!AIPlayer.isInProgress()) {
					ai.makeTurn(window);
				}
			}
		});
		
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		p.add(progressBar, BorderLayout.SOUTH);
		
		
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
		progressBar.setValue(progressBar.getValue() + i);
	}

	public void resetProgress() {
		progressBar.setValue(0);		
	}
}
