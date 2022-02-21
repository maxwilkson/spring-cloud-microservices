package br.com.maxribeiro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Cambio implements Serializable {

	private static final long serialVersionUID = -3676387075809128789L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "from_currency", nullable = false, length = 3)
	private String from;

	@Column(name = "to_currency", nullable = false, length = 3)
	private String to;

	@Column(nullable = false)
	private BigDecimal conversionFactor;

	@Transient
	private BigDecimal convertedValue;

	@Transient
	private String environment;

	public Cambio() {
	}

	public Cambio(Long id, String from, String to, BigDecimal convertFactor, BigDecimal convertedValue,
			String environment) {
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

	public BigDecimal getConversionFactor() {
		return conversionFactor;
	}

	public void setConversionFactor(BigDecimal convertFactor) {
		this.conversionFactor = convertFactor;
	}

	public BigDecimal getConvertedValue() {
		return convertedValue;
	}

	public void setConvertedValue(BigDecimal convertedValue) {
		this.convertedValue = convertedValue;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public void calculateConvertedValue(BigDecimal amount) {
		convertedValue = conversionFactor.multiply(amount).setScale(2, RoundingMode.CEILING);
	}

}
