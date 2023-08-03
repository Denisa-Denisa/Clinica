package clinica;

import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Ejercicio1 {

	public static void main(String[] args) {

		BaseDatos baseDatos = new BaseDatos();
		try {
			baseDatos.conectar();
			ClinicaModel clinicaModel = new ClinicaModel();
			VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(clinicaModel);
			ventanaPrincipal.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos", "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();

		}
	}
}