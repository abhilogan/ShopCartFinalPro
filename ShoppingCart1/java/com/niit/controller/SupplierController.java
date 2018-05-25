package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

@Controller
public class SupplierController {

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private Supplier supplier;

	@Autowired
	private HttpSession httpSession;

	@RequestMapping("/supplier/get/{id}")
	public ModelAndView getSupplier(@RequestParam(value = "id", required = false) String id) {
		supplier = supplierDAO.get(id);
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("supplier", supplier);
		return mv;

	}

	@PostMapping("/supplier/save/")
	public ModelAndView saveSupplier(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("address") String address) 
	{
        System.out.println("saveSupplier method is calling");
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
		supplier.setId(id);
		supplier.setName(name);
		supplier.setAddress(address);
		if (supplierDAO.save(supplier)) 
		{
			mv.addObject("supplierSuccessMessage", "The supplier created successfully");
		} else 
		{
			mv.addObject("supplierErrorMessage", "Coulc not able to create supplier.  please contact admin");
		}
		return mv;
	}

	@PutMapping("/supplier/update/")
	public ModelAndView updateSupplier(@ModelAttribute Supplier supplier) {
		ModelAndView mv = new ModelAndView("home");
		if (supplierDAO.upadte(supplier) == true) {
			mv.addObject("successMEssage", "This supplier updated successfully");
		} else {
			mv.addObject("errorMessage", "Could not update the supplier");
		}
		return mv;
	}

	@GetMapping("/supplier/delete")
	public ModelAndView deleteSupplier(@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
		if (supplierDAO.delete(id) == true) {
			mv.addObject("supplierSuccessMessage", "This supplier deleted successfully");
		} else {
			mv.addObject("supplierErrorMessage", "Could not delete the supplier");
		}
		return mv;
	}

	@GetMapping("/supplier/edit")
	public ModelAndView editSupplier(@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView("redirect:/managesuppliers");
		supplier = supplierDAO.get(id);
		httpSession.setAttribute("selectedSupplier", supplier);
		return mv;
	}

	@GetMapping("/suppliers")
	public ModelAndView getAllSuppliers() {
		ModelAndView mv = new ModelAndView("home");
		List<Supplier> suppliers = supplierDAO.list();
		mv.addObject("suppliers", suppliers);
		return mv;

	}

}
