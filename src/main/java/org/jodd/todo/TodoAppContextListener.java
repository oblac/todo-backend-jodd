package org.jodd.todo;

import jodd.joy.JoddJoy;
import jodd.joy.JoyContextListener;
import jodd.joy.JoyDb;

/**
 * This class actually starts the Jodd web application.
 * Here we just want to explicitly disable database access.
 * This is not necessary (as DB will not be used if not configured),
 * but we wanted to show all this as an example.
 */
public class TodoAppContextListener extends JoyContextListener {
	@Override
	protected JoddJoy createJoy() {
		return JoddJoy.get()
			.withScanner(joyScanner -> joyScanner.scanClasspathOf(this))    // required for java9
			.withDb(JoyDb::disableDatabase);
	}
}
