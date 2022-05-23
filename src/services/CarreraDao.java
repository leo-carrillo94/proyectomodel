package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entitis.Carrera;
import util.Conexion;

public class CarreraDao {
	private Conexion conexion;
	private static final String INSERT_CARRERA_SQL = "INSERT INTO carrera (codigo, nombre, creditos, semestre) VALUES (?,?,?,?);";
	private static final String DELETE_CARRERA_SQL = "DELETE FROM carrera WHERE codigo = ?;";
	private static final String UPDATE_CARRERA_SQL = "UPDATE carrera SET nombre=?, creditos=?, semestre=? WHERE codigo=?;";
	private static final String SELECT_CARRERA_BY_CODIGO = "SELECT * FROM carrera WHERE codigo=?;";
	private static final String SELECT_ALL_CARRERAS = "SELECT * FROM carrera;";
	
	
	public CarreraDao() {
		this.conexion = Conexion.getConexion();
	
	}
	
	public void insert(Carrera carrera) throws SQLException {
		try {
			PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(INSERT_CARRERA_SQL);
			preparedStatement.setString(1, carrera.getCodigo());
			preparedStatement.setString(2, carrera.getNombre());
			preparedStatement.setInt(3, carrera.getCreditos());
			preparedStatement.setInt(4, carrera.getSemestre());
			conexion.execute();
		}catch(SQLException e) {
			
		}
		
	}
	
	public void delete(String codigo) throws SQLException {
		try {
		PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(DELETE_CARRERA_SQL);
		preparedStatement.setString(1, codigo);
		conexion.execute();
		}catch(SQLException e){
			
		}
	}
	
	public void update(Carrera carrera) throws SQLException{
		try {
			PreparedStatement preparedStatement =(PreparedStatement)conexion.setPreparedStatement(UPDATE_CARRERA_SQL);
			preparedStatement.setString(1, carrera.getNombre());
			preparedStatement.setInt(2, carrera.getCreditos());
			preparedStatement.setInt(3, carrera.getSemestre());
			preparedStatement.setString(4, carrera.getCodigo());
			conexion.execute();
		}catch(SQLException e) {
			
		}
		
	}
	
	public List<Carrera> selectAll(){
		List <Carrera> carreras = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_ALL_CARRERAS);
			ResultSet rs = conexion.query();
			
			while(rs.next()){
				String codigo = rs.getString("codigo");
				String nombre = rs.getString("nombre");
				int creditos = rs.getInt("creditos");
				int semestre = rs.getInt("semestre");
				carreras.add(new Carrera(codigo, nombre, creditos, semestre));
			}
		}catch(SQLException e) {
			
		}
		return carreras;		
	}
	
	public Carrera select(String codigo){
		Carrera carrera = null;
		try {
			PreparedStatement preparedStatement = (PreparedStatement)conexion.setPreparedStatement(SELECT_CARRERA_BY_CODIGO);
			preparedStatement.setString(1, codigo);
			ResultSet rs = conexion.query();
			while(rs.next()){
				
				
				String nombre = rs.getString("nombre");
				int creditos = rs.getInt("creditos");
				int semestre = rs.getInt("semestre");
				carrera = new Carrera(codigo, nombre, creditos, semestre);
				
			}
		}catch(SQLException e) {
		}	
		return carrera;
	}
}
