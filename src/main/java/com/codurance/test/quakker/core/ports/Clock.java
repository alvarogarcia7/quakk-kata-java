package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.DateTime;

public interface Clock {
	DateTime now ();
}
