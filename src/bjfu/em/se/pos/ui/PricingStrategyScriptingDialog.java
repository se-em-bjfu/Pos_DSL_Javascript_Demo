package bjfu.em.se.pos.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bjfu.em.se.pos.domain.salepricing.PricingStrategyFactory;

public class PricingStrategyScriptingDialog extends JDialog {
	private JTextField txtName;
	private JTextArea txtDescription;
	private JTextArea txtScript;
	
	public PricingStrategyScriptingDialog() {
		initUI();
		PricingStrategyFactory factory=PricingStrategyFactory.getInstance();
		txtName.setText(factory.getName());
		txtDescription.setText(factory.getDescription());
		txtScript.setText(factory.getScript());
		
	}
	
	private void initUI() {
		setSize(550,400);
		setTitle("价格策略脚本编辑器");
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("策略名称");
		label.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label.setBounds(10, 10, 69, 26);
		getContentPane().add(label);
		
		txtName = new JTextField();
		txtName.setBounds(90, 10, 434, 26);
		getContentPane().add(txtName);
		txtName.setColumns(10);
		
		JLabel label_1 = new JLabel("详细说明");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_1.setBounds(10, 46, 69, 26);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("策略脚本");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 15));
		label_2.setBounds(10, 139, 69, 26);
		getContentPane().add(label_2);
		
		txtDescription = new JTextArea();
		txtDescription.setBounds(90, 49, 434, 88);
		getContentPane().add(txtDescription);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(90, 142, 434, 179);
		getContentPane().add(scrollPane);
		
		txtScript = new JTextArea();
		scrollPane.setViewportView(txtScript);
		
		JButton btnSave = new JButton("保存");
		btnSave.addActionListener(this::saveStrategy);
		btnSave.setBounds(321, 326, 93, 23);
		getContentPane().add(btnSave);
		
		JButton btnExit = new JButton("退出");
		btnExit.addActionListener(e->dispose());
		btnExit.setBounds(424, 326, 93, 23);
		getContentPane().add(btnExit);
	}

	protected void saveStrategy(ActionEvent e) {
		PricingStrategyFactory factory=PricingStrategyFactory.getInstance();
		factory.setName(txtName.getText());
		factory.setDescription(txtDescription.getText());
		factory.setScript(txtScript.getText());		
	}
}
