package ch.unibe.scg.team3.wordlist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import ch.unibe.scg.team3.wordfinder.R;

import android.content.Context;

/**
 * 
 * @author adrian
 *
 */
public class WordlistBuilder {
	
	private final Wordlist wordlist;

	public WordlistBuilder(String name){
		this.wordlist = new Wordlist(name);
	}
	
	public void addWords(String words){
		Scanner scan = new Scanner(words);
		scan.useDelimiter("" + Wordlist.WORD_SEPARATOR);
		
		while(scan.hasNext()){
			wordlist.addWord(scan.next());
		}
	}
	/**
	 * Reads from an InputStream line-by-line and adds the words to a wordlist.
	 * 
	 * @param reader An instance of the BuffereReader class reading from /res/raw/textfile
	 */
	public void addWords(BufferedReader reader){
		String line;
		try {
			while((line = reader.readLine()) != null) {
				wordlist.addWord(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Wordlist getWordlist() {
		return this.wordlist;
	}
}
