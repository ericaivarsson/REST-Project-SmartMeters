package de.tub.ise.anwsys.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class MeasuredData implements Serializable {

	private static final long serialVersionUID = -4579020145181847184L;

	private Long id;
	private DataType type;
	private int value;
	private Date measuredDate;
	private SmartMeter smartMeter;

	public MeasuredData() {
		// empty constructor required by JPA
		super();
	}

	public MeasuredData(DataType type, int value, Date measuredDate, SmartMeter smartMeter) {
		super();
		this.type = type;
		this.value = value;
		this.measuredDate = measuredDate;
		this.smartMeter = smartMeter;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@Column(name = "value")
	public int getValue() {
		return value;
	}

	@Column(name = "name")
	public Date getMeasuredDate() {
		return measuredDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setMeasuredDate(Date measuredDate) {
		this.measuredDate = measuredDate;
	}

	@ManyToOne
	@JoinColumn(name = "smartMeter_id", nullable = false)
	@JsonIgnoreProperties("measuredData") // to avoid stackoverflow error
	public SmartMeter getSmartMeter() {
		return smartMeter;
	}

	public void setSmartMeter(SmartMeter smartMeter) {
		this.smartMeter = smartMeter;
	}

	public DataType getType() {
		return type;
	}

	public void setType(DataType type) {
		this.type = type;
	}

}
