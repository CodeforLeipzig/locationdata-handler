package de.codefor.le.locations.locator;

import java.io.Serial;
import java.io.Serializable;

public class Point implements Serializable {
	@Serial
	private static final long serialVersionUID = 325271956854893239L;
	private Double x;
	private Double y;

	public Point() {
		// default
	}
	
	public Point(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}
}
