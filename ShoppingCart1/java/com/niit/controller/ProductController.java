package com.niit.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Product;
import com.niit.util.FileUtil;

@Controller
public class ProductController {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SupplierDAO supplierDAO;
	
	@Autowired
	private Product product;

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	FileUtil fileUtil;
	
	Logger log = LoggerFactory.getLogger(ProductController.class);
	
	private static final String imageDirectory = "C:\\Users\\lenovo\\eclipse-workspace\\LoginApp2\\ShoppingCart1\\src\\main\\webapp\\resources\\images";
	private static String rootPath = System.getProperty("catalina.home");

	
	
	/*@GetMapping("/product/get")
	public ModelAndView getProduct(@RequestParam String id)
	{

		product = productDAO.get(id);
		
		ModelAndView mv = new ModelAndView("home");
		mv.addObject("selectedproduct", product);
		mv.addObject("isUserSelectedProduct",true);
		mv.addObject("selectedProductImage", 
				rootPath + File.separator + imageDirectory + File.separator + id + ".PNG");
		
		return mv;
		
	}*/
	
	@GetMapping("/product/get/{id}")
	public ModelAndView getSelectedProduct(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		
		ModelAndView mv = new ModelAndView("redirect:/");
		
/*product = productDAO.get(id);
		
		//ModelAndView mv = new ModelAndView("home");
		mv.addObject("selectedproduct", product);
		mv.addObject("isUserSelectedProduct",true);
		mv.addObject("selectedProductImage", 
				rootPath + File.separator + imageDirectory + File.separator + id + ".PNG");*/
		
		
		redirectAttributes.addFlashAttribute("selectedProduct",  productDAO.get(id));
		redirectAttributes.addFlashAttribute("isUserSelectedProduct",  true);
		redirectAttributes.addFlashAttribute("productID", imageDirectory +File.separator +id + ".PNG");
		redirectAttributes.addFlashAttribute("selectedProductId", 
				imageDirectory + File.separator + id + ".PNG");
		return mv;

	}

	@PostMapping("/product/save/")
	public ModelAndView saveProduct(@RequestParam("id") String id, 
			@RequestParam("name") String name,
			@RequestParam("description") String description, 
			@RequestParam("price") String price,
			@RequestParam("categoryID") String categoryID,
			@RequestParam("supplierID") String supplierID,
			@RequestParam("file") MultipartFile file
			
			) {
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		product.setId(id);
		product.setName(name);
		product.setDescription(description);
		price = price.replace(",", "");
		product.setPrice(Integer.parseInt(price));
		/*product.setCategory(categoryDAO.get(categoryID));
		product.setSupplier(supplierDAO.get(supplierID));*/
		product.setSupplierId(supplierID);
		product.setCategoryId(categoryID);
		if(productDAO.save(product))
		{
			mv.addObject("productSuccessMessage","This product saved successfully");
			// call upload image method
			if(fileUtil.fileCopyNIO(file, id + ".PNG"))
			{
				mv.addObject("uploadMessage","product image successfully updated");
			}
			else
			{
				mv.addObject("uploadMessage","Couldnot upload image");
			}
			
		}
		else
		{
			mv.addObject("productErrorMessage","Could able to create product.Please contact admin");
			List<Product> products = productDAO.list();
			for(Product prod:products) {
				System.out.println("supplier name="+prod.getSupplier().getName());
			}
			httpSession.setAttribute("products", products);
		}
		return mv;

	}
	
	@PutMapping("/product/update")
	public ModelAndView updateProduct(@ModelAttribute Product product)
	{
		ModelAndView mv = new ModelAndView("home");
		if(productDAO.upadte(product)==true)
		{
			mv.addObject("successMessage","This product updated successfully");
		}
		else
		{
			mv.addObject("errorMessage","Could not update the product");
		}
		return mv;
	}
	
	
	@GetMapping("/product/delete")
	public ModelAndView deleteProduct(@RequestParam("id") String id)
	{
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		if(productDAO.delete(id)==true)
		{
			mv.addObject("productSuccessMessage", "This product deleted successfully");
		}
		else
		{
			mv.addObject("productErrorMessage", "Could not delete the product");
		}
		return mv;
	}
	
	@GetMapping("/product/edit")
	public ModelAndView editProduct(@RequestParam("id") String id)
	{
		ModelAndView mv = new ModelAndView("redirect:/manageproducts");
		product = productDAO.get(id);
		httpSession.setAttribute("selectedProduct", product);
		return mv;
	}
	
	@GetMapping("/products")
	public ModelAndView getAllProducts()
	{
		ModelAndView mv  = new ModelAndView("home");
		product=new Product();
		List<Product> products = productDAO.list();
		httpSession.setAttribute("selectedProduct", product);
		mv.addObject("products",products);
		return mv;
		
	}
	
	/*@GetMapping("search")
	public ModelAndView searchProduct(@RequestParam("searchString") String searchString)
	{
		ModelAndView mv  = new ModelAndView("home");	
		List<Product>	products = productDAO.search(searchString);
		mv.addObject("products",products);
		mv.addObject("isUserSelectedProduct", true);
		log.info("Number of products with search string " +searchString +  " is/are : " + products.size());
		return mv;
		
		
	}
	*/
	
	@GetMapping("search")
	public ModelAndView searchProduct(@RequestParam("searchString") String searchString)
	{
		ModelAndView mv = new ModelAndView("home");
		List<Product> products =  productDAO.search(searchString);
		mv.addObject("products", products);
		mv.addObject("isUserSelectedProduct", true);
		log.info("Number of products with search string " +searchString +  " is/are : " + products.size());
		return mv;
		
	}
	
	
	
	
	
}
