package com.codurance.test.quakker.core.adapters;

import com.codurance.test.quakker.core.domain.DateTime;
import com.codurance.test.quakker.core.ports.Clock;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class SystemClock implements Clock{
	@Override
	public DateTime now () {
		return new DateTime(new SimpleDateFormat("HH:mm:ss").format(Date.from(Instant.now())));
	}
}
