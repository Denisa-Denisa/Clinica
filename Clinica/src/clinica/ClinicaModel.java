package clinica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClinicaModel {

	private BaseDatos bd;

	public ClinicaModel() throws SQLException {
		super();
		bd = new BaseDatos();
	}

	public Medico getById(int id) throws SQLException {
		bd.conectar();
		String sql = "SELECT * FROM Medico WHERE id=?";
		Object[] params = new Object[1];
		params[0] = id;
		ResultSet Medico = bd.query(sql, params);

		Medico medic = null;
		if (Medico.next()) {
			medic = new Medico(Medico.getInt("id"), Medico.getString("nombre"), Medico.getString("apellidos"),
					Medico.getString("especialidad"));
		}

		return medic;
	}

	public List<Medico> getMedicosByEspecialidad(String especialidad) throws SQLException {
		String sql = "SELECT * FROM medicos WHERE especialidad=?";
		Object[] params = new Object[1];
		params[0] = especialidad;
		ResultSet resultSet = bd.query(sql, params);

		List<Medico> medicos = new ArrayList<>();
		while (resultSet.next()) {
			Medico medico = new Medico(resultSet.getInt("id"), resultSet.getString("nombre"),
					resultSet.getString("apellidos"), resultSet.getString("especialidad"));
			medicos.add(medico);
		}
		return medicos;
	}

	public List<Paciente> getPacientesByMedico(int idMedico) throws SQLException {
		String sql = "SELECT * FROM pacientes WHERE idMedico=?";
		Object[] params = new Object[1];
		params[0] = idMedico;
		ResultSet resultSet = bd.query(sql, params);

		List<Paciente> pacientes = new ArrayList<>();
		while (resultSet.next()) {
			Paciente paciente = new Paciente(resultSet.getInt("id"), resultSet.getString("nombre"),
					resultSet.getString("apellidos"), resultSet.getDate("fechaAlta"));
			pacientes.add(paciente);
		}
		return pacientes;
	}

	public int darDeAltaMedico(Medico medico) throws SQLException {
		String sql = "INSERT INTO medicos (nombre, apellidos, especialidad) VALUES (?, ?, ?)";
		Object[] params = new Object[3];
		params[0] = medico.getNombre();
		params[1] = medico.getApellidos();
		params[2] = medico.getEspecialidad();
		int n = bd.execute(sql, params);
		return n;
	}

	public int eliminarMedicoPorId(int id) throws SQLException {
		String sql = "DELETE FROM medicos WHERE id=?";
		Object[] params = new Object[1];
		int n = bd.execute(sql, params);
		return n;
	}

}