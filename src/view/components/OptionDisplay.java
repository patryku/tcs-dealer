package view.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class OptionDisplay extends JPanel {

	private static final long serialVersionUID = -5574435430820741090L;
	private JCheckBox optionCheckBox;

	/**
	 * Create the panel.
	 */
	public OptionDisplay(final String name, final double price, final JLabel lblPrice, final JList<String> optionList) {
		setLayout(new BorderLayout(0, 0));
		setMaximumSize(new Dimension(2000, 30));
		
		JLabel optionName = new JLabel(name + "   " + price + " zł");
		add(optionName, BorderLayout.CENTER);
		
		optionCheckBox = new JCheckBox("");
		optionCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(optionCheckBox.isSelected()) {
					double p = Double.parseDouble(lblPrice.getText().replaceFirst(" zł", ""));
					lblPrice.setText((p + price) + " zł");
					DefaultListModel<String> model = (DefaultListModel<String>) optionList.getModel();
					model.addElement(name);
				} else {
					double p = Double.parseDouble(lblPrice.getText().replaceFirst(" zł", ""));
					lblPrice.setText((p - price) + " zł");
					DefaultListModel<String> model = (DefaultListModel<String>) optionList.getModel();
					model.removeElement(name);
				}
			}
		});
		add(optionCheckBox, BorderLayout.EAST);

	}
	
	public JCheckBox getCheckBox() {
		return optionCheckBox;
	}

}
