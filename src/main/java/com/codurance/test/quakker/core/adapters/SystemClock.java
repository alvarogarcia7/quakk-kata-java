package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.ports.Clock;

public class SystemClock implements Clock{
	@Override
	public DateTime now () {
		return DateTime.now();
	}
}
