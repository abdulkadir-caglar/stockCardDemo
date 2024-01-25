package view.internalFrame;

import view.internalFrame.base.BaseInternalFrame;
import view.internalFrame.nav.NavigationPanel;
import view.mainFrame.MainFrame;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import controller.dataController.CardController;
import controller.dataController.KdvController;
import controller.frameController.StockCardController;
import model.entity.Card;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class StockCardFrame extends BaseInternalFrame implements ActionListener{
	private static StockCardFrame instance;
	
	private NavigationPanel navigationPanel;
	public JTextField codeTF;
	public JTextField nameTF;
	public JTextField barcodeTF;
	public JButton btnClear;
	public JButton btnCreateCard;
	public JTextArea descriptionTA;
	public JComboBox<Integer> stockTypeCB;
	public JComboBox<String> unitCB;
	public JComboBox<Integer> kdvTypeCB;
	public JTextField searchTF;
	public JLabel lblSearch;
	public JButton btnSearch;
	
	private CardController cardController;
	private KdvController kdvController;
	
	public int index = 0;
	public Integer[] kdvTipId;
	
	public static StockCardFrame getInstance() {
		if(instance == null) {
			instance = new StockCardFrame();
		}
		return instance;
	}

	public StockCardFrame() {
		setTitle("Stok Kartı");
		navigationPanel = new NavigationPanel();
		getContentPane().add(navigationPanel);
		cardController = new CardController();
		kdvController = new KdvController();
		
		btnCreateCard = new JButton("Oluştur");
		btnCreateCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCreateCard.setBounds(895, 472, 161, 41);
		getContentPane().add(btnCreateCard);
		btnCreateCard.addActionListener(this);
		
		JLabel lblStockCode = new JLabel("Stok Kodu");
		lblStockCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStockCode.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockCode.setBounds(34, 79, 296, 41);
		getContentPane().add(lblStockCode);
		
		codeTF = new JTextField();
		codeTF.setBounds(34, 130, 296, 41);
		getContentPane().add(codeTF);
		codeTF.setColumns(10);
		
		JLabel lblStockName = new JLabel("Stok Adı");
		lblStockName.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStockName.setBounds(34, 198, 296, 41);
		getContentPane().add(lblStockName);
		
		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(34, 249, 296, 41);
		getContentPane().add(nameTF);
		
		JLabel lblBarcode = new JLabel("Barkod");
		lblBarcode.setHorizontalAlignment(SwingConstants.CENTER);
		lblBarcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblBarcode.setBounds(34, 319, 296, 41);
		getContentPane().add(lblBarcode);
		
		barcodeTF = new JTextField();
		barcodeTF.setColumns(10);
		barcodeTF.setBounds(34, 370, 296, 41);
		getContentPane().add(barcodeTF);
		
		JLabel lblDescription = new JLabel("Açıklama");
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDescription.setBounds(435, 247, 296, 41);
		getContentPane().add(lblDescription);
		
		descriptionTA = new JTextArea();
		descriptionTA.setBorder(new LineBorder(new Color(0, 0, 0)));
		descriptionTA.setBounds(435, 319, 296, 92);
		getContentPane().add(descriptionTA);
		
		JLabel lblStockType = new JLabel("Stok Tipi");
		lblStockType.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblStockType.setBounds(820, 79, 296, 41);
		getContentPane().add(lblStockType);
		
		JLabel lblUnit = new JLabel("Birim");
		lblUnit.setHorizontalAlignment(SwingConstants.CENTER);
		lblUnit.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUnit.setBounds(820, 198, 296, 41);
		getContentPane().add(lblUnit);
		
		JLabel lblKdvType = new JLabel("KDV Tipi");
		lblKdvType.setHorizontalAlignment(SwingConstants.CENTER);
		lblKdvType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblKdvType.setBounds(820, 319, 296, 41);
		getContentPane().add(lblKdvType);
		
		stockTypeCB = new JComboBox<Integer>();
		stockTypeCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		stockTypeCB.setModel(new DefaultComboBoxModel<Integer>(new Integer[] {1, 2, 3}));
		stockTypeCB.setBounds(820, 130, 296, 41);
		getContentPane().add(stockTypeCB);
		
		unitCB = new JComboBox<String>();
		unitCB.setModel(new DefaultComboBoxModel<String>(new String[] {"Birim A", "Birim B", "Birim C"}));
		unitCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		unitCB.setBounds(820, 249, 296, 41);
		getContentPane().add(unitCB);
		
		getKdvTips();
		kdvTypeCB = new JComboBox<Integer>();
		kdvTypeCB.setFont(new Font("Tahoma", Font.PLAIN, 16));
		kdvTypeCB.setModel(new DefaultComboBoxModel<Integer>(kdvTipId));
		kdvTypeCB.setBounds(820, 370, 296, 41);
		getContentPane().add(kdvTypeCB);
		
		btnClear = new JButton("Temizle");
		btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnClear.setBounds(494, 472, 177, 41);
		getContentPane().add(btnClear);
		btnClear.addActionListener(this);
		
		searchTF = new JTextField();
		searchTF.setColumns(10);
		searchTF.setBounds(494, 103, 177, 41);
		getContentPane().add(searchTF);
		
		lblSearch = new JLabel("Ara");
		lblSearch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearch.setBounds(494, 63, 177, 41);
		getContentPane().add(lblSearch);
		
		ImageIcon image = new ImageIcon("C:\\Users\\PC\\eclipse-workspace\\workshop\\icon\\search.png");
		image.setImage(image.getImage().getScaledInstance(34,34,Image.SCALE_SMOOTH));
		btnSearch = new JButton(image);
		btnSearch.setBounds(565, 160, 41, 41);
		getContentPane().add(btnSearch);
		btnSearch.addActionListener(this);
		
//==========================================================================================================================================================
//			ACTION LISTENERS FOR NAVIGATION PANEL		
//==========================================================================================================================================================
		
		navigationPanel.btnNext.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				index++;
				ResultSet rs= cardController.sortAsc();
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
				ResultSet rs= cardController.sortAsc();
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
					ResultSet rs = cardController.findFirst();
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
					ResultSet rs = cardController.sortAsc();
						
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
				if(!cardController.check(codeTF.getText())) {
					JOptionPane.showMessageDialog(null, "Lütfen geçerli bir stok kodu giriniz.");
				}else {
					cardController.update(StockCardController.getInstance().setCard());
					JOptionPane.showMessageDialog(null, "Kart güncellendi!");
				}
			}
		});
		
		navigationPanel.btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cardController.check(codeTF.getText())) {
					cardController.delete(codeTF.getText());
					setContainersNull();
					JOptionPane.showMessageDialog(null, "Kart silindi.");
				}else {
					JOptionPane.showMessageDialog(null, "Kayıtlı kart bulunamadı.");
				}
			}
		});
	}

	//ACTION LISTENERS FOR THIS CLASS
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnClear) {
			setContainersNull();
			index = 0;
		}else if(e.getSource() == btnCreateCard) {
			if(codeTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Stok kodu boş bırakılamaz.");
				
			}else if(!cardController.check(codeTF.getText())) {
				cardController.save(StockCardController.getInstance().setCard());
				JOptionPane.showMessageDialog(null, "Kart başarıyla kaydedildi!");
			}else {
				JOptionPane.showMessageDialog(null, codeTF.getText() + " kodlu kart zaten mevcut.");
			}
		}else if(e.getSource() == btnSearch) {
			ResultSet rs = cardController.search(searchTF.getText());
			showSearch(rs);
		}
	}
	
	//METHODS
	public Card setCard() {
		Card card = new Card();
		
		card.setStokKodu(codeTF.getText());
		card.setStokAdi(nameTF.getText());
		card.setStokTipi((Integer)stockTypeCB.getSelectedItem());
		card.setBirim((String)unitCB.getSelectedItem());
		card.setBarkod(barcodeTF.getText());
		card.setkdvTipId((Integer)kdvTypeCB.getSelectedItem());
		card.setAciklama(descriptionTA.getText());
		card.setTarih(new Date(new java.util.Date().getTime()));
		
		return card;
	}
	
	public void setContainers(ResultSet rs) {
		try {
			codeTF.setText(rs.getString("stok_kodu"));
			nameTF.setText(rs.getString("stok_adi"));
			stockTypeCB.setSelectedItem(rs.getInt("stok_tipi"));
			unitCB.setSelectedItem(rs.getString("birim"));
			barcodeTF.setText(rs.getString("barkod"));
			kdvTypeCB.setSelectedItem(rs.getInt("kdv_tip_id"));
			descriptionTA.setText(rs.getString("aciklama"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setContainersNull() {
		codeTF.setText(null);
		nameTF.setText(null);
		stockTypeCB.setSelectedIndex(0);;
		unitCB.setSelectedIndex(0);
		barcodeTF.setText(null);
		kdvTypeCB.setSelectedIndex(0);
		descriptionTA.setText(null);
	}
	
	public void getKdvTips() {
		try {
			int val = 0;
			ResultSet rs = kdvController.findAll();
			
			rs.last();
			kdvTipId = new Integer[rs.getRow()];
			rs.beforeFirst();
			
			while(rs.next()) {
				kdvTipId[val] = rs.getInt("id");
				val++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void showSearch(ResultSet rs) {
		try {
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