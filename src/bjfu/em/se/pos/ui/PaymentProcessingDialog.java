package bjfu.em.se.pos.ui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import bjfu.em.se.pos.domain.Register;
import bjfu.em.se.pos.domain.accounting.AccountingException;
import bjfu.em.se.pos.domain.payment.PaymentType;

public class PaymentProcessingDialog extends JDialog {
	private JTextField txtAmountTendered;
	private JLabel lblTotalPrice;
	private Register register;
	private JComboBox<PaymentType> cbPaymentType;
	private JLabel lblDiscountPrice;
	private JLabel lblDiscountInfo;
	public PaymentProcessingDialog(Register register) {
		this.register=register;
		initUI();
		lblTotalPrice.setText(
			String.format("%.2f元", (double)(
				register.getSale().getPreDiscountTotal())/100));
		lblDiscountPrice.setText(
			String.format("%.2f元", (double)(
				register.getSale().getDiscountedTotal())/100));
		lblDiscountInfo.setText(
			register.getSale().getPricingStrategy().getName());
					
		
	}
	private void initUI() {
		setSize(400,283);
		setLocation(100,100);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setTitle("支付处理");
		getContentPane().setLayout(null);
		
		lblTotalPrice = new JLabel("元");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTotalPrice.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblTotalPrice.setBounds(117, 13, 237, 28);
		getContentPane().add(lblTotalPrice);
		
		JLabel label = new JLabel("总计金额");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label.setBounds(14, 13, 89, 28);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("付款金额");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_1.setBounds(14, 130, 89, 28);
		getContentPane().add(label_1);
		
		txtAmountTendered = new JTextField();
		txtAmountTendered.setBounds(113, 127, 208, 31);
		getContentPane().add(txtAmountTendered);
		txtAmountTendered.setColumns(10);
		
		JLabel label_2 = new JLabel("支付类型");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_2.setBounds(14, 169, 89, 28);
		getContentPane().add(label_2);
		
		cbPaymentType = new JComboBox<PaymentType>();
		cbPaymentType.setModel(
			new DefaultComboBoxModel<PaymentType>(
				PaymentType.values()));
		cbPaymentType.setBounds(117, 171, 204, 24);
		getContentPane().add(cbPaymentType);
		
		JButton btnNewButton = new JButton("付款");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				processPayment();
			}

		});
		btnNewButton.setBounds(160, 210, 113, 27);
		getContentPane().add(btnNewButton);
		
		JLabel label_3 = new JLabel("折后金额");
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_3.setBounds(14, 89, 89, 28);
		getContentPane().add(label_3);
		
		lblDiscountPrice = new JLabel("0.00元");
		lblDiscountPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscountPrice.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblDiscountPrice.setBounds(117, 89, 237, 28);
		getContentPane().add(lblDiscountPrice);
		
		JLabel label_4 = new JLabel("折扣类型");
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		label_4.setBounds(14, 48, 89, 28);
		getContentPane().add(label_4);
		
		lblDiscountInfo = new JLabel("无折扣");
		lblDiscountInfo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDiscountInfo.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		lblDiscountInfo.setBounds(117, 48, 237, 28);
		getContentPane().add(lblDiscountInfo);
	}
	private void processPayment(){
		try {
			int amount=(int)(Double.parseDouble(txtAmountTendered.getText())*100);
			int balance=register.makePayment(amount, (PaymentType)(cbPaymentType.getModel().getSelectedItem()));
			JOptionPane.showMessageDialog(this,
				String.format("应收%s 实收%.2f元 找零%.2f元",
					lblDiscountPrice.getText(),
					(double)amount/100,
					(double)balance/100
			));
			dispatchEvent(new WindowEvent(
					this, WindowEvent.WINDOW_CLOSING
				));			
		} catch(NumberFormatException exp) {
			JOptionPane.showMessageDialog(this,"付款金额错误！");
		} catch (AccountingException e) {
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}
}
