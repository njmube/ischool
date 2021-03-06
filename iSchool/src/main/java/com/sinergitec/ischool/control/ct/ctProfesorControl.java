package com.sinergitec.ischool.control.ct;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.progress.open4gl.Open4GLException;
import com.sinergitec.ischool.model.ct.ctProfesor;
import com.sinergitec.ischool.service.ct.ctProfesorService;



@Controller
public class ctProfesorControl {

	@Autowired
	private ctProfesorService servProfesor;

	
	
	
	@RequestMapping(value = "/ctProfesor", method = RequestMethod.GET)
	public String list_ctProfesor(Model model) {
		
		
	
		model.addAttribute("ctProfesor", new ctProfesor());
		model.addAttribute("lista_ctProfesor", this.servProfesor.list_ctProfesor());
	

		return "ctProfesor_List";
	}

	@RequestMapping(value = "/ctProfesor/add", method = RequestMethod.POST)
	public String add_ctProfesor(@ModelAttribute("ctProfesor") ctProfesor obj,
			ModelMap model) {

		
		System.out.println(obj.toString());
		if(obj.getiIdProfesor() == 0){
			System.out.println("entro al 0");

			this.servProfesor.add_ctProfesor(obj);
            
        }else{
            //existing person, call update
           this.servProfesor.update_ctProfesor(obj);
        }
		return "redirect:/ctProfesor";

	}

	@RequestMapping("/remove_ctProfesor/{cProfesor}")
	public String remove_ctProfesor(@PathVariable("cProfesor") int id) {

		
		this.servProfesor.remove_ctProfesor(id);
		return "redirect:/ctProfesor";

	}

	@RequestMapping("/get_ctProfesor/{id}")
	public String get_ctProfesor(@PathVariable("id") int id,
			Model model) {
		
		model.addAttribute("ctProfesor", this.servProfesor.get_ctProfesor(id));
	
		return "ctProfesor_Form";

	}

	
	
	@RequestMapping(value = "/ctProfesor/update", method = RequestMethod.POST)
	public String edit_ctProfesor(@PathVariable("id") int id, Model moldel){
		
		
		
		moldel.addAttribute("ctProfesor",this.servProfesor.get_ctProfesor(id));
		return "redirect:/ctProfesor";
	}
	
	

}
