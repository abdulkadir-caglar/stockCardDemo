package view.internalFrame.nav;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public 
class NavigationPanel extends JPanel {
	
	public JButton btnFirst;
	public JButton btnPrevious;
	public JButton btnNext;
	public JButton btnLast;
	public JButton btnSave;
	public JButton btnDelete;
	
	public NavigationPanel() {
		setBounds(0, 0, 1160, 41);
		setLayout(null);
		
		btnFirst = new JButton(getIcon("leftCircle.png"));
		btnFirst.setBounds(0, 0, 33, 41);
		btnFirst.setToolTipText("İlk Kayıt");
		this.add(btnFirst);
		btnFirst.addActionListener(null);
		
		btnPrevious = new JButton(getIcon("left.png"));
		btnPrevious.setBounds(32, 0, 33, 41);
		btnPrevious.setToolTipText("Önceki");
		this.add(btnPrevious);
		
		btnNext = new JButton(getIcon("right.png"));
		btnNext.setBounds(63, 0, 33, 41);
		btnNext.setToolTipText("Sonraki");
		this.add(btnNext);
		
		btnLast = new JButton(getIcon("rightCircle.png"));
		btnLast.setBounds(93, 0, 33, 41);
		btnLast.setToolTipText("Son Kayıt");
		this.add(btnLast);
		
		btnSave = new JButton(getIcon("save.png"));
		btnSave.setBounds(1094, 0, 33, 41);
		btnSave.setToolTipText("Kaydet");
		this.add(btnSave);
		
		btnDelete = new JButton(getIcon("delete.png"));
		btnDelete.setBounds(1127, 0, 33, 41);
		btnDelete.setToolTipText("Sil");
		this.add(btnDelete);
	}
	
	public ImageIcon getIcon(String img) {
		ImageIcon image = new ImageIcon("C:\\Users\\PC\\eclipse-workspace\\workshop\\icon\\" + img);
		image.setImage(image.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		return image;
	}
}
