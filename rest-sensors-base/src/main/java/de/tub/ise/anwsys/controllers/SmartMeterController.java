package de.tub.ise.anwsys.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.tub.ise.anwsys.models.SmartMeter;
import de.tub.ise.anwsys.repos.SmartMeterRepository;

@RestController
@RequestMapping("/smartMeter")
public class SmartMeterController {

	@Autowired
	public SmartMeterRepository repo;

	// @RequestMapping(method = RequestMethod.POST)
	// public SmartMeter createNewSmartMeter(@RequestParam("name") String name)
	// {
	// SmartMeter sm = new SmartMeter(name, new ArrayList<>());
	// return repo.save(sm);
	// }

	@RequestMapping(method = RequestMethod.POST)
	public SmartMeter createNewSmartMeter(@RequestBody SmartMeter input) {
		SmartMeter sm = new SmartMeter(input.getName(), new ArrayList<>());
		return repo.save(sm);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<SmartMeter> getAllSmartMeters() {
		return repo.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<SmartMeter> getSmartMeter(@PathVariable Long id) {
		SmartMeter sm = repo.getOne(id);

		if (sm == null) {
			return new ResponseEntity<SmartMeter>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<SmartMeter>(sm, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteSmartMeter(@PathVariable Long id) {

		repo.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
