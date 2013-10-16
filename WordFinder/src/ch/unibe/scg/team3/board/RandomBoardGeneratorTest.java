package ch.unibe.scg.team3.board;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import ch.unibe.jexample.JExample;
import ch.unibe.scg.team3.token.IToken;
import ch.unibe.scg.team3.token.NullToken;

/**
 * 
 * @author adrian
 * 
 */
@RunWith(JExample.class)
public class RandomBoardGeneratorTest {

	@Test
	public void testBoardWithSize1(){
		RandomBoardGenerator r = new RandomBoardGenerator(1);
		r.generate();
		
		Board board = r.getBoard();
		
		IToken tok = board.getToken(0, 0);
		IToken nul = NullToken.getInstance();
		
		assertFalse(tok.getLetter() == nul.getLetter());
	}
	
	@Test 
	public void testBoardNoNullToken(){
		RandomBoardGenerator r = new RandomBoardGenerator(100);
		r.generate();
		
		Board board = r.getBoard();
		IToken nul = NullToken.getInstance();
		
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		for(int i = 0; i < board.getSize(); i++){
			for(int j = 0; j < board.getSize(); j++){
				
				IToken tok = board.getToken(i, j);
				assertFalse(tok.equals(nul));
				assertTrue(alphabet.contains(tok.toString()));
			}
		}
	}
	
}
