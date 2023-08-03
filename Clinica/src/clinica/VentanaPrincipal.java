package clinica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame {

	private ClinicaModel clinicaModel;
	private JTextField txtIdMedico;
	private JButton btnBuscar;
	private JButton btnPacientes;
	private JButton btnEliminar;
	private JButton btnSalir;
	private JTextArea txtDatosMedico;

	public VentanaPrincipal(ClinicaModel clinicaModel) {
		this.clinicaModel = clinicaModel;
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout());
		JLabel lblIdMedico = new JLabel("ID Médico:");
		txtIdMedico = new JTextField(10);
		btnBuscar = new JButton("Buscar");
		btnPacientes = new JButton("Pacientes");
		btnEliminar = new JButton("Eliminar");
		btnSalir = new JButton("Salir");
		txtDatosMedico = new JTextArea(10, 30);
		txtDatosMedico.setEditable(false);

		panelSuperior.add(lblIdMedico);
		panelSuperior.add(txtIdMedico);
		panelSuperior.add(btnBuscar);
		panelSuperior.add(btnPacientes);
		panelSuperior.add(btnEliminar);
		panelSuperior.add(btnSalir);

		add(panelSuperior, BorderLayout.NORTH);
		add(new JScrollPane(txtDatosMedico), BorderLayout.CENTER);

		btnBuscar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int idMedico = Integer.parseInt(txtIdMedico.getText());
				try {
					Medico medico = clinicaModel.getById(idMedico);
					if (medico != null) {
						txtDatosMedico.setText(
								"ID: " + medico.getId() + "\n" + "Nombre: " + medico.getNombre() + "\n" + "Apellidos: "
										+ medico.getApellidos() + "\n" + "Especialidad: " + medico.getEspecialidad());
						btnPacientes.setEnabled(true);
					} else {
						JOptionPane.showMessageDialog(null, "No se encontro el medico con el ID especificado", "Error",
								JOptionPane.ERROR_MESSAGE);
						btnPacientes.setEnabled(false);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al consultar el medico en la base de datos", "Error",
							JOptionPane.ERROR_MESSAGE);

				}
			}
		});

		btnPacientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int idMedico = Integer.parseInt(txtIdMedico.getText());
				try {
					List<Paciente> pacientes = clinicaModel.getPacientesByMedico(idMedico);
					if (!pacientes.isEmpty()) {
						VentanaPacientes ventanaPacientes = new VentanaPacientes(pacientes);
						ventanaPacientes.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "No hay pacientes asignados a este medico", "Informacion",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al obtener los pacientes del medico", "Error",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		btnEliminar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int idMedico = Integer.parseInt(txtIdMedico.getText());
				try {
					clinicaModel.eliminarMedicoPorId(idMedico);
					JOptionPane.showMessageDialog(null, "Medico eliminado correctamente", "Informacion",
							JOptionPane.INFORMATION_MESSAGE);
					txtDatosMedico.setText("");
					txtIdMedico.setText("");
					btnPacientes.setEnabled(false);
				} catch (SQLException ex) {
					JOptionPane.showMessageDialog(null, "Error al eliminar el medico", "Error",
							JOptionPane.ERROR_MESSAGE);
					ex.printStackTrace();
				}
			}
		});

		btnSalir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

}
