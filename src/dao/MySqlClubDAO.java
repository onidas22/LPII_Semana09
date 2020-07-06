package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Club;
import util.MySqlDBConexion;

public class MySqlClubDAO implements ClubDAO {

	private static final Log log = LogFactory.getLog(MySqlClubDAO.class);

	@Override
	public int insertaClub(Club obj) {
		int salida = -1;
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = MySqlDBConexion.getConexion();
			String sql = "insert into club values(null,?,?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, obj.getNombre());
			pstm.setDate(2, obj.getFechaCreacion());
			pstm.setString(3, obj.getPais());
			pstm.setInt(4, obj.getAuspiciador().getIdAuspiciador());
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
