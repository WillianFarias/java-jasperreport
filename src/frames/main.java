package frames;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import classes.Conection;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class main extends JFrame {

	private JPanel contentPane;
	private JTextField txtId;
	private JTextField txtNome;
	private JTextField txtSobrenome;
	private JTextField txtAdress;
	private JTextField txtFone;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					main frame = new main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public main() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 832, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JPanel panel = new JPanel();

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Registro de Empregados", TitledBorder.CENTER, TitledBorder.ABOVE_TOP,
				null, null));

		JPanel panel_2 = new JPanel();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_contentPane.createSequentialGroup().addGap(12)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 368, GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(2).addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 239, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 319, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup().addGap(12)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)));
		gl_panel_2.setVerticalGroup(
				gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2.createSequentialGroup().addGap(13)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Sobrenome", "Endere\u00E7o", "Fone" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		scrollPane.setViewportView(table);
		panel_2.setLayout(gl_panel_2);

		JLabel lblNewLabel_1 = new JLabel("ID");

		JLabel lblNewLabel_1_1 = new JLabel("NOME");

		JLabel lblNewLabel_1_1_1 = new JLabel("SOBRENOME");

		JLabel lblNewLabel_1_1_1_1 = new JLabel("ENDEREÇO");

		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("TELEFONE");

		txtId = new JTextField();
		txtId.setColumns(10);

		txtNome = new JTextField();
		txtNome.setColumns(10);

		txtSobrenome = new JTextField();
		txtSobrenome.setColumns(10);

		txtAdress = new JTextField();
		txtAdress.setColumns(10);

		txtFone = new JTextField();
		txtFone.setColumns(10);

		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					PreparedStatement ps = cn
							.prepareStatement("INSERT INTO empregado (name, surname, andress, fone) VALUES (?,?,?,?)");

					ps.setString(1, txtNome.getText());
					ps.setString(2, txtSobrenome.getText());
					ps.setString(3, txtAdress.getText());
					ps.setString(4, txtFone.getText());

					ps.executeUpdate();

					limpar();
					mostrarTabela("");

				} catch (SQLException e) {
					System.err.println("Erro ao salvar... "+e);
					JOptionPane.showMessageDialog(null, "Erro ao salvar");
				}
			}
		});

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					PreparedStatement ps = cn.prepareStatement("UPDATE empregado"
							+ " SET name='" + txtNome.getText() + "',surname='"+txtSobrenome.getText() + ",andress='" + 
							txtAdress.getText() + ",fone='" + txtFone.getText() + "WHERE id='" + txtId.getText()+"'");
					
					int resp = ps.executeUpdate();
					
					if (resp > 0) {
						JOptionPane.showMessageDialog(null, "Dados atualizados");
						limpar();
						mostrarTabela("");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao atualizar!");
					}
				} catch (SQLException e) {
					System.err.println("Erro ao atualizar");
					JOptionPane.showMessageDialog(null, "Erro ao atualizar");
				}
			}
		});

		JButton btnSalvar_1_1 = new JButton("Cancelar");

		JButton btnPrint = new JButton("Imprimir Relatório");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addContainerGap().addGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 88,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtSobrenome, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 88,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtAdress, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.PREFERRED_SIZE, 88,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(txtFone, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 88,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addComponent(txtId, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
						.addGroup(gl_panel_1.createSequentialGroup().addComponent(btnSalvar)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnAtualizar, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnSalvar_1_1, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
						.addComponent(btnPrint, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE).addComponent(txtId,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 15, Short.MAX_VALUE)
						.addComponent(txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(txtSobrenome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
						.addComponent(txtAdress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1_1_1_1_1, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
						.addComponent(txtFone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addGap(25).addComponent(btnPrint).addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(btnSalvar)
						.addComponent(btnAtualizar).addComponent(btnSalvar_1_1))
				.addContainerGap()));
		panel_1.setLayout(gl_panel_1);
		contentPane.setLayout(gl_contentPane);
		
//		this.setLocationRelativeTo(null);
//		limpar();
		mostrarTabela("");
		txtId.setEnabled(false);
	}

	private void mostrarTabela(String valor) {
		DefaultTableModel modelo = new DefaultTableModel();

		modelo.addColumn("ID");
		modelo.addColumn("NOME");
		modelo.addColumn("SOBRENOME");
		modelo.addColumn("ENDEREÇO");
		modelo.addColumn("TELEFONE");

		table.setModel(modelo);

		String sql = "SELECT * FROM empregado WHERE CONCAT (name,' ',surname) LIKE '%" + valor + "%'";

		String dados[] = new String[5];

		Statement st;

		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				dados[0] = rs.getString(1);
				dados[1] = rs.getString(2);
				dados[2] = rs.getString(3);
				dados[3] = rs.getString(4);
				dados[4] = rs.getString(5);
			}

			table.setModel(modelo);
		} catch (SQLException e) {
			System.err.println("Erro nos dados da tabela" + e);
			JOptionPane.showMessageDialog(null, "Erro nos dados da tabela");
		} catch (Exception e) {
			System.err.println(e);
		}

	}

	private void limpar() {
		txtNome.setText("");
		txtSobrenome.setText("");
		txtAdress.setText("");
		txtFone.setText("");
	}

	Conection con = new Conection();
	Connection cn = con.connection();
}
