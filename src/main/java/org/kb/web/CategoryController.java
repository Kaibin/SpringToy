package org.kb.web;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.kb.domain.Category;
import org.kb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "category")
public class CategoryController {
	private Logger logger = Logger.getLogger(CategoryController.class);
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("action", "create");
		return "categoryForm";
	}
	
	@RequestMapping(value = "create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("category") Category category, RedirectAttributes redirectAttributes) {
		categoryService.saveCategory(category);
		logger.info("create category " + category.getName() + "successfully!");
		redirectAttributes.addFlashAttribute("message", "创建成功");
		return "redirect:/article/";
	}

}
