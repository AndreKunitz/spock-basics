package me.github.andre.kunitz;

import java.util.TooManyListenersException;

class Polygon {
	private final int numberOfSides;

	public Polygon(int numberOfSides) {
		if (numberOfSides <= 2) {
			throw new TooFewSidesException("The shape must have more than 2 sides", numberOfSides);
		}
		this.numberOfSides = numberOfSides;
	}

	public int getNumberOfSides() {
		return numberOfSides;
	}
}
