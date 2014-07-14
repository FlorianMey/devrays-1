package app.jaid.devrays.entity;

import app.jaid.jtil.JTil;

public class Team {

	public static final Team ALLIES = new Team("Allies", false);
	public static final Team ENEMIES_BONUS = new Team("Bonus Enemies", true);
	public static final Team ENEMIES_MAIN = new Team("Enemies", true);
	public static final Team NEUTRALS_AGGRESSIVE = new Team("Neutral (aggressive)", true);
	public static final Team NEUTRALS_FORGIVING = new Team("Neutral", false);
	public static final Team OTHER = new Team("Other", false);
	public static final Team PLAYERS = new Team("Players", false);

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

	private Team(String name, boolean aggressive) {
		this.name = name;
	}

	public boolean isAggressive()
	{
		return isAggressive;
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
