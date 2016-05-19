package com.codurance.test.quakker.domain;

import java.util.Arrays;

public class Timeline {
	private final Quakk[] quakks;

	public Timeline (final Quakk... quakks) {
		this.quakks = quakks;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer("Timeline{");
		sb.append("quakks=").append(quakks == null ? "null" : Arrays.asList(quakks).toString());
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals (final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Timeline timeline = (Timeline) o;

		boolean isEqual = true;
		for (int i = 0; i < quakks.length; i++) {
			isEqual = isEqual && quakks[i].equals(timeline.quakks[i]);
		}
		return isEqual;

	}

	@Override
	public int hashCode () {
		return Arrays.hashCode(quakks);
	}

	public Timeline merge (final Timeline another) {
		return new Timeline(another.quakks[0], this.quakks[0]);
	}
}
