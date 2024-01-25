package view.internalFrame.base;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import view.mainFrame.MainFrame;

public abstract class BaseInternalFrame extends JInternalFrame{
	public BaseInternalFrame() {
		setBounds(0, 0, 1172, 614);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		getContentPane().setLayout(null);
		
		addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				dispose();
				MainFrame.getInstance().control("stockCardClosed");
			}
		});
	}
}