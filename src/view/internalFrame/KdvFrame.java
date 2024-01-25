package view.internalFrame;

import view.internalFrame.base.BaseInternalFrame;
import view.internalFrame.nav.NavigationPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;

import controller.dataController.KdvController;
import model.entity.KdvTip;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class KdvFrame extends BaseInternalFrame implements ActionListener{
	
	private NavigationPanel navigationPanel;
	private JTextField codeTF;
	private JTextField nameTF;
	private JTextField oranTF;
	private JTextField searchTF;
	private JButton btnSearch ;
	private JButton btnCreate;
	private JButton btnClear;
	
	private KdvController kdvController;
	
	public int index = 0;
	
	public KdvFrame() {
		setTitle("KDV Tip Kartı");
		navigationPanel = new NavigationPanel();
		getContentPane().add(navigationPanel);
		kdvController = new KdvController();
		
		JLabel lblKdvCode = new JLabel("KDV Kodu");
		lblKdvCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblKdvCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKdvCode.setBounds(646, 86, 296, 41);
		getContentPane().add(lblKdvCode);
		
		codeTF = new JTextField();
		codeTF.setColumns(10);
		codeTF.setBounds(646, 137, 296, 41);
		getContentPane().add(codeTF);
		
		JLabel lblKdvName = new JLabel("KDV Adı");
		lblKdvName.setHorizontalAlignment(SwingConstants.CENTER);
		lblKdvName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKdvName.setBounds(646, 205, 296, 41);
		getContentPane().add(lblKdvName);
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(646, 256, 296, 41);
		getContentPane().add(nameTF);
		
		JLabel lblKdvOran = new JLabel("KDV Oranı");
		lblKdvOran.setHorizontalAlignment(SwingConstants.CENTER);
		lblKdvOran.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKdvOran.setBounds(646, 326, 296, 41);
		getContentPane().add(lblKdvOran);
		
		oranTF = new JTextField();
		oranTF.setColumns(10);
		oranTF.setBounds(646, 377, 296, 41);
		getContentPane().add(oranTF);
		
		JLabel searchKdv = new JLabel("Ara");
		searchKdv.setHorizontalAlignment(SwingConstants.CENTER);
		searchKdv.setFont(new Font("Tahoma", Font.PLAIN, 16));
		searchKdv.setBounds(163, 205, 296, 41);
		getContentPane().add(searchKdv);
		
		searchTF = new JTextField();
		searchTF.setColumns(10);
		searchTF.setBounds(163, 256, 296, 41);
		getContentPane().add(searchTF);
		
		btnCreate = new JButton("Oluştur");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreate.setBounds(698, 511, 199, 41);
		getContentPane().add(btnCreate);
		btnCreate.addActionListener(this);
		
		ImageIcon image = new ImageIcon("C:\\Users\\PC\\eclipse-workspace\\workshop\\icon\\search.png");
		image.setImage(image.getImage().getScaledInstance(34,34,Image.SCALE_SMOOTH));
		btnSearch = new JButton(image);
		btnSearch.setBounds(290, 326, 41, 41);
		getContentPane().add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnClear = new JButton("Temizle");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(698, 453, 199, 41);
		getContentPane().add(btnClear);
		btnClear.addActionListener(this);
		
//==========================================================================================================================================================
//		ACTION LISTENERS FOR NAVIGATION PANEL		
//==========================================================================================================================================================
	
		navigationPanel.btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index++;
				ResultSet rs= kdvController.sortAsc();
				try {
					int count = 0;
					while(rs.next()) {
						count++;
					}if(index>count) {
						index = count;
					}
					if(rs.absolute(index)) {
						setContainers(rs);
					}
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		navigationPanel.btnPrevious.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index --;
				if(index<1) {
					index=1;
				}
				ResultSet rs= kdvController.sortAsc();
				try {
					if(rs.absolute(index)) {
						setContainers(rs);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		navigationPanel.btnFirst.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					ResultSet rs = kdvController.findFirst();
					if(rs.next()) {
						setContainers(rs);
						index = 1;
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		navigationPanel.btnLast.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int val = 0;
					ResultSet rs = kdvController.sortAsc();
						
					try {
						while(rs.next()) {
							val++;
						}
						if(rs.absolute(val)) {
							setContainers(rs);
							index = val;
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
		});
		
		navigationPanel.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!oranControl()) {
					JOptionPane.showMessageDialog(null, "Lütfen geçerli bir oran giriniz.");
				}else if(!kdvController.check(codeTF.getText())) {
					JOptionPane.showMessageDialog(null, "Lütfen geçerli KDV kodu giriniz.");
				}else {
					kdvController.update(setKdv());
					JOptionPane.showMessageDialog(null, "Kart güncellendi!");
				}
			}
		});
		
		navigationPanel.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(kdvController.check(codeTF.getText())) {
					kdvController.delete(codeTF.getText());
					setContainersNull();
					JOptionPane.showMessageDialog(null, "Kart silindi.");
				}else {
					JOptionPane.showMessageDialog(null, "Kayıtlı KDV tipi bulunamadı.");
				}
			}
		});
}


	//ACTION LISTENERS FOR THIS CLASS
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnCreate) {
			if(codeTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "KDV kodu boş bırakılamaz.");
			}else if(!oranControl()) {
				JOptionPane.showMessageDialog(null, "Lütfen geçerli bir oran giriniz.");
			}else if(kdvController.check(codeTF.getText())) {
				JOptionPane.showMessageDialog(null, codeTF.getText() + " kodlu KDV tipi zaten mevcut.");
			}else {
				kdvController.save(setKdv());
				JOptionPane.showMessageDialog(null, "KDV Tipi başarıyla kaydedildi!");
			}
		}else if(e.getSource() == btnClear) {
			setContainersNull();
			index = 0;
		}else if(e.getSource() == btnSearch) {
			try {
				ResultSet rs = kdvController.search(searchTF.getText());
				if(rs.next()) {
					setContainers(rs);
					index = 0;
				}
				else {
					JOptionPane.showMessageDialog(null, "Kayıt bulunamadı.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	//METHODS
	public KdvTip setKdv() {
		KdvTip kdv = new KdvTip();
		
		kdv.setKod(codeTF.getText());
		kdv.setAd(nameTF.getText());
		kdv.setOran((Double)Double.parseDouble(oranTF.getText()));
		
		return kdv;
	}
	
	public void setContainers(ResultSet rs) {
		try {
			codeTF.setText(rs.getString("kod"));
			nameTF.setText(rs.getString("ad"));
			oranTF.setText(rs.getString("oran"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setContainersNull() {
		codeTF.setText(null);
		nameTF.setText(null);
		oranTF.setText(null);
	}
	
	public boolean oranControl() {
		try {
			Double.parseDouble(oranTF.getText());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
