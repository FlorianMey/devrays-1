package app.jaid.devrays.etc;

import app.jaid.devrays.Core;

public class Scheduler {

	float charge;
	float frequency;

	public Scheduler(float frequency)
	{
		this.frequency = frequency;
	}

	public float getFrequency()
	{
		return frequency;
	}

	/**
	 * @return A progress value between 0 and 1
	 */
	public float getProgress()
	{
		return Math.min(charge / getFrequency(), 1);
	}

	public boolean isReady()
	{
		return charge >= frequency;
	}

	public boolean request()
	{
		if (isReady())
		{
			charge = 0;
			return true;
		}

		return false;
	}

	public void reset()
	{
		charge = 0;
	}

	public void setFrequency(float frequency)
	{
		this.frequency = frequency;
	}

	public void update()
	{
		charge += Core.delta;
	}

}
