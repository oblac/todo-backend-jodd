package org.jodd.todo;

import jodd.joy.JoyContextListener;

/**
 * This class actually starts the Jodd web application.
 * Here we just want to explicitly disable database access.
 * This is not necessary (as DB will not be used if not configured),
 * but we wanted to show all this as an example.
 */
public class TodoAppContextListener extends JoyContextListener {
}
