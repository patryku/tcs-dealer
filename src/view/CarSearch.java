package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import sql.CarSearchManager;
import view.components.OptionDisplay;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class CarSearch extends JFrame {

	private static final long serialVersionUID = -8957266627233713461L;
	private JPanel contentPane;
	private CarSearchManager manager = new CarSearchManager();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CarSearch frame = new CarSearch();
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
	public CarSearch() {
		setTitle("Wyszukiwarka");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel title = new JPanel();
		contentPane.add(title, BorderLayout.NORTH);
		
		JLabel lblTitle = new JLabel("Wyszukiwarka modeli");
		lblTitle.setFont(new Font("Dialog", Font.BOLD, 20));
		title.add(lblTitle);
		
		JPanel content = new JPanel();
		contentPane.add(content);
		content.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblEngine = new JLabel("Silnik");
		content.add(lblEngine, "2, 2");
		
		JLabel lblTyp = new JLabel("typ:");
		content.add(lblTyp, "4, 4");
		
		Vector<String> engTypes = new Vector<>();
		engTypes.add("dowolny");
		engTypes.addAll(manager.getEngineTypes());
		JComboBox<String> engineBox = new JComboBox<>(engTypes);
		content.add(engineBox, "6, 4, 4, 1, left, default");
		
		JLabel lblWyposazenie = new JLabel("Wyposazenie");
		content.add(lblWyposazenie, "16, 4");
		
		JScrollPane optionPane = new JScrollPane();
		content.add(optionPane, "16, 5, 1, 20");
		
		JPanel optionList = new JPanel();
		optionPane.setViewportView(optionList);
		optionList.setLayout(new GridLayout(0, 2, 0, 0));
		Vector<OptionDisplay> opts = manager.getOptions();
		for(OptionDisplay d : opts) {
			optionList.add(d);
		}
		
		JLabel lblMoc = new JLabel("moc (KM): ");
		content.add(lblMoc, "4, 6");
		
		JLabel lblOd = new JLabel("od");
		content.add(lblOd, "6, 6");
		
		JSpinner mocOd = new JSpinner();
		mocOd.setModel(new SpinnerNumberModel(1, 1, 2000, 1));
		mocOd.setEditor(new JSpinner.NumberEditor(mocOd, "#"));
		content.add(mocOd, "8, 6");
		
		JLabel lblDo = new JLabel("do");
		content.add(lblDo, "10, 6");
		
		JSpinner mocDo = new JSpinner();
		mocDo.setModel(new SpinnerNumberModel(2000, 1, 2000, 1));
		mocDo.setEditor(new JSpinner.NumberEditor(mocDo, "#"));
		content.add(mocDo, "12, 6");
		
		JLabel lblPojemnoscccm = new JLabel("pojemnosc (ccm):");
		content.add(lblPojemnoscccm, "4, 8");
		
		JLabel lblOd_1 = new JLabel("od");
		content.add(lblOd_1, "6, 8");
		
		JSpinner pojOd = new JSpinner();
		pojOd.setModel(new SpinnerNumberModel(1, 1, 9999, 1));
		pojOd.setEditor(new JSpinner.NumberEditor(pojOd, "#"));
		content.add(pojOd, "8, 8");
		
		JLabel lblDo_1 = new JLabel("do");
		content.add(lblDo_1, "10, 8");
		
		JSpinner pojDo = new JSpinner();
		pojDo.setModel(new SpinnerNumberModel(9999, 1, 9999, 1));
		pojDo.setEditor(new JSpinner.NumberEditor(pojDo, "#"));
		content.add(pojDo, "12, 8");
		
		JLabel lblMomentObrnm = new JLabel("moment obr (Nm):");
		content.add(lblMomentObrnm, "4, 10");
		
		JLabel lblOd_2 = new JLabel("od");
		content.add(lblOd_2, "6, 10");
		
		JSpinner momOd = new JSpinner();
		momOd.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
		momOd.setEditor(new JSpinner.NumberEditor(momOd, "#"));
		content.add(momOd, "8, 10");
		
		JLabel lblDo_2 = new JLabel("do");
		content.add(lblDo_2, "10, 10");
		
		JSpinner momDo = new JSpinner();
		momDo.setModel(new SpinnerNumberModel(3000, 1, 3000, 1));
		momDo.setEditor(new JSpinner.NumberEditor(momDo, "#"));
		content.add(momDo, "12, 10");
		
		JLabel lblNadwozie = new JLabel("Nadwozie");
		content.add(lblNadwozie, "2, 14");
		
		JLabel lblTyp_1 = new JLabel("typ:");
		content.add(lblTyp_1, "4, 16");
		
		Vector<String> bodyTypes = new Vector<>();
		bodyTypes.add("dowolny");
		bodyTypes.addAll(manager.getBodyTypes());
		JComboBox<String> bodyBox = new JComboBox<>(bodyTypes);
		content.add(bodyBox, "6, 16, 4, 1, left, default");
		
		JLabel lblLiczbaDrzwi = new JLabel("liczba drzwi:");
		content.add(lblLiczbaDrzwi, "4, 18");
		
		JLabel lblOd_3 = new JLabel("od");
		content.add(lblOd_3, "6, 18");
		
		JSpinner drzOd = new JSpinner();
		drzOd.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		content.add(drzOd, "8, 18");
		
		JLabel lblDo_3 = new JLabel("do");
		content.add(lblDo_3, "10, 18");
		
		JSpinner drzDo = new JSpinner();
		drzDo.setModel(new SpinnerNumberModel(9, 1, 9, 1));
		content.add(drzDo, "12, 18");
		
		JLabel lblInne = new JLabel("Inne");
		content.add(lblInne, "2, 22");
		
		JLabel lblCena = new JLabel("cena:");
		content.add(lblCena, "4, 24");
		
		JLabel lblOd_4 = new JLabel("od");
		content.add(lblOd_4, "6, 24");
		
		JSpinner cenaOd = new JSpinner();
		cenaOd.setModel(new SpinnerNumberModel(0, 0, null, 1));
		cenaOd.setEditor(new JSpinner.NumberEditor(cenaOd, "#"));
		content.add(cenaOd, "8, 24");
		
		JLabel lblDo_4 = new JLabel("do");
		content.add(lblDo_4, "10, 24");
		
		JSpinner cenaDo = new JSpinner();
		cenaDo.setModel(new SpinnerNumberModel(9999999, 0, 9999999, 1));
		cenaDo.setEditor(new JSpinner.NumberEditor(cenaDo, "#"));
		content.add(cenaDo, "12, 24");
		
		JPanel footer = new JPanel();
		contentPane.add(footer, BorderLayout.SOUTH);
		
		JButton btnClose = new JButton("Zamknij");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		JButton btnSearch = new JButton("Wyszukaj");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//TODO zbierz parametry i wyszukaj modele
			}
		});
		GroupLayout gl_footer = new GroupLayout(footer);
		gl_footer.setHorizontalGroup(
			gl_footer.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_footer.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnClose)
					.addPreferredGap(ComponentPlacement.RELATED, 524, Short.MAX_VALUE)
					.addComponent(btnSearch)
					.addContainerGap())
		);
		gl_footer.setVerticalGroup(
			gl_footer.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_footer.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_footer.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnSearch)
						.addComponent(btnClose))
					.addGap(12))
		);
		footer.setLayout(gl_footer);
		toFront();
	}
}
