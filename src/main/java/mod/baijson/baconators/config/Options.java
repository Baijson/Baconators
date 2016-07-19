package mod.baijson.baconators.config;

import java.util.Arrays;

/**
 * File created by Baijson.
 */
public class Options implements IOptions {

	private boolean enabled;
	private boolean dyeable;
	private boolean tooltip;
	private int capacity;
	private String[] accepted = new String[]{ };


	public Options ( boolean enabled, boolean dyeable, boolean tooltip, int capacity, String[] accepted ) {
		this.enabled = enabled;
		this.tooltip = tooltip;
		this.capacity = capacity;
		this.dyeable = dyeable;
		Arrays.sort ( accepted ); // sort.
		this.accepted = accepted;
	}

	/**
	 * @return
	 */
	@Override
	public boolean getEnabled () {
		return this.enabled;
	}

	@Override
	public boolean getDyeable () {
		return this.dyeable;
	}

	/**
	 * @return
	 */
	@Override
	public boolean getTooltipsEnabled () {
		return this.tooltip;
	}

	/**
	 * @return
	 */
	@Override
	public int getMaxCapacity () {
		return ( this.capacity > 0 ? this.capacity : 64 );
	}

	/**
	 * @return
	 */
	@Override
	public String[] getAcceptedFood () {
		return this.accepted;
	}
}
