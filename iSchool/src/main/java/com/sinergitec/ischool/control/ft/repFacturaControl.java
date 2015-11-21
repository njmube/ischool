package com.sinergitec.ischool.control.ft;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.progress.open4gl.Open4GLException;
import com.progress.open4gl.RunTime4GLException;
import com.progress.open4gl.SystemErrorException;
import com.sinergitec.ischool.model.ct.ctAlumno;
import com.sinergitec.ischool.model.ct.ctGrupo;
import com.sinergitec.ischool.model.ft.ftDetFactura;
import com.sinergitec.ischool.model.ft.ftFacturaAlumno;
import com.sinergitec.ischool.service.ct.ctAlumnoService;
import com.sinergitec.ischool.service.ct.ctGrupoService;
import com.sinergitec.ischool.service.ft.repDetFacturaService;
import com.sinergitec.ischool.service.ft.repFacturaService;

@Controller
public class repFacturaControl {
	
	List<ftFacturaAlumno> list;
	
	@Autowired
	private repFacturaService repService;
	
	@Autowired
	private repDetFacturaService repDetService;
	
	@Autowired
	private ctGrupoService ctGrupoService;
	
	@Autowired
	private ctAlumnoService alumnoService;
	
	
	@RequestMapping(value = "/repFactura", method = RequestMethod.GET)
	public String grupo(Model model) throws Open4GLException, IOException {

		model.addAttribute("ctGrupo", new ctGrupo());
		model.addAttribute("lista_ctGrupo", this.ctGrupoService.list_ctGrupo());

		return "repFactura";
	}
	
	@RequestMapping(value = "/repFactura/getAlumnos", headers = "Accept=application/json")
	public @ResponseBody List<ftFacturaAlumno> list_repAlumnoGrupo(int iIdGrupo) {
		
		list = this.repService.list_ftFactura(iIdGrupo);		

		return list;
	}
	
	@RequestMapping(value = "/repFactura/detalle", headers = "Accept=application/json")
	public @ResponseBody List<ftDetFactura> list_ftDetFactura(int iFolio, int iSerie){		
		
		List<ftDetFactura> lista = new ArrayList<ftDetFactura>();
		lista = repDetService.list_ftDetFactura(iFolio, iSerie);
		return lista;		
	}
	
	@RequestMapping(value = "/repFactura/alumno", headers = "Accept=application/json")
	public @ResponseBody ctAlumno get_alumno(int iAlumno) throws RunTime4GLException, SystemErrorException, Open4GLException, IOException, SQLException{		
		
		ctAlumno obj = alumnoService.get_ctAlumno(iAlumno);
		System.out.println(obj.getcNombre());
		return obj;		
	}
	
	

}
