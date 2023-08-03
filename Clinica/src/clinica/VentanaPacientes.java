package clinica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaPacientes extends JFrame {

	private JTextArea txtAreaPacientes;

	public VentanaPacientes(List<Paciente> pacientes) {
		super("Lista de Pacientes");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		txtAreaPacientes = new JTextArea();
		txtAreaPacientes.setEditable(false);

		StringBuilder sb = new StringBuilder();
		for (Paciente paciente : pacientes) {
			sb.append("ID: ").append(paciente.getId()).append("\n");
			sb.append("Nombre: ").append(paciente.getNombre()).append("\n");
			sb.append("Apellidos: ").append(paciente.getApellidos()).append("\n");
			sb.append("Fecha de Alta: ").append(paciente.getFechaAlta()).append("\n");
			sb.append("\n");
		}
		txtAreaPacientes.setText(sb.toString());

		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		add(new JScrollPane(txtAreaPacientes), BorderLayout.CENTER);
		add(btnCerrar, BorderLayout.SOUTH);
	}
}
