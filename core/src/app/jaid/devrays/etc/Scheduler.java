package app.jaid.devrays.etc;

import app.jaid.devrays.Core;

public class Scheduler {

	float charge;
	float frequency;

	public Scheduler(float frequency)
	{
		this.frequency = frequency;
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

	public void update()
	{
		charge += Core.delta;
	}

}
