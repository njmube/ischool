package com.sinergitec.ischool.dao.ct.imp;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.springframework.stereotype.Repository;

import com.progress.AppServer.AppServer;
import com.progress.open4gl.BooleanHolder;
import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.ResultSetHolder;
import com.progress.open4gl.StringHolder;
import com.progress.open4gl.javaproxy.Connection;
import com.sinergitec.ischool.dao.ct.ctGrupoDao;
import com.sinergitec.ischool.model.ct.ctGrupo;
import com.sinergitec.ischool.util.DBConexion;
import com.sinergitec.ischool.util.VectorResultSet;

@Repository
public class ctGrupoDaoImpl implements ctGrupoDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void add_ctGrupo(ctGrupo obj_Grupo) throws Open4GLException, IOException {

		List<ctGrupo> Lista = new ArrayList<ctGrupo>();

		Lista.add(obj_Grupo);

		Vector vecTabla1, vecRow1;
		vecTabla1 = new Vector();

		BooleanHolder oplResultado = new BooleanHolder();
		StringHolder opcTexto = new StringHolder();

		Connection conexion = DBConexion.getConnection();
		AppServer app = new AppServer(conexion);

		System.out.println("antes del for");
		for (ctGrupo obj : Lista) {
			vecRow1 = obj.getVectorDatos();
			vecTabla1.add(vecRow1);
		}

		ResultSetHolder ttGrupos = new ResultSetHolder(new VectorResultSet(vecTabla1));
		try {

			app.as_ctGrupo_Inserta("SISIMB", ttGrupos, oplResultado, opcTexto);

			System.out.println(opcTexto.getValue());

		} catch (Exception ex) {
			System.err.println(ex);

		} finally {
			app._release();
			DBConexion.closeConnection(conexion);

		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void update_ctGrupo(ctGrupo obj_Grupo) throws Open4GLException, IOException {

		List<ctGrupo> Lista = new ArrayList<ctGrupo>();
		Lista.add(obj_Grupo);

		BooleanHolder oplResultado = new BooleanHolder();
		StringHolder opcTexto = new StringHolder();
		Connection conexion = DBConexion.getConnection();
		AppServer app = new AppServer(conexion);

		Vector vecTabla1, vecRow1;
		vecTabla1 = new Vector();

		for (ctGrupo e : Lista) {
			vecRow1 = e.getVectorDatos();
			vecTabla1.add(vecRow1);
		}

		ResultSet gruposModificados = new VectorResultSet(vecTabla1);

		try {
			app.as_ctGrupo_Actualiza("SISIMB", gruposModificados, oplResultado, opcTexto);
			System.out.print(opcTexto.getValue() + " Y el Resultado " + oplResultado.getValue());

		} finally {
			// TODO: handle finally clause
			app._release();
			DBConexion.closeConnection(conexion);
		}
	}

	public void remove_ctGrupo(int id) throws Open4GLException, IOException {

		BooleanHolder oplResultado = new BooleanHolder();
		StringHolder opcTexto = new StringHolder();

		Connection conexion = DBConexion.getConnection();
		AppServer app = new AppServer(conexion);

		try {
			System.out.println("Entro al metodo eliminar");
			app.as_ctGrupo_Borra("SISIMB", id, oplResultado, opcTexto);
			System.err.println(opcTexto.getValue());
		} finally {
			app._release();
			DBConexion.closeConnection(conexion);
		}
	}

	
}