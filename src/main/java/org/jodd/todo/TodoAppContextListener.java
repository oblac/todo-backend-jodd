package org.jodd.todo;

import jodd.joy.JoddJoy;
import jodd.joy.JoyContextListener;
import jodd.joy.JoyDb;

public class TodoAppContextListener extends JoyContextListener {
	@Override
	protected JoddJoy createJoy() {
		return JoddJoy.get()
			.withDb(JoyDb::disableDatabase);
	}
}
