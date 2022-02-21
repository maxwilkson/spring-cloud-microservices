package br.com.maxribeiro.response;

import java.io.Serializable;
import java.util.Objects;

public class Cambio implements Serializable {

	private static final long serialVersionUID = -3676387075809128789L;

	private Long id;

	private String from;

	private String to;

	private Double conversionFactor;

	private Double convertedValue;

	private String environment;

	public Cambio() {
	}

	public Cambio(Long id, String from, String to, Double convertFactor, Double convertedValue, String environment) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionFactor = convertFactor;
		this.convertedValue = convertedValue;
		this.environment = environment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(conversionFactor, convertedValue, environment, from, id, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cambio other = (Cambio) obj;
		return Objects.equals(conversionFactor, other.conversionFactor)
				&& Objects.equals(convertedValue, other.convertedValue)
				&& Objects.equals(environment, other.environment) && Objects.equals(from, other.from)
				&& Objects.equals(id, other.id) && Objects.equals(to, other.to);
	}

	@Override
	public String toString() {
		return "Cambio [id=" + id + ", from=" + from + ", to=" + to + ", conversionFactor=" + conversionFactor
				+ ", convertedValue=" + convertedValue + ", environment=" + environment + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public Double getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(Double convertFactor) {
		this.conversionFactor = convertFactor;
	}

	public Double getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(Double convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

}
