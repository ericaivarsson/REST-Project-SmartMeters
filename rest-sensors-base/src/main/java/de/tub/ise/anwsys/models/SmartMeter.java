package de.tub.ise.anwsys.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class SmartMeter implements Serializable {

	private static final long serialVersionUID = -6640481949420444264L;

	private Long id;
	private String name;
	private List<MeasuredData> measuredData;

	public SmartMeter() {
		super();
	}

	public SmartMeter(String name, List<MeasuredData> measuredData) {
		super();
		this.name = name;
		this.measuredData = measuredData;
	}

	@Id
	@Column(name = "smartMeter_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	@OneToMany(mappedBy = "smartMeter")
	public List<MeasuredData> getMeasuredData() {
		if (measuredData == null) {
			measuredData = new ArrayList<>();
		}
		return measuredData;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMeasuredData(List<MeasuredData> measuredData) {
		this.measuredData = measuredData;
	}

}
