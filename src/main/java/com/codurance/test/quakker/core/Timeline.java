package com.codurance.test.quakker.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

		final Quakk[] mergedQuakks = sort(concatQuacksFrom(another));
		return new Timeline(mergedQuakks);
	}

	private Quakk[] sort (final List<Quakk> allQuacks) {
		return allQuacks.stream().sorted((o1, o2) -> o1.dateTime().compare(o2.dateTime())).collect(Collectors.toList()).toArray(new Quakk[0]);
	}

	private List<Quakk> concatQuacksFrom (final Timeline another) {
		final List<Quakk> allQuacks = new ArrayList<>(Arrays.asList((another.quakks)));
		allQuacks.addAll(Arrays.asList(this.quakks));
		return allQuacks;
	}

}
