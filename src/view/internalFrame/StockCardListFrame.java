package view.internalFrame;

import view.internalFrame.base.BaseInternalFrame;
import view.mainFrame.MainFrame;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.dataController.CardController;
import controller.dataController.KdvController;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class StockCardListFrame extends BaseInternalFrame implements ActionListener{
	
	private JScrollPane scrollPane;
	private JLabel search;
	private JTable table;
	private JTextField searchTF;
	private JButton btnSearch;
	private JButton btnListCode;
	private JButton btnListAll;
	private DefaultTableModel model;
	private Object[] columns;
	private Object[] rows;
	
	private JPopupMenu popMenu;
	private JMenuItem item;
	
	private CardController cardController;
	private KdvController kdvController;
	
	int row;
	
	public StockCardListFrame() {
		setTitle("Stok Kartları");
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 10, 796, 565);
		getContentPane().add(scrollPane);
		
		cardController = new CardController();
		kdvController = new KdvController();
		
		search = new JLabel("Ara");
		search.setHorizontalAlignment(SwingConstants.CENTER);
		search.setFont(new Font("Tahoma", Font.PLAIN, 16));
		search.setBounds(28, 141, 296, 41);
		getContentPane().add(search);
		
		searchTF = new JTextField();
		searchTF.setColumns(10);
		searchTF.setBounds(28, 192, 296, 41);
		getContentPane().add(searchTF);
		
		ImageIcon image = new ImageIcon("C:\\Users\\PC\\eclipse-workspace\\workshop\\icon\\search.png");
		image.setImage(image.getImage().getScaledInstance(34,34,Image.SCALE_SMOOTH));
		btnSearch = new JButton(image);
		btnSearch.setBounds(155, 262, 41, 41);
		getContentPane().add(btnSearch);
		btnSearch.addActionListener(this);
		
		btnListAll = new JButton("Tümünü Listele");
		btnListAll.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListAll.setBounds(28, 445, 296, 41);
		getContentPane().add(btnListAll);
		btnListAll.addActionListener(this);
		
		btnListCode = new JButton("Stok Koduna Göre Listele");
		btnListCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnListCode.setBounds(28, 509, 296, 41);
		getContentPane().add(btnListCode);
		btnListCode.addActionListener(this);
		
		model = new DefaultTableModel() {
			public boolean isCellEditable() {
				return false;
			}
		};
		
		table = new JTable(model);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		
		popMenu = new JPopupMenu();
		popMenu.setBounds(0,0, 0,0);
		item = new JMenuItem("Düzenle");
		popMenu.add(item);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				row = table.rowAtPoint(e.getPoint());
				if(row >= 0) {
					popMenu.show(table, e.getX(), e.getY());
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {}
		});
		
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame main = MainFrame.getInstance();
				main.isStockListOpen = false;
				main.getIcon().doClick();
				
				StockCardFrame stockCardFrame = new StockCardFrame();
				ResultSet rs = cardController.findByStockCode(table.getValueAt(row, 1).toString());
				try {
					if(rs.next()) {
						stockCardFrame.setContainers(rs);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnListAll) {
			getList("findAll");
		}
		if(e.getSource() == btnListCode) {
			getList("listByCode");
		}if(e.getSource() == btnSearch) {
			getList("search");
		}
	}
	
	public void getList(String method) {
		ResultSet cardRs = null;
		
		switch (method) {
		case "findAll":
			cardRs = cardController.findAll();
			break;

		case "listByCode":
			cardRs = cardController.listByCode();
			break;
		case "search":
			cardRs = cardController.listSearch(searchTF.getText());
			break;
		}
		
		columns = new Object[]{
				"Kart ID", "Stok Kodu", "Stok Adı", "Stok Tipi", "Birim", "Barkod","KDV Tip ID", 
				"KDV Kodu", "KDV Adı", "KDV Oranı", "Açıklama", "Tarih"
		};
		rows = new Object[12];
		model.setColumnIdentifiers(columns);
		
		model.setColumnCount(0);
		model.setRowCount(0);
		model.setColumnIdentifiers(columns);
		try {
			while(cardRs.next()) {
				ResultSet kdvRs = kdvController.findById(cardRs.getInt("kdv_tip_id"));
				if(kdvRs.next()) {
					
					rows[0] = cardRs.getString("id");
					rows[1] = cardRs.getString("stok_kodu");
					rows[2] = cardRs.getString("stok_adi");
					rows[3] = cardRs.getString("stok_tipi");
					rows[4] = cardRs.getString("birim");
					rows[5] = cardRs.getString("barkod");
					rows[6] = cardRs.getString("kdv_tip_id");
					rows[7] = kdvRs.getString("kod");
					rows[8] = kdvRs.getString("ad");
					rows[9] = kdvRs.getString("oran");
					rows[10] = cardRs.getString("aciklama");
					rows[11] = cardRs.getString("tarih");
					
					model.addRow(rows);
				}
			}
			table.setModel(model);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}	
	}
}