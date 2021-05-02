package Palabrejas;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.GridBagConstraints;
import java.text.SimpleDateFormat;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.awt.Font;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Gui extends JFrame {
	
	JPanel panelShowStadisticsAllPlayer;
	JPanel panelButtonsInputWordsContext;
	JPanel panelStadisticsCurrentPlayer;
	JPanel panelInputUsername;
	JPanel panelInputWords;
	JPanel panelViewWords;
	JLabel labelStadisticsCurrentPlayer;
	JLabel labelStadisticsAllPlayers;
	JLabel labelInputUsername;
	JLabel labelInputWords;
	JLabel labelViewWords;
	JLabel labelCountDown;
	JLabel labelGameOver;
	JLabel labelWord;
	JTextField textFieldInputUserName;
	JTextField textFieldInputWord;
	JTextArea textAreaShowStadisticsAllPlayer;
	JButton buttonClosePanelStadisticsAllPlayers;
	JButton buttonViewStadisticsAllPlayers;
	JButton buttonSendUserName;
	JButton buttonDeleteWord;
	JButton buttonViewWords;
	JButton buttonEndSerie;
	JButton buttonSendWord;
	JScrollPane scrollpane;
	GridBagConstraints constraints;
	Controller controller;
	Listener listener;
	Timer timerDeployWords;
	Timer timerCountDown;
	private int timeLimit = 0;
	private int index = 0;
	
	public Gui() {
		initGui();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1000, 600));
		this.setLocationRelativeTo(null);
		this.addWindowListener( new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent eventWindowClosing) {
				controller.setCurrentRememberWords(0);
				controller.saveMapPlayers();
			}
		});
		this.setTitle("Palabrejas");
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void initGui() {
		
		this.getContentPane().setLayout(new BorderLayout());
		constraints = new GridBagConstraints();
		controller = new Controller();
		listener = new Listener();
		timeLimit = controller.getTimeLimit();
		
		// Configuración de la pantalla 'Input UserName'
		
		panelInputUsername = new JPanel(new GridBagLayout());
		panelInputUsername.setPreferredSize(new Dimension(500, 400));
		
		labelInputUsername = new JLabel("Introduce tu nombre");
		constraints = makeConstraints(2, 2, 1, 1, 0, GridBagConstraints.NONE);
		panelInputUsername.add(labelInputUsername, constraints);
		
		textFieldInputUserName = new JTextField(20);
		constraints = makeConstraints(1, 3, 2, 1, 0, GridBagConstraints.NONE);
		panelInputUsername.add(textFieldInputUserName, constraints);
		
		buttonSendUserName = new JButton("send");
		buttonSendUserName.addActionListener(listener);
		constraints = makeConstraints(2, 4, 1, 1, 0, GridBagConstraints.NONE);
		panelInputUsername.add(buttonSendUserName, constraints);
		panelInputUsername.setVisible(true);
		
		add(panelInputUsername, BorderLayout.CENTER);
		
		// Configuración de la pantalla 'Show Words'
		
		panelViewWords = new JPanel(new GridBagLayout());
		panelViewWords.setPreferredSize(new Dimension(500, 400));
		
		labelViewWords = new JLabel("Memoriza las siguientes palabras");
		constraints = makeConstraints(1, 1, 1, 1, 0, GridBagConstraints.NONE);
		panelViewWords.add(labelViewWords, constraints);
		
		labelWord = new JLabel();
		constraints = makeConstraints(1, 2, 1, 1, 2, GridBagConstraints.NONE);
		panelViewWords.add(labelWord, constraints);
		panelViewWords.setVisible(true);
		
		// add(panelViewWords); Esto se hace en el controlador de eventos del boton "sendUserName"
		
		// Configuración de la pantalla 'Input Words'
		
		panelInputWords = new JPanel(new GridBagLayout());
		panelInputWords.setPreferredSize(new Dimension(700, 600));
		
		labelCountDown = new JLabel();
		constraints = makeConstraints(1, 1, 1, 2, 0, GridBagConstraints.NONE);
		panelInputWords.add(labelCountDown, constraints);
		
		labelInputWords = new JLabel("Ingresa las palabras que recuerdes");
		constraints = makeConstraints(1, 3, 1, 1, 20, GridBagConstraints.NONE);
		panelInputWords.add(labelInputWords, constraints);
		
		textFieldInputWord = new JTextField(15);
		textFieldInputWord.addKeyListener(listener);
		constraints = makeConstraints(1, 4, 1, 1, 0, GridBagConstraints.NONE);
		panelInputWords.add(textFieldInputWord, constraints);
		panelInputWords.setVisible(true);
		
		//add(panelInputWords); Esto se agrega en la función deployWords(), cuando es llamada desde el evento del boton "sendUserName"
		
		// Configuración de la pantalla 'Buttons Words'
		
		panelButtonsInputWordsContext = new JPanel(new GridBagLayout());
		panelButtonsInputWordsContext.setPreferredSize(new Dimension(400, 400));
		
		buttonViewWords = new JButton("View Words");
		buttonViewWords.addActionListener(listener);
		buttonViewWords.setPreferredSize(new Dimension(123, 25));
		constraints = makeConstraints(1, 1, 1, 1, 0, GridBagConstraints.NONE);
		panelButtonsInputWordsContext.add(buttonViewWords, constraints);
		
		buttonEndSerie = new JButton("End Serie");
		buttonEndSerie.addActionListener(listener);
		buttonEndSerie.setPreferredSize(new Dimension(123, 25));
		constraints = makeConstraints(2, 1, 1, 1, 0, GridBagConstraints.NONE);
		panelButtonsInputWordsContext.add(buttonEndSerie, constraints);
		
		buttonDeleteWord = new JButton("Delete Word");
		buttonDeleteWord.addActionListener(listener);
		constraints = makeConstraints(1, 2, 1, 1, 0, GridBagConstraints.NONE);
		panelButtonsInputWordsContext.add(buttonDeleteWord, constraints);
		
		buttonSendWord = new JButton("Send Word");
		buttonSendWord.addActionListener(listener);
		buttonSendWord.setPreferredSize(new Dimension(123, 25));
		constraints = makeConstraints(2, 2, 1, 1, 0, GridBagConstraints.NONE);
		panelButtonsInputWordsContext.add(buttonSendWord, constraints);
		panelButtonsInputWordsContext.setVisible(true);
		
		// add(panelButtonsInputWordsContext); Esto se agrega en la función deployWords(), cuando es llamada desde el evento del boton "sendUserName"
		
		// Configuración de la pantalla 'Stadistics Current Player'
		
		panelStadisticsCurrentPlayer = new JPanel(new GridBagLayout());
		panelInputWords.setPreferredSize(new Dimension(700, 600));
		
		labelGameOver = new JLabel("Game Over");
		constraints = makeConstraints(1, 1, 1, 1, 0, GridBagConstraints.NONE);
		panelStadisticsCurrentPlayer.add(labelGameOver, constraints);
		
		labelStadisticsCurrentPlayer = new JLabel();
		constraints = makeConstraints(1, 2, 1, 1, 0, GridBagConstraints.NONE);
		panelStadisticsCurrentPlayer.add(labelStadisticsCurrentPlayer, constraints);
		
		buttonViewStadisticsAllPlayers = new JButton("View Stadistics of All Players");
		buttonViewStadisticsAllPlayers.addActionListener(listener);
		constraints = makeConstraints(1, 3, 1, 1, 0, GridBagConstraints.NONE);
		panelStadisticsCurrentPlayer.add(buttonViewStadisticsAllPlayers, constraints);
		panelStadisticsCurrentPlayer.setVisible(true);
		
		// Configuración de la pantalla 'Stadistics All Players'
		
		panelShowStadisticsAllPlayer = new JPanel(new BorderLayout());
		panelShowStadisticsAllPlayer.setPreferredSize(new Dimension(700, 600));
		
		labelStadisticsAllPlayers = new JLabel("Stadistics of All Players", SwingConstants.CENTER);
		panelShowStadisticsAllPlayer.add(labelStadisticsAllPlayers, BorderLayout.PAGE_START);
		
		textAreaShowStadisticsAllPlayer = new JTextArea();
		// El texto se agrega en la función deployWords(), cuando es llamada desde el evento del boton "buttonViewStadisticsAllPlayers"
		textAreaShowStadisticsAllPlayer.setFont(new Font("Serif", Font.ITALIC, 16));
		textAreaShowStadisticsAllPlayer.setWrapStyleWord(true);
		textAreaShowStadisticsAllPlayer.setEditable(false);
		textAreaShowStadisticsAllPlayer.setLineWrap(true);
		textAreaShowStadisticsAllPlayer.setOpaque(false);
        
        scrollpane = new JScrollPane(textAreaShowStadisticsAllPlayer);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        buttonClosePanelStadisticsAllPlayers = new JButton("Close");
        buttonClosePanelStadisticsAllPlayers.addActionListener(listener);
        panelShowStadisticsAllPlayer.add(buttonClosePanelStadisticsAllPlayers, BorderLayout.PAGE_END);
     
        panelShowStadisticsAllPlayer.add(scrollpane, BorderLayout.CENTER);
        panelShowStadisticsAllPlayer.setVisible(true);
        
        // add(panelShowStadisticsAllPlayer); Esto se hace en el controlador de eventos del boton "viewStadisticsOtherPlayers"
	}
	
	public GridBagConstraints makeConstraints(int gridx, int gridy, int gridwidth, int gridheight, int ipady, int fill) {
		
		GridBagConstraints constraints = new GridBagConstraints();
		
		constraints.gridx = gridx;
		constraints.gridy = gridy;
		constraints.gridwidth  = gridwidth;
		constraints.gridheight = gridheight;
		constraints.ipady = ipady;
		constraints.fill = fill;
		
		return constraints;
	}
	
	public void updateGUIStatus() {
		if(isGameOver()) {
			
			removeAllPanels();
			add(panelStadisticsCurrentPlayer, BorderLayout.CENTER);
			labelStadisticsCurrentPlayer.setText(controller.getStadistics());
			
			validate();
		}
		else if(controller.getAccomplishedSerie()) {
			
			timerCountDown.stop();
			labelCountDown.setText("");
			timeLimit = controller.getTimeLimit();
			
			removeAllPanels();
			add(panelViewWords, BorderLayout.CENTER);
			validate();
			
			index = 0;
			deployWords(controller.getWords());
			controller.setAccomplishedSerie(false);
		}
	}
	
	public void countDown() {
		
		ActionListener countdown=new ActionListener() {
		    public void actionPerformed(ActionEvent eventAction) {
		    	
		        SimpleDateFormat df = new SimpleDateFormat("mm:ss"); 
		        timeLimit -= 1000;
		        
		        labelCountDown.setText("Current Time: " + df.format(timeLimit));
		        
		        if(timeLimit <= 0) {
		        	controller.addWord(textFieldInputWord.getText());
					textFieldInputWord.setText("");
					updateGUIStatus();
					timerCountDown.stop();
		        }
		    }
		};
		timerCountDown = new Timer(1000, countdown);
		timerCountDown.start();
	}
	
	public void deployWords(List<String> words) {	
		
		ActionListener delay = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
				if(index != controller.getSizeWordsBackend()) {
					labelWord.setText(words.get(index));
				}
				else {		
					removeAllPanels();
					add(panelInputWords, BorderLayout.CENTER);
					add(panelButtonsInputWordsContext, BorderLayout.PAGE_END);
					validate();
					
					labelWord.setText("");
					countDown();
					timerDeployWords.stop();
				}
				index++;
		    }
		};
		timerDeployWords = new Timer(2000, delay);
		timerDeployWords.start();
	}
	
	public void removeAllPanels() {
		this.getContentPane().removeAll();
		this.repaint();
	}
	
	public Boolean isGameOver() {
		return controller.getGameOver()? true:false;
	}
	
	private class Listener implements ActionListener, KeyListener {

		@Override
		public void actionPerformed(ActionEvent eventActionPerformed) {
			
			if(eventActionPerformed.getSource() == buttonSendUserName) {			
				String username = textFieldInputUserName.getText();
				controller.setUserName(username);
				
				timeLimit = controller.getTimeLimit();
				
				removeAllPanels();
				add(panelViewWords, BorderLayout.CENTER);
				validate();
				
				deployWords(controller.getWords());
			}
			else if(eventActionPerformed.getSource() == buttonDeleteWord) {
				textFieldInputWord.setText("");
			}
			else if(eventActionPerformed.getSource() == buttonSendWord) {
				controller.addWord(textFieldInputWord.getText());
				textFieldInputWord.setText("");
				updateGUIStatus();
			}
			else if(eventActionPerformed.getSource() == buttonViewWords) {
				JOptionPane.showMessageDialog(
						null, 
						controller.getUserEnteredWords(), 
						"Palabras Digitadas", 
						JOptionPane.INFORMATION_MESSAGE
				);
			}
			else if(eventActionPerformed.getSource() == buttonEndSerie) {
				controller.endSerie();
				textFieldInputWord.setText("");
				updateGUIStatus();
			}
			else if(eventActionPerformed.getSource() == buttonViewStadisticsAllPlayers) {
				removeAllPanels();
				add(panelShowStadisticsAllPlayer, BorderLayout.CENTER);
				
				controller.updateUserRecords();
				textAreaShowStadisticsAllPlayer.setText(controller.getStadisticsAllPlayers());
				
				validate();
			}
			else if(eventActionPerformed.getSource() == buttonClosePanelStadisticsAllPlayers) {
				removeAllPanels();
				add(panelStadisticsCurrentPlayer, BorderLayout.CENTER);
				validate();
			}
		}

		@Override
		public void keyPressed(KeyEvent eventKeyPressed) {
			if(eventKeyPressed.getKeyCode() == KeyEvent.VK_ENTER) {
				if(eventKeyPressed.getSource() == textFieldInputWord) {
					controller.addWord(textFieldInputWord.getText());
					textFieldInputWord.setText("");
					updateGUIStatus();
				}
			}
			else if( (eventKeyPressed.getKeyCode() == KeyEvent.VK_DELETE) || 
					 (eventKeyPressed.getKeyCode() == KeyEvent.VK_BACK_SPACE)) {
				textFieldInputWord.setText("");
			}
		}

		@Override
		public void keyReleased(KeyEvent eventKeyReleased) {
		}

		@Override
		public void keyTyped(KeyEvent eventKeyTyped) {
		}
	}
}