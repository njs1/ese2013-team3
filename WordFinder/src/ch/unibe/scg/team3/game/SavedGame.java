package ch.unibe.scg.team3.game;

import java.util.ArrayList;

import ch.unibe.scg.team3.board.Board;
import ch.unibe.scg.team3.board.RawBoardBuilder;
import ch.unibe.scg.team3.board.WordSelection;

/**
 * A saved game stores all the data needed to recover the game and replay it.
 * 
 * @author adrian
 * @author nils
 * @see AbstractGame
 */
public class SavedGame extends AbstractGame {

	private String name;
	private String date;
	private String board;
	
	private long remainingTime;
	private int foundWords;

	public ArrayList<WordSelection> getFoundWords() {
		return found;
	}

	public void setFoundWords(ArrayList<WordSelection> found) {
		this.found = found;
	}

	public void setNumberOfFoundWords(int foundWords) {
		this.foundWords = foundWords;
	}

	@Override
	public int getNumberOfFoundWords() {
		return foundWords;
	}

	/**
	 * @return The string representation of the board.
	 */
	public String getStringBoard() {
		return board;
	}

	public void setStringBoard(String stringBoard) {

		try {
			double side = Math.sqrt(stringBoard.length());
			if (side % 1 == 0) {
				board = stringBoard;
			}
		} catch (Exception e) {
			board = "";
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public long getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(long remaining) {
		remainingTime = remaining;
	}

	@Override
	public long getElapsedTime() {
		return Game.TIME_LIMIT - getRemainingTime();
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setWordlistId(int wordlistId) {
		this.wordlistId = wordlistId;
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Returns if the game is private. Private games are games explicitly saved
	 * by the user. Non-private games are saved automatically and not visible to
	 * the user.
	 * 
	 * @return True, if the game is private, false otherwise.
	 */
	public boolean isPersonal() {
		return isPersonal;
	}

	public void setPersonal(boolean isPersonal) {
		this.isPersonal = isPersonal;
	}

	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	@Override
	public boolean isOver() {
		return true;
	}

	@Override
	public Board getBoard() {
		RawBoardBuilder builder = new RawBoardBuilder(board);
		return builder.getBoard();
	}

	@Override
	public int getBoardSize() {
		return (int) Math.sqrt(board.length());
	}

	@Override
	public String toString() {
		String labels = "Score: %s\nFound Words: %s\nAttempted Words: %s\nElapsed Time: %s\n";

		String text = String.format(labels, getScore(), getNumberOfFoundWords(),
				getNumberOfAttempts(), Timer.format(getElapsedTime()));

		return text;
	}
}
