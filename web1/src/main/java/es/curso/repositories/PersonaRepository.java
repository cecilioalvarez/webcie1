package es.curso.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.curso.negocio.Persona;

public class PersonaRepository {

	static final String DB_URL = "jdbc:mysql://localhost:8889/cie1";
	static final String USER = "root";
	static final String PASS = "root";
	static final String QUERY = "SELECT * from Personas";

	public void insertar(Persona persona) {

		try {
			 Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement sentencia = conexion.createStatement();

			sentencia.executeUpdate("insert into personas values ('" + persona.getNombre() + "','"
					+ persona.getApellidos() + "'," + persona.getEdad() + ")");

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void borrar(Persona persona) {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement sentencia = conexion.createStatement();

			String consultaBorrar = "delete from Personas where nombre='" + persona.getNombre() + "'";

			System.out.println(consultaBorrar);

			sentencia.executeUpdate(consultaBorrar);

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<Persona> buscarTodos() {

		ResultSet rs=null;
		Connection conexion=null;
		ArrayList<Persona> lista= new ArrayList<Persona>();
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(DB_URL, USER, PASS);
			//preparo la sentencia
			Statement sentencia = conexion.createStatement();
			//ejecuto
			rs = sentencia.executeQuery("select * from Personas");
			while (rs.next()) {
				
				Persona p= new Persona(rs.getString("nombre"),
						rs.getString("apellidos"), rs.getInt("edad"));
				
				lista.add(p);
			}
			
			
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			if (conexion!=null) {
				try {
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return lista;
	}
}
