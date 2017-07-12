package de.tub.ise.anwsys.repos;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;

import de.tub.ise.anwsys.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findOne(Long id);

}
