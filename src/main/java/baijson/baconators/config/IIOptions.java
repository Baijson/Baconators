package baijson.baconators.config;

/**
 *
 */
public interface IIOptions {

	/**
	 * @return
	 */
	boolean getEnabled ();

	/**
	 *
	 * @param value
	 */
	void setEnabled ( boolean value );

	/**
	 * @return
	 */
	boolean getTooltipsEnabled ();

	/**
	 *
	 * @param value
	 */
	void setTooltipsEnabled ( boolean value );

	/**
	 * @return
	 */
	int getMaxCapacity ();

	/**
	 *
	 * @param value
	 */
	void setMaxCapacity ( int value );

	/**
	 * @return
	 */
	String[] getAcceptedFood ();

	/**
	 *
	 * @param value
	 */
	void setAcceptedFood ( String[] value );
}
