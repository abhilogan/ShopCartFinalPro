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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Controller
public class CategoryController {

	// we need to call CategoryDAO methods
	// get , save , update , delete , list

	// 1.inject the CategoryDAO and Category
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private Category category;

	@Autowired
	private HttpSession httpSession;

	// @GetMapping("/category/get/{id}")
	@RequestMapping(name = "/category/get/{id}", method = RequestMethod.GET)
	public ModelAndView getCategory(@RequestParam(name="id",required=false) String id) {
		// based on id fetch the details from categoryDAO
		category = categoryDAO.get(id);

		// navigate to home page
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("category", category);

		return mv;

	}

	@PostMapping("/category/save/")
	/*
	 * public ModelAndView saveCategory(@RequestParam("id") String id ,
	 * 
	 * @RequestParam("id") String name ,
	 * 
	 * @RequestParam("id") String description)
	 */

	public ModelAndView saveCategory(@RequestParam("id") String id, 
									@RequestParam("name") String name,
									@RequestParam("description") String description) {
		/*
		 * // navigate to home page ModelAndView mv = new ModelAndView("home");
		 * 
		 * // call save methods of categoryDAO if (categoryDAO.save(category) == true) {
		 * // add success method mv.addObject("successMessage",
		 * "This category saved successfully"); } else { // add failure message
		 * mv.addObject("errorMessage", "Could not save the category"); } return mv;
		 */
		ModelAndView mv = new ModelAndView("redirect:/managecategories");
		// set the values to category
		category.setId(id);
		category.setName(name);
		category.setDescription(description);

		// save into db
		if (categoryDAO.save(category)) {
			mv.addObject("categorySuccessMessage", "This category saved successfully");
		} else {
			mv.addObject("categoryErrorMessage", "Could able to create category.Please contact admin");
			// fetch all the categories again
			List<Category> categories = categoryDAO.list();
			// and set to http session
			httpSession.setAttribute("categories", categories);

		}
		return mv;
	}

	@PutMapping("/category/update/")
	public ModelAndView updateCategory(@ModelAttribute("category") Category category) {
		// navigate to home page
		ModelAndView mv = new ModelAndView("home");

		// call save methods of categoryDAO
		if (categoryDAO.upadte(category) == true) {
			// add success method
			mv.addObject("successMessage", "This category updated successfully");
		} else {
			// add failure message
			mv.addObject("errorMessage", "Could not update the category");
		}
		return mv;

	}

	@GetMapping("/category/delete")
	public ModelAndView deleteCategory(@RequestParam("id") String id) {
		System.out.println("Going to delete Category : " + id);
		// navigate to home page
		ModelAndView mv = new ModelAndView("redirect:/managecategories");
		// we supposed to fetch the latest categories
		// and add to httpSession
		// based on id fetch the details from categoryDAO
		if (categoryDAO.delete(id) == true) {

			// add success method
			mv.addObject("successMessage", "This category deleted successfully");
		} else {

			// add failure method
			mv.addObject("errorMessage", "Could not delete the category");
		}

		return mv;

	}

	@GetMapping("/category/edit")
	public ModelAndView editCategory(@RequestParam String id) {
		ModelAndView mv = new ModelAndView("redirect:/managecategories");

		// based on category id fetch category details
		category = categoryDAO.get(id);
		//mv.addObject("selectedCategory", category);
		httpSession.setAttribute("selectedCategory", category);
		return mv;
	}

	@GetMapping("/categories")
	public ModelAndView getAllCategories() {
		ModelAndView mv = new ModelAndView("home");
		List<Category> categories = categoryDAO.list();
		mv.addObject("categories", categories);
		return mv;

	}

}
