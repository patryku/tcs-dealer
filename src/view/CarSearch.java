package view;

import java.awt.BorderLayout;
import java.awt.Component;
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
	private JPanel optionList;
	private Vector<JComboBox<String>> boxes = new Vector<>();
	private Vector<JSpinner> spinners = new Vector<>();

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
		
		JLabel lblWyposazenie = new JLabel("Wyposazenie");
		content.add(lblWyposazenie, "16, 2");
		
		JLabel lblTyp = new JLabel("typ:");
		content.add(lblTyp, "4, 4");
		
		Vector<String> engTypes = new Vector<>();
		engTypes.add("dowolny");
		engTypes.addAll(manager.getEngineTypes());
		JComboBox<String> engineBox = new JComboBox<>(engTypes);
		engineBox.setName("typ_silnika");
		content.add(engineBox, "6, 4, 4, 1, left, default");
		boxes.add(engineBox);
		
		JScrollPane optionPane = new JScrollPane();
		content.add(optionPane, "16, 4, 1, 21");
		
		optionList = new JPanel();
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
		mocOd.setName("mocOd");
		mocOd.setModel(new SpinnerNumberModel(1, 1, 2000, 1));
		mocOd.setEditor(new JSpinner.NumberEditor(mocOd, "#"));
		content.add(mocOd, "8, 6");
		spinners.add(mocOd);
		
		JLabel lblDo = new JLabel("do");
		content.add(lblDo, "10, 6");
		
		JSpinner mocDo = new JSpinner();
		mocDo.setName("mocDo");
		mocDo.setModel(new SpinnerNumberModel(2000, 1, 2000, 1));
		mocDo.setEditor(new JSpinner.NumberEditor(mocDo, "#"));
		content.add(mocDo, "12, 6");
		spinners.add(mocDo);
		
		JLabel lblPojemnoscccm = new JLabel("pojemnosc (ccm):");
		content.add(lblPojemnoscccm, "4, 8");
		
		JLabel lblOd_1 = new JLabel("od");
		content.add(lblOd_1, "6, 8");
		
		JSpinner pojemnoscOd = new JSpinner();
		pojemnoscOd.setName("pojemnoscOd");
		pojemnoscOd.setModel(new SpinnerNumberModel(1, 1, 9999, 1));
		pojemnoscOd.setEditor(new JSpinner.NumberEditor(pojemnoscOd, "#"));
		content.add(pojemnoscOd, "8, 8");
		spinners.add(pojemnoscOd);
		
		JLabel lblDo_1 = new JLabel("do");
		content.add(lblDo_1, "10, 8");
		
		JSpinner pojemnoscDo = new JSpinner();
		pojemnoscDo.setName("pojemnoscDo");
		pojemnoscDo.setModel(new SpinnerNumberModel(9999, 1, 9999, 1));
		pojemnoscDo.setEditor(new JSpinner.NumberEditor(pojemnoscDo, "#"));
		content.add(pojemnoscDo, "12, 8");
		spinners.add(pojemnoscDo);
		
		JLabel lblMomentObrnm = new JLabel("moment obr (Nm):");
		content.add(lblMomentObrnm, "4, 10");
		
		JLabel lblOd_2 = new JLabel("od");
		content.add(lblOd_2, "6, 10");
		
		JSpinner moment_obrOd = new JSpinner();
		moment_obrOd.setName("moment_obrOd");
		moment_obrOd.setModel(new SpinnerNumberModel(1, 1, 3000, 1));
		moment_obrOd.setEditor(new JSpinner.NumberEditor(moment_obrOd, "#"));
		content.add(moment_obrOd, "8, 10");
		spinners.add(moment_obrOd);
		
		JLabel lblDo_2 = new JLabel("do");
		content.add(lblDo_2, "10, 10");
		
		JSpinner moment_obrDo = new JSpinner();
		moment_obrDo.setName("moment_obrDo");
		moment_obrDo.setModel(new SpinnerNumberModel(3000, 1, 3000, 1));
		moment_obrDo.setEditor(new JSpinner.NumberEditor(moment_obrDo, "#"));
		content.add(moment_obrDo, "12, 10");
		spinners.add(moment_obrDo);
		
		JLabel lblNadwozie = new JLabel("Nadwozie");
		content.add(lblNadwozie, "2, 14");
		
		JLabel lblTyp_1 = new JLabel("typ:");
		content.add(lblTyp_1, "4, 16");
		
		Vector<String> bodyTypes = new Vector<>();
		bodyTypes.add("dowolny");
		bodyTypes.addAll(manager.getBodyTypes());
		JComboBox<String> bodyBox = new JComboBox<>(bodyTypes);
		bodyBox.setName("typ_nadwozia");
		content.add(bodyBox, "6, 16, 4, 1, left, default");
		boxes.add(bodyBox);
		
		JLabel lblLiczbaDrzwi = new JLabel("liczba drzwi:");
		content.add(lblLiczbaDrzwi, "4, 18");
		
		JLabel lblOd_3 = new JLabel("od");
		content.add(lblOd_3, "6, 18");
		
		JSpinner liczba_drzwiOd = new JSpinner();
		liczba_drzwiOd.setName("liczba_drzwiOd");
		liczba_drzwiOd.setModel(new SpinnerNumberModel(1, 1, 9, 1));
		content.add(liczba_drzwiOd, "8, 18");
		spinners.add(liczba_drzwiOd);
		
		JLabel lblDo_3 = new JLabel("do");
		content.add(lblDo_3, "10, 18");
		
		JSpinner liczba_drzwiDo = new JSpinner();
		liczba_drzwiDo.setName("liczba_drzwiDo");
		liczba_drzwiDo.setModel(new SpinnerNumberModel(9, 1, 9, 1));
		content.add(liczba_drzwiDo, "12, 18");
		spinners.add(liczba_drzwiDo);
		
		JLabel lblInne = new JLabel("Inne");
		content.add(lblInne, "2, 22");
		
		JLabel lblCena = new JLabel("cena:");
		content.add(lblCena, "4, 24");
		
		JLabel lblOd_4 = new JLabel("od");
		content.add(lblOd_4, "6, 24");
		
		JSpinner cenaOd = new JSpinner();
		cenaOd.setName("cenaOd");
		cenaOd.setModel(new SpinnerNumberModel(0, 0, null, 1));
		cenaOd.setEditor(new JSpinner.NumberEditor(cenaOd, "#"));
		content.add(cenaOd, "8, 24");
		spinners.add(cenaOd);
		
		JLabel lblDo_4 = new JLabel("do");
		content.add(lblDo_4, "10, 24");
		
		JSpinner cenaDo = new JSpinner();
		cenaDo.setName("cenaDo");
		cenaDo.setModel(new SpinnerNumberModel(9999999, 0, 9999999, 1));
		cenaDo.setEditor(new JSpinner.NumberEditor(cenaDo, "#"));
		content.add(cenaDo, "12, 24");
		spinners.add(cenaDo);
		
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
				Vector<String> vals = getValues();
				Vector<String> opts = getSelectedOptions();
				System.out.println();
				CarSearchResult results = new CarSearchResult(manager.getVersions(vals, opts));
				results.setVisible(true);
				results.toFront();
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
		pack();
		toFront();
	}
	
	private Vector<String> getValues() {
		Vector<String> res = new Vector<>();
		for(JComboBox<String> box : boxes) {
			String val = (String) box.getSelectedItem();
			if(!val.equals("dowolny"))
				res.add(box.getName() + "='" + val + "'");
		}
		for(JSpinner s : spinners) {
			String name = s.getName();
			if(name.endsWith("Od")) {
				res.add(name.substring(0, name.length()-2) + ">=" + s.getValue());
			} else {
				res.add(name.substring(0, name.length()-2) + "<=" + s.getValue());
			}
		}
		return res;
	}
	
	private Vector<String> getSelectedOptions() {
		Vector<String> res = new Vector<>();
		for(Component c : optionList.getComponents()) {
			OptionDisplay d = (OptionDisplay) c;
			if(d.getCheckBox().isSelected())
				res.add(d.getOptionName());
		}
		return res;
	}
}
