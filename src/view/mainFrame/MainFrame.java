package view.mainFrame;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import config.Database;
import view.internalFrame.StockCardFrame;
import view.internalFrame.StockCardListFrame;
import view.internalFrame.KdvFrame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JToolBar;
import java.awt.Dimension;

public class MainFrame extends JFrame implements ActionListener{
	private static MainFrame instance;

	private JPanel mainFrame;
	private JMenuBar mainMenuBar;
	private Font itemFont;
	private JMenuItem stockCard;
	private JMenuItem stockCardList;
	private JMenuItem vatTypeCard;
	private JDesktopPane mainDesktopPane;
	
	private StockCardFrame stockCardFrame;
	private StockCardListFrame stockCardListFrame;
	private KdvFrame vatTypeCardFrame;
	private JToolBar mainToolBar;
	private JButton icon1;
	private JButton icon2;
	private JButton icon3;
	
	public boolean frameControl = false;
	public boolean isStockCardOpen = false;
	public boolean isStockListOpen = false;
	public boolean isKdvOpen = false;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Database db = new Database();
					//db.createCardDatabase();
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static MainFrame getInstance() {
	    if (instance == null) {
	        instance = new MainFrame();
	    }
	    return instance; 
	}

	private MainFrame() {
		setTitle("Stok Kartı");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1186, 710);
		setLocationRelativeTo(null);
		
		stockCardFrame = new StockCardFrame();
		stockCardListFrame = new StockCardListFrame();
		vatTypeCardFrame = new KdvFrame();
		
		
		mainFrame = new JPanel();
		mainFrame.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(mainFrame);
		
		mainFrame.setLayout(null);
		
		mainDesktopPane = new JDesktopPane();
		mainDesktopPane.setBounds(0, 37, 1172, 614);
		mainFrame.add(mainDesktopPane);
//		mainDesktopPane.add(stockCardFrame);
//		mainDesktopPane.add(stockCardListFrame);
//		mainDesktopPane.add(vatTypeCardFrame);
		
		mainToolBar = new JToolBar();
		mainToolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		mainToolBar.setBounds(0, 0, 1172, 38);
		mainFrame.add(mainToolBar);
		
		icon1 = new JButton(getIcon("card.png"));
		icon1.setPreferredSize(new Dimension(32, 28));
		mainToolBar.add(icon1);
		icon1.addActionListener(this);
		
		icon2 = new JButton(getIcon("list.png"));
		icon2.setPreferredSize(new Dimension(32, 28));
		mainToolBar.add(icon2);
		icon2.addActionListener(this);
		
		icon3 = new JButton(getIcon("vat.png"));
		icon3.setPreferredSize(new Dimension(32, 28));
		mainToolBar.add(icon3);
		icon3.addActionListener(this);
		
		mainMenuBar = new JMenuBar();
		mainMenuBar.setBounds(0, 19, 1172, 33);
		
		JMenu stockMenu = new JMenu("Stok");
		stockMenu.setBackground(Color.LIGHT_GRAY);
		stockMenu.setBounds(0, 19, 111, 33);
		
		itemFont = new Font("Yu Gothic Medium", Font.PLAIN, 12);
		
		stockCard = new JMenuItem("Stok Kartı");
		stockCard.setFont(itemFont);
		stockCard.addActionListener(this);
		
		stockCardList = new JMenuItem("Stok Kartlarını Görüntüle");
		stockCardList.setFont(itemFont);
		stockCardList.addActionListener(this);
		
		vatTypeCard = new JMenuItem("KDV Tip Kartı");
		vatTypeCard.setFont(itemFont);
		vatTypeCard.addActionListener(this);
		
		stockMenu.add(stockCard);
		stockMenu.add(stockCardList);
		stockMenu.add(vatTypeCard);
		
		mainMenuBar.add(stockMenu);
		this.setJMenuBar(mainMenuBar);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == stockCard || e.getSource() == icon1) {
			if(!isStockCardOpen) {
				mainDesktopPane.removeAll();
				stockCardFrame = new StockCardFrame();
				mainDesktopPane.add(stockCardFrame).setVisible(true);
				control("stockCard");
			}else return;
			
		}else if (e.getSource() == stockCardList || e.getSource() == icon2) {
			if(!isStockListOpen) {
				mainDesktopPane.removeAll();
				stockCardListFrame = new StockCardListFrame();
				mainDesktopPane.add(stockCardListFrame).setVisible(true);
				control("stockList");
			}else return;
			
		}else if (e.getSource() == vatTypeCard || e.getSource() == icon3) {
			if(!isKdvOpen) {
				mainDesktopPane.removeAll();
				vatTypeCardFrame = new KdvFrame();
				mainDesktopPane.add(vatTypeCardFrame).setVisible(true);
				control("kdv");
			}else return;
		}
	}
	
	public ImageIcon getIcon(String img) {
		ImageIcon image = new ImageIcon("C:\\Users\\PC\\eclipse-workspace\\workshop\\icon\\" + img);
		image.setImage(image.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH));
		return image;
	}
	
	public void control(String str) {
		switch(str) {
			case "stockCard":
				isStockCardOpen = true;
				isStockListOpen = false;
				isKdvOpen = false;
				stockCardListFrame.setVisible(false);
				vatTypeCardFrame.setVisible(false);
				break;
			case "stockList":
				isStockListOpen = true;
				isStockCardOpen = false;
				isKdvOpen = false;
				stockCardFrame.setVisible(false);
				vatTypeCard.setVisible(false);
				break;
			case "kdv":
				isKdvOpen = true;
				isStockListOpen = false;
				isStockCardOpen = false;
				stockCardFrame.setVisible(false);
				stockCardListFrame.setVisible(false);
			case "stockCardClosed":
	            isStockCardOpen = false;
	            break;	
		}
	}
	
	public void setIsOpenFalse() {
		isStockCardOpen = false;
		isStockListOpen = false;
		isKdvOpen = false;
	}
	
	public JButton getIcon() {
		return icon1;
	}
}