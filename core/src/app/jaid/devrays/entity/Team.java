package app.jaid.devrays.entity;

import app.jaid.jtil.JTil;

/**
 * Each {@link Entity} must be assigned to a team. An entity's team specifies here which other teams they are able to
 * attack. The assigned team of an enemy or NPC also affects its behaviour, it tells them which teams to help, which
 * teams to ignore and which teams to attack.
 *
 * @author jaid
 */
public enum Team {

	ALLIES("Allies", false), ENEMIES_BONUS("Bonus Enemies", true), ENEMIES_MAIN("Enemies", true), NEUTRALS_AGGRESSIVE("Neutral (aggressive)", true), NEUTRALS_FORGIVING("Neutral", false), OTHER("Other", false), PLAYERS("Players", false);

	static
	{
		ALLIES.setLoves(PLAYERS);
		ENEMIES_MAIN.setLoves(ENEMIES_BONUS);
		NEUTRALS_AGGRESSIVE.setLoves(NEUTRALS_FORGIVING);

		ENEMIES_MAIN.setHates(PLAYERS, ALLIES);
		ENEMIES_BONUS.setHates(NEUTRALS_AGGRESSIVE, NEUTRALS_FORGIVING);
	}

	private Team[] hates, loves;
	private boolean isAggressive;
	private String name;

	private Team(String name, boolean aggressive)
	{
		this.name = name;
	}

	public boolean canAttack(Team other)
	{
		return this != other;
	}

	public boolean isAggressive()
	{
		return isAggressive;
	}

	public boolean isGood()
	{
		return this == PLAYERS || this == ALLIES;
	}

	public boolean isHating(Team other)
	{
		return JTil.arrayContainsReference(hates, other);
	}

	public boolean isLoving(Team other)
	{
		return JTil.arrayContainsReference(loves, other);
	}

	private void setHates(Team... teams)
	{
		hates = teams;
	}

	private void setLoves(Team... teams)
	{
		loves = teams;
	}

	@Override
	public String toString()
	{
		return name;
	}

}
