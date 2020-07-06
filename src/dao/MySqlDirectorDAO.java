package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Director;
import util.MySqlDBConexion;

public class MySqlDirectorDAO implements DirectorDAO {

	private static final Log log = LogFactory.getLog(MySqlDirectorDAO.class);

	@Override
	public int insertaDirector(Director obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into director values(null,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaNacimiento());
			pstm.setInt(3, obj.getGrado().getIdGrado());
			salida = pstm.executeUpdate();
			log.info(pstm);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null)
					pstm.close();
				if (conn != null)
					conn.close();
			} catch (Exception e2) {
			}
		}

		return salida;
	}

}
