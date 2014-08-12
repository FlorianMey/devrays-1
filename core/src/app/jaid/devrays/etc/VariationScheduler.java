package app.jaid.devrays.etc;

import app.jaid.jtil.JRand;

public class VariationScheduler extends Scheduler {

	float currentFrequency;
	float variation;

	public VariationScheduler(float frequency, float variation)
	{
		super(frequency);

		if (variation >= frequency) // Check to prevent negative frequency on roll()
			throw new IllegalArgumentException("Frequency (" + frequency + ") must be higher than variation (" + variation + ").");

		this.variation = variation;
		roll();
	}

	@Override
	public float getFrequency()
	{
		return currentFrequency;
	}

	public float getVariation()
	{
		return variation;
	}

	@Override
	public boolean isReady()
	{
		return charge >= currentFrequency;
	}

	@Override
	public boolean request()
	{
		if (super.request())
		{
			roll();
			return true;
		}

		return false;
	}

	/**
	 * Sets currentFrequency to a new random value based on frequency and variation.
	 */
	private void roll()
	{
		currentFrequency = JRand.vary(frequency, variation);
	}

}
