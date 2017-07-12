package de.tub.ise.anwsys.clients;

import java.util.HashMap;
import java.util.Map;

public class MeasuredData {

	private Integer timestamp;
	private Map<String, Double> measuredValueById;
	private String metricName;

	public MeasuredData() {
		super();
	}

	public MeasuredData(Integer timestamp, Map<String, Double> measuredValueById) {
		super();
		this.timestamp = timestamp;
		this.measuredValueById = measuredValueById;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public Map<String, Double> getMeasuredValueById() {
		if (measuredValueById == null) {
			measuredValueById = new HashMap<>();
		}
		return measuredValueById;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasuredData other = (MeasuredData) obj;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

	public String getMetricName() {
		return metricName;
	}

	public void setMetricName(String metricName) {
		this.metricName = metricName;
	}

}
