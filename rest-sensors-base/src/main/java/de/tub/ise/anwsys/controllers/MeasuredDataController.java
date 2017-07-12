package de.tub.ise.anwsys.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.tub.ise.anwsys.models.DataType;
import de.tub.ise.anwsys.models.MeasuredData;
import de.tub.ise.anwsys.models.SmartMeter;
import de.tub.ise.anwsys.repos.MeasuredDataRepository;
import de.tub.ise.anwsys.repos.SmartMeterRepository;

@RestController
@RequestMapping("/measuredData")
public class MeasuredDataController {

	@Autowired
	public MeasuredDataRepository repo;

	@Autowired
	public SmartMeterRepository smRepo;

	@RequestMapping(method = RequestMethod.POST)
	public MeasuredData createNewData(@RequestParam("type") DataType type, @RequestParam("value") int value,
			@RequestParam("smartMeter_id") Long smartMeter_id) {
		SmartMeter sm = null;
		if (smartMeter_id != null) {
			sm = smRepo.findOne(smartMeter_id);
		}
		MeasuredData md = new MeasuredData(type, value, new Date(System.currentTimeMillis()), sm);
		return repo.save(md);

	}

	@RequestMapping(method = RequestMethod.GET)
	public List<MeasuredData> getSMartMeterValue(@RequestParam("type") DataType type,
			@RequestParam("smartMeter_id") Long smartMeter_id) {
		List<MeasuredData> values = new ArrayList<>();
		SmartMeter sm = null;
		if (smartMeter_id != null) {
			sm = smRepo.findOne(smartMeter_id);
		}

		for (MeasuredData data : sm.getMeasuredData()) {
			if (data.getType().equals(type)) {
				values.add(data);
			}
		}
		return values;
	}

}
