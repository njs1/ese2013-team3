package ch.unibe.scg.team3.wordfinder.test;

import java.io.IOException;

import android.test.AndroidTestCase;
import ch.unibe.scg.team3.localDatabase.MySQLiteHelper;
import ch.unibe.scg.team3.localDatabase.WordlistAlreadyInDataBaseException;
import ch.unibe.scg.team3.localDatabase.WordlistHandler;
import ch.unibe.scg.team3.wordfinder.R;

import com.parse.Parse;

public class WordlistHandlerTest extends AndroidTestCase  {
	
	protected WordlistHandler wordlistHandler;
	
	public void testGetRandomWordFromWordlist() {

		String string = wordlistHandler.getRandomWordFromWordlist();
		assertNotNull(string);
		assertFalse(string.equals(""));

	}

	public void testAddEmptyWordlist() throws WordlistAlreadyInDataBaseException {
		wordlistHandler.addEmptyWordlist("TestAddEmptyWordlist");
		assertTrue(wordlistHandler.isWordlistInDatabase("TestAddEmptyWordlist"));
		// wordlistHandler.addEmptyWordlist("TestAddEmptyWordlist");

	}

	public void testAddWordToWordlist() throws WordlistAlreadyInDataBaseException {
		wordlistHandler.addEmptyWordlist("TestAddWordToWordlist");
		wordlistHandler.addWord("teste", "TestAddWordToWordlist");
		assertTrue(wordlistHandler.isWordInWordlist("teste", wordlistHandler.getWordlistId("TestAddWordToWordlist")));
	}

	public void testGetFirstLetterFromInputToLowerCase() {
		assertTrue(wordlistHandler.firstLetterOf("Test").equals("t"));

	}

	public void testRemoveWordlist() throws WordlistAlreadyInDataBaseException {
		wordlistHandler.addEmptyWordlist("TestRemoveWordlist");
		wordlistHandler.removeWordlist("TestRemoveWordlist");
		assertFalse(wordlistHandler.isWordlistInDatabase("TestRemoveWordlist"));
		assertFalse(wordlistHandler.isWordlistInDatabase(""));
	}

	public void testRemoveWordFromWordlist()
			throws WordlistAlreadyInDataBaseException {
		wordlistHandler.addEmptyWordlist("TestRemoveWordFromWordlist");
		wordlistHandler
				.addWord("Teste", "TestRemoveWordFromWordlist");
		wordlistHandler.removeWord("teste",
				"TestRemoveWordFromWordlist");
		assertFalse((wordlistHandler.isWordInWordlist("Teste",
				wordlistHandler.getWordlistId("TestRemoveWordFromWordlist"))));
	}

	public void testIsWordInWordlist()
			throws WordlistAlreadyInDataBaseException {
		wordlistHandler.addEmptyWordlist("TestIsWordInWordlist");
		wordlistHandler.addWord("Teste", "TestIsWordInWordlist");
		assertTrue(wordlistHandler.isWordInWordlist("Teste",
				wordlistHandler.getWordlistId("TestIsWordInWordlist")));

	}

	public void testIsWordlistInDatabase() {
		assertTrue(wordlistHandler.isWordlistInDatabase("English"));
		assertTrue(wordlistHandler.isWordlistInDatabase("Deutsch"));
		assertFalse(wordlistHandler.isWordlistInDatabase(""));

	}

	public void testGetWordlistId() {
		assertTrue(wordlistHandler.getWordlistId("English") == 1);
		assertTrue(wordlistHandler.getWordlistId("Deutsch") == 2);
		assertTrue(wordlistHandler.getWordlistId("") == -1);

	}

	public void testGetWordlists() {
		assertNotNull(wordlistHandler.getWordlists());
		assertTrue(wordlistHandler.getWordlists().size() >= 2);

	}

	public void testGetWordlistids() {
		assertNotNull(wordlistHandler.getWordlistIds());
		assertTrue(wordlistHandler.getWordlistIds().size() >= 2);
	}

	public void testGetRandomWordFromWordlistByLetter() {
		String a = MySQLiteHelper.ALPHABET;
		wordlistHandler = new WordlistHandler(mContext.getApplicationContext());
		for (int i = 0; i < a.length(); i++) {
			String temp = wordlistHandler.getRandomWordStartingWith(a
					.substring(i, i + 1));
			String temp2 = wordlistHandler
					.firstLetterOf(temp);
			assertTrue(a.substring(i, i + 1).equals(temp2));
		}
	}
	
	public void setUp() throws Exception {
		super.setUp();
		 wordlistHandler = new WordlistHandler(mContext.getApplicationContext());
		 try {
			 
			wordlistHandler.copyDB();
		} catch (IOException e) {
			e.printStackTrace();
		}
		android.preference.PreferenceManager.setDefaultValues(
				mContext.getApplicationContext(), R.xml.preferences, false);
		Parse.initialize(mContext, "ORYli0X0QqbH3Oefe0wvI2TsYa4d4Kw7sYKZFYuK",
				"FYUWqwq1E0VlFkVUXs6Fus1OZUN6CfqJo81EPbTJ");
		
	}
}
