package mod.baijson.baconators.config;

/**
 * File created by Baijson.
 */
public interface IOptions {

	/**
	 * @return
	 */
	boolean getEnabled ();

	/**
	 * @return
	 */
	boolean getDyeable ();

	/**
	 * @return
	 */
	boolean getTooltipsEnabled ();

	/**
	 * @return
	 */
	int getMaxCapacity ();

	/**
	 * @return
	 */
	String[] getAcceptedFood ();
}
