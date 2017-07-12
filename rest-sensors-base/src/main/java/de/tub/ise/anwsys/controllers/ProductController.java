package de.tub.ise.anwsys.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.tub.ise.anwsys.models.Product;
import de.tub.ise.anwsys.repos.ProductRepository;

@RestController
@RequestMapping("/product") // wishlist/{userid} also possible... !
public class ProductController {

	@Autowired
	public ProductRepository repo;

	@RequestMapping(method = RequestMethod.POST)
	public Product CreateNewProduct(@RequestBody Product input) {
		Product newProduct = new Product(input.getName(), input.getPrice());

		return repo.save(newProduct);

	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Product> getSingleProduct(@PathVariable Long id) {
		Product p = repo.getOne(id);

		if (p == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(p, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product input) {
		Product savedProduct = repo.getOne(id);

		if (savedProduct == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		savedProduct.setName(input.getName());
		savedProduct.setPrice(input.getPrice());
		savedProduct = repo.save(savedProduct);

		return new ResponseEntity<Product>(savedProduct, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

		repo.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
