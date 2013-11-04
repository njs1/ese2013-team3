package ch.unibe.scg.team3.wordfinder.test;

import android.test.AndroidTestCase;

import ch.unibe.scg.team3.board.Board;
import ch.unibe.scg.team3.token.Token;

/**
 * @author adrian
 */
public class BoardTest extends AndroidTestCase {

	public void testBoardWithSize1() {
		Board board = new Board(1);
		assertEquals(board.toString(), " ");
		assertEquals(board.getSize(), 1);
	}

	public void testSetTokenGetTokenAtCorners() {
		Board board = new Board(7);

		assertEquals(board.toString().length(), 49);

		assertEquals(board.getToken(0, 0).toString(), " ");
		assertEquals(board.getToken(0, 6).toString(), " ");
		assertEquals(board.getToken(6, 0).toString(), " ");
		assertEquals(board.getToken(6, 6).toString(), " ");

		Token tok1 = new Token('1', 1);
		Token tok2 = new Token('2', 2);
		Token tok3 = new Token('3', 3);
		Token tok4 = new Token('4', 4);

		board.setToken(tok1, 0, 0);
		board.setToken(tok2, 0, 6);
		board.setToken(tok3, 6, 0);
		board.setToken(tok4, 6, 6);

		assertEquals(board.getToken(0, 0).toString(), "1");
		assertEquals(board.getToken(0, 6).toString(), "2");
		assertEquals(board.getToken(6, 0).toString(), "3");
		assertEquals(board.getToken(6, 6).toString(), "4");
	}
}