package de.tub.ise.anwsys.clients;

public class Measurement {

	private String metricId;
	private String metricText;

	public Measurement() {
	}

	public Measurement(String metricId, String metricText) {
		super();
		this.metricId = metricId;
		this.metricText = metricText;
	}

	public String getMetricId() {
		return metricId;
	}

	public String getMetricText() {
		return metricText;
	}

	public void setMetricId(String metricId) {
		this.metricId = metricId;
	}

	public void setMetricText(String metricText) {
		this.metricText = metricText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((metricId == null) ? 0 : metricId.hashCode());
		result = prime * result + ((metricText == null) ? 0 : metricText.hashCode());
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
		Measurement other = (Measurement) obj;
		if (metricId == null) {
			if (other.metricId != null)
				return false;
		} else if (!metricId.equals(other.metricId))
			return false;
		if (metricText == null) {
			if (other.metricText != null)
				return false;
		} else if (!metricText.equals(other.metricText))
			return false;
		return true;
	}

}
