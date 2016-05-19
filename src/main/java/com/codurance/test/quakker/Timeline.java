package com.codurance.test.quakker;

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

		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		return Arrays.equals(quakks, timeline.quakks);

	}

	@Override
	public int hashCode () {
		return Arrays.hashCode(quakks);
	}
}
