package baijson.baconators.config;

/**
 *
 */
public class IOptions implements IIOptions {
	private boolean enabled = false;
	private boolean tooltips = false;
	private int capacity = 64;
	private String[] accepted = new String[]{ };

	/**
	 *
	 * @param enabled
	 * @param accepted
	 * @param capacity
	 * @param tooltips
	 */
	public IOptions ( boolean enabled, String[] accepted, int capacity, boolean tooltips ) {
		this.enabled = enabled;
		this.accepted = accepted;
		this.capacity = capacity;
		this.tooltips = tooltips;
	}

	/**
	 * @return
	 */
	@Override
	public boolean getEnabled () {
		return this.enabled;
	}

	@Override
	public void setEnabled ( boolean value ) {
		this.enabled = value;
	}

	/**
	 * @return
	 */
	@Override
	public boolean getTooltipsEnabled () {
		return this.tooltips;
	}

	@Override
	public void setTooltipsEnabled ( boolean value ) {
		this.tooltips = value;
	}

	/**
	 * @return
	 */
	@Override
	public int getMaxCapacity () {
		return ( this.capacity > 0 ? this.capacity : 64 );
	}

	@Override
	public void setMaxCapacity ( int value ) {
		this.capacity = value;
	}

	/**
	 * @return
	 */
	@Override
	public String[] getAcceptedFood () {
		return this.accepted;
	}

	@Override
	public void setAcceptedFood ( String[] value ) {
		this.accepted = value;
	}

	/**
	 *
	 * @return
	 */
	public String getCustomDisplayName() {
		return "";
	}

	/**
	 *
	 */
	public void setCustomDisplayName() {

	}
}
