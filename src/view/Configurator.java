package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import sql.ConfiguratorManager;
import view.components.OptionDisplay;
import javax.swing.BoxLayout;

public class Configurator extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ConfiguratorManager manager = new ConfiguratorManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Configurator frame = new Configurator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Configurator() {
		setResizable(false);
		setTitle("Konfigurator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel header = new JPanel();
		
		JLabel lblTitle = new JLabel("Konfigurator");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		header.add(lblTitle);
		
		final JPanel configPanel = new JPanel();
		
		JPanel info = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(header, GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(12)
					.addComponent(configPanel, GroupLayout.PREFERRED_SIZE, 590, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(info, GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(header, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(info, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
						.addComponent(configPanel, GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE))
					.addContainerGap())
		);
		GridBagLayout gbl_info = new GridBagLayout();
		gbl_info.columnWidths = new int[]{94, 81, 0};
		gbl_info.rowHeights = new int[]{20, 40, 40, 40, 40, 40, 200, 40};
		gbl_info.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_info.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		info.setLayout(gbl_info);
		
		JLabel lblTwjWybr = new JLabel("Twój wybór");
		GridBagConstraints gbc_lblTwjWybr = new GridBagConstraints();
		gbc_lblTwjWybr.insets = new Insets(0, 0, 5, 0);
		gbc_lblTwjWybr.anchor = GridBagConstraints.NORTH;
		gbc_lblTwjWybr.gridx = 1;
		gbc_lblTwjWybr.gridy = 0;
		info.add(lblTwjWybr, gbc_lblTwjWybr);
		
		JLabel lblModel = new JLabel("Model:");
		GridBagConstraints gbc_lblModel = new GridBagConstraints();
		gbc_lblModel.insets = new Insets(0, 0, 5, 5);
		gbc_lblModel.gridx = 0;
		gbc_lblModel.gridy = 1;
		info.add(lblModel, gbc_lblModel);
		
		final JLabel lblModelVal = new JLabel("");
		GridBagConstraints gbc_lblModelVal = new GridBagConstraints();
		gbc_lblModelVal.insets = new Insets(0, 0, 5, 0);
		gbc_lblModelVal.gridx = 1;
		gbc_lblModelVal.gridy = 1;
		info.add(lblModelVal, gbc_lblModelVal);
		
		JLabel lblNadwozie = new JLabel("Nadwozie:");
		GridBagConstraints gbc_lblNadwozie = new GridBagConstraints();
		gbc_lblNadwozie.insets = new Insets(0, 0, 5, 5);
		gbc_lblNadwozie.gridx = 0;
		gbc_lblNadwozie.gridy = 2;
		info.add(lblNadwozie, gbc_lblNadwozie);
		
		final JLabel lblNadwozieVal = new JLabel("");
		GridBagConstraints gbc_lblNadwozieVal = new GridBagConstraints();
		gbc_lblNadwozieVal.insets = new Insets(0, 0, 5, 0);
		gbc_lblNadwozieVal.gridx = 1;
		gbc_lblNadwozieVal.gridy = 2;
		info.add(lblNadwozieVal, gbc_lblNadwozieVal);
		
		JLabel lblWersja = new JLabel("Wersja:");
		GridBagConstraints gbc_lblWersja = new GridBagConstraints();
		gbc_lblWersja.insets = new Insets(0, 0, 5, 5);
		gbc_lblWersja.gridx = 0;
		gbc_lblWersja.gridy = 3;
		info.add(lblWersja, gbc_lblWersja);
		
		final JLabel lblWersjaVal = new JLabel("");
		GridBagConstraints gbc_lblWersjaVal = new GridBagConstraints();
		gbc_lblWersjaVal.insets = new Insets(0, 0, 5, 0);
		gbc_lblWersjaVal.gridx = 1;
		gbc_lblWersjaVal.gridy = 3;
		info.add(lblWersjaVal, gbc_lblWersjaVal);
		
		JLabel lblLakier = new JLabel("Lakier:");
		GridBagConstraints gbc_lblLakier = new GridBagConstraints();
		gbc_lblLakier.insets = new Insets(0, 0, 5, 5);
		gbc_lblLakier.gridx = 0;
		gbc_lblLakier.gridy = 4;
		info.add(lblLakier, gbc_lblLakier);
		
		final JLabel lblLakierVal = new JLabel("");
		GridBagConstraints gbc_lblLakierVal = new GridBagConstraints();
		gbc_lblLakierVal.insets = new Insets(0, 0, 5, 0);
		gbc_lblLakierVal.gridx = 1;
		gbc_lblLakierVal.gridy = 4;
		info.add(lblLakierVal, gbc_lblLakierVal);
		
		JLabel lblSilnik = new JLabel("Silnik:");
		GridBagConstraints gbc_lblSilnik = new GridBagConstraints();
		gbc_lblSilnik.insets = new Insets(0, 0, 5, 5);
		gbc_lblSilnik.gridx = 0;
		gbc_lblSilnik.gridy = 5;
		info.add(lblSilnik, gbc_lblSilnik);
		
		final JLabel lblSilnikVal = new JLabel("");
		GridBagConstraints gbc_lblSilnikVal = new GridBagConstraints();
		gbc_lblSilnikVal.insets = new Insets(0, 0, 5, 0);
		gbc_lblSilnikVal.gridx = 1;
		gbc_lblSilnikVal.gridy = 5;
		info.add(lblSilnikVal, gbc_lblSilnikVal);
		
		JLabel lblOpcje = new JLabel("Opcje:");
		GridBagConstraints gbc_lblOpcje = new GridBagConstraints();
		gbc_lblOpcje.insets = new Insets(0, 0, 5, 5);
		gbc_lblOpcje.gridx = 0;
		gbc_lblOpcje.gridy = 6;
		info.add(lblOpcje, gbc_lblOpcje);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		info.add(scrollPane, gbc_scrollPane);
		
		final JList<String> optionInfoList = new JList<>(new DefaultListModel<String>());
		optionInfoList.setOpaque(false);
		scrollPane.setViewportView(optionInfoList);
		
		JLabel lblCena = new JLabel("Cena:");
		GridBagConstraints gbc_lblCena = new GridBagConstraints();
		gbc_lblCena.insets = new Insets(0, 0, 0, 5);
		gbc_lblCena.gridx = 0;
		gbc_lblCena.gridy = 7;
		info.add(lblCena, gbc_lblCena);
		
		final JLabel lblCenaVal = new JLabel("");
		GridBagConstraints gbc_lblCenaVal = new GridBagConstraints();
		gbc_lblCenaVal.gridx = 1;
		gbc_lblCenaVal.gridy = 7;
		info.add(lblCenaVal, gbc_lblCenaVal);
		contentPane.setLayout(gl_contentPane);
		
		final CardLayout cards = new CardLayout(0, 0);
		configPanel.setLayout(cards);
		
		JPanel modelCard = new JPanel();
		configPanel.add(modelCard, "name_41301629141775");
		modelCard.setLayout(new BorderLayout(0, 0));
		
		JScrollPane modelPane = new JScrollPane();
		modelCard.add(modelPane);
		
		final JList<String> modelList = new JList<>(manager.getModels());
		modelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		modelList.setOpaque(false);
		modelPane.setViewportView(modelList);
		
		JPanel noteModelPanel = new JPanel();
		modelCard.add(noteModelPanel, BorderLayout.NORTH);
		
		JLabel lblNote = new JLabel("Wybierz model");
		noteModelPanel.add(lblNote);
		lblNote.setSize(0, 30);
		
		JPanel modelFooter = new JPanel();
		modelCard.add(modelFooter, BorderLayout.SOUTH);
		
		JButton btnModelNext = new JButton("Dalej");
		
		GroupLayout gl_modelFooter = new GroupLayout(modelFooter);
		gl_modelFooter.setHorizontalGroup(
			gl_modelFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modelFooter.createSequentialGroup()
					.addContainerGap(500, Short.MAX_VALUE)
					.addComponent(btnModelNext, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		gl_modelFooter.setVerticalGroup(
			gl_modelFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_modelFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnModelNext))
		);
		modelFooter.setLayout(gl_modelFooter);
		
		JPanel bodyCard = new JPanel();
		configPanel.add(bodyCard, "name_7253748656747");
		bodyCard.setLayout(new BorderLayout(0, 0));
		
		JScrollPane bodyPane = new JScrollPane();
		bodyCard.add(bodyPane, BorderLayout.CENTER);
		
		final JList<String> bodyList = new JList<>();
		bodyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bodyList.setOpaque(false);
		bodyPane.setViewportView(bodyList);
		
		JPanel noteBodyPanel = new JPanel();
		bodyCard.add(noteBodyPanel, BorderLayout.NORTH);
		
		JLabel lblWybierzNadwozie = new JLabel("Wybierz nadwozie");
		noteBodyPanel.add(lblWybierzNadwozie);
		
		JPanel bodyFooter = new JPanel();
		bodyCard.add(bodyFooter, BorderLayout.SOUTH);
		
		JButton btnBodyNext = new JButton("Dalej");
		
		JButton btnBodyPrev = new JButton("Wstecz");
		btnBodyPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblModelVal.setText("");
				cards.previous(configPanel);
			}
		});
		GroupLayout gl_bodyFooter = new GroupLayout(bodyFooter);
		gl_bodyFooter.setHorizontalGroup(
			gl_bodyFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bodyFooter.createSequentialGroup()
					.addComponent(btnBodyPrev, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
					.addComponent(btnBodyNext, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		gl_bodyFooter.setVerticalGroup(
			gl_bodyFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_bodyFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_bodyFooter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBodyNext)
						.addComponent(btnBodyPrev)))
		);
		bodyFooter.setLayout(gl_bodyFooter);
		
		JPanel versionCard = new JPanel();
		configPanel.add(versionCard, "name_12008434940921");
		versionCard.setLayout(new BorderLayout(0, 0));
		
		JScrollPane versionPane = new JScrollPane();
		versionCard.add(versionPane, BorderLayout.CENTER);
		
		final JList<String> versionList = new JList<>();
		versionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		versionList.setOpaque(false);
		versionPane.setViewportView(versionList);
		
		JPanel noteVersionPanel = new JPanel();
		versionCard.add(noteVersionPanel, BorderLayout.NORTH);
		
		JLabel lblWybierzWersj = new JLabel("Wybierz wersję");
		noteVersionPanel.add(lblWybierzWersj);
		
		JPanel versionFooter = new JPanel();
		versionCard.add(versionFooter, BorderLayout.SOUTH);
		
		JButton btnVersionNext = new JButton("Dalej");
		
		JButton btnVersionPrev = new JButton("Wstecz");
		btnVersionPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNadwozieVal.setText("");
				cards.previous(configPanel);
			}
		});
		
		GroupLayout gl_versionFooter = new GroupLayout(versionFooter);
		gl_versionFooter.setHorizontalGroup(
			gl_versionFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_versionFooter.createSequentialGroup()
					.addComponent(btnVersionPrev, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
					.addComponent(btnVersionNext, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		gl_versionFooter.setVerticalGroup(
			gl_versionFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_versionFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_versionFooter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnVersionNext)
						.addComponent(btnVersionPrev)))
		);
		versionFooter.setLayout(gl_versionFooter);
		
		JPanel paintCard = new JPanel();
		configPanel.add(paintCard, "name_17517423955962");
		paintCard.setLayout(new BorderLayout(0, 0));
		
		JScrollPane paintPane = new JScrollPane();
		paintCard.add(paintPane, BorderLayout.CENTER);
		
		final JList<String> paintList = new JList<>();
		paintList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		paintList.setOpaque(false);
		paintPane.setViewportView(paintList);
		
		JPanel notePaintPanel = new JPanel();
		paintCard.add(notePaintPanel, BorderLayout.NORTH);
		
		JLabel lblWybierzTypLakieru = new JLabel("Wybierz typ lakieru i kolor");
		notePaintPanel.add(lblWybierzTypLakieru);
		
		JPanel paintFooter = new JPanel();
		paintCard.add(paintFooter, BorderLayout.SOUTH);

		JButton btnPaintNext = new JButton("Dalej");
		
		JButton btnPaintPrev = new JButton("Wstecz");
		btnPaintPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblWersjaVal.setText("");
				cards.previous(configPanel);
			}
		});
		
		GroupLayout gl_paintFooter = new GroupLayout(paintFooter);
		gl_paintFooter.setHorizontalGroup(
			gl_paintFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_paintFooter.createSequentialGroup()
					.addComponent(btnPaintPrev, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
					.addComponent(btnPaintNext, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		gl_paintFooter.setVerticalGroup(
			gl_paintFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_paintFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_paintFooter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnPaintNext)
						.addComponent(btnPaintPrev)))
		);
		paintFooter.setLayout(gl_paintFooter);
		
		JPanel engineCard = new JPanel();
		configPanel.add(engineCard, "name_15559110885049");
		engineCard.setLayout(new BorderLayout(0, 0));
		
		JScrollPane enginePane = new JScrollPane();
		engineCard.add(enginePane, BorderLayout.CENTER);
		
		final JList<String> engineList = new JList<>();
		engineList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		engineList.setOpaque(false);
		enginePane.setViewportView(engineList);
		
		JPanel noteEnginePanel = new JPanel();
		engineCard.add(noteEnginePanel, BorderLayout.NORTH);
		
		JLabel lblWybierzSilnik = new JLabel("Wybierz silnik");
		noteEnginePanel.add(lblWybierzSilnik);
		
		JPanel engineFooter = new JPanel();
		engineCard.add(engineFooter, BorderLayout.SOUTH);
		
		JButton btnEngineNext = new JButton("Dalej");
		
		JButton btnEnginePrev = new JButton("Wstecz");
		btnEnginePrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblLakierVal.setText("");
				cards.previous(configPanel);
			}
		});
		
		GroupLayout gl_engineFooter = new GroupLayout(engineFooter);
		gl_engineFooter.setHorizontalGroup(
			gl_engineFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_engineFooter.createSequentialGroup()
					.addComponent(btnEnginePrev, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
					.addComponent(btnEngineNext, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		gl_engineFooter.setVerticalGroup(
			gl_engineFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_engineFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_engineFooter.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnEngineNext)
						.addComponent(btnEnginePrev)))
		);
		engineFooter.setLayout(gl_engineFooter);
		
		JPanel optionCard = new JPanel();
		configPanel.add(optionCard, "name_20303560832219");
		optionCard.setLayout(new BorderLayout(0, 0));
		
		final JScrollPane optionPane = new JScrollPane();
		optionCard.add(optionPane, BorderLayout.CENTER);
		
		final JPanel optionList = new JPanel();
		optionPane.setViewportView(optionList);
		optionList.setLayout(new BoxLayout(optionList, BoxLayout.Y_AXIS));
		
		JPanel noteOptionPanel = new JPanel();
		optionCard.add(noteOptionPanel, BorderLayout.NORTH);
		
		JLabel lblWybierzWyposaenieDodatkowe = new JLabel("Wybierz wyposazenie dodatkowe");
		noteOptionPanel.add(lblWybierzWyposaenieDodatkowe);
		
		JPanel optionFooter = new JPanel();
		optionCard.add(optionFooter, BorderLayout.SOUTH);
		
		JButton btnOptionPrev = new JButton("Wstecz");
		btnOptionPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Component c : optionList.getComponents()) {
					((OptionDisplay) c).getCheckBox().setSelected(false);
				}
				optionInfoList.removeAll();
				optionInfoList.setModel(new DefaultListModel<String>());
				optionList.removeAll();
				lblSilnikVal.setText("");
				cards.previous(configPanel);
			}
		});
		
		GroupLayout gl_optionFooter = new GroupLayout(optionFooter);
		gl_optionFooter.setHorizontalGroup(
			gl_optionFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_optionFooter.createSequentialGroup()
					.addComponent(btnOptionPrev, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(500, Short.MAX_VALUE))
		);
		gl_optionFooter.setVerticalGroup(
			gl_optionFooter.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_optionFooter.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(btnOptionPrev))
		);
		optionFooter.setLayout(gl_optionFooter);
		
		btnModelNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice = modelList.getSelectedValue();
				if(choice == null)
					return;
				lblModelVal.setText(choice);
				bodyList.setListData(manager.getBodies(choice));
				cards.next(configPanel);
			}
		});
		
		btnBodyNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice = bodyList.getSelectedValue();
				if(choice == null)
					return;
				lblNadwozieVal.setText(choice);
				versionList.setListData(manager.getVersions(lblModelVal.getText(), choice));
				cards.next(configPanel);
			}
		});
		
		btnVersionNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice = versionList.getSelectedValue();
				if(choice == null)
					return;
				choice = choice.split(" ")[0];
				lblWersjaVal.setText(choice);
				paintList.setListData(manager.getPaints(lblModelVal.getText(), lblNadwozieVal.getText(), choice));
				cards.next(configPanel);
			}
		});
		
		btnPaintNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice = paintList.getSelectedValue();
				if(choice == null)
					return;
				lblLakierVal.setText(choice);
				choice = choice.split(" ")[1];
				engineList.setListData(manager.getEngines(lblModelVal.getText(),
						lblNadwozieVal.getText(), lblWersjaVal.getText(), choice));
				cards.next(configPanel);
			}
		});
		
		btnEngineNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String choice = engineList.getSelectedValue();
				if(choice == null)
					return;
				String[] vals = choice.split(", ");
				lblSilnikVal.setText(vals[0] + " " + vals[2]);
				lblCenaVal.setText(vals[4]);
				Vector<OptionDisplay> opts = manager.getOptions(lblCenaVal, optionInfoList, lblModelVal.getText(),
						lblNadwozieVal.getText(), lblWersjaVal.getText(), lblLakierVal.getText(), vals);
				for(OptionDisplay d : opts) {
					optionList.add(d);
				}
				cards.next(configPanel);
			}
		});
	}
}
