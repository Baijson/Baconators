package baijson.baconators.assets;

import org.apache.logging.log4j.LogManager;

/**
 *
 */
public class Logger {

	/**
	 * @param message
	 */
	static public void Warning ( String message ) {
		LogManager.getLogger ( Constants.MOD_ID ).warn ( message );
	}

	/**
	 * @param message
	 */
	static public void Error ( String message ) {
		LogManager.getLogger ( Constants.MOD_ID ).error ( message );
	}

	/**
	 * @param message
	 */
	static public void Message ( String message ) {
		LogManager.getLogger ( Constants.MOD_ID ).info ( message );
	}
}
