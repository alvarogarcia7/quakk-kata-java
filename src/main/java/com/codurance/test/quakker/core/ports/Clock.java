package com.codurance.test.quakker.core.ports;

import com.codurance.test.quakker.core.domain.DateTime;

public interface Clock {
    DateTime now ();
}
