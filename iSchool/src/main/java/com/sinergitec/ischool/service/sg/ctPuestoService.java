package com.sinergitec.ischool.service.sg;

import java.util.List;

import com.sinergitec.ischool.model.sg.ctPuesto;

public interface ctPuestoService {
	public void add_ctPuesto(ctPuesto obj);

	public void update_ctPuesto(ctPuesto obj);

	public List<ctPuesto> list_ctPuesto();

	public ctPuesto get_ctPuesto(int id);

	public void remove_ctPuesto(int id);

}
