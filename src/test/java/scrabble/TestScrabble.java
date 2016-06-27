package scrabble;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestScrabble {

	@Test
	public void testInitial() {
		Scrabble s=new EnglishScrabble();
		s.removeTiles("");
		assertEquals(
				"12: E\n"+
				"9: A, I\n"+
				"8: O\n"+
				"6: N, R, T\n"+
				"4: D, L, S, U\n"+
				"3: G\n"+
				"2: B, C, F, H, M, P, V, W, Y, _\n"+
				"1: J, K, Q, X, Z\n",s.toString());
	}

	@Test
	public void test1() {
		Scrabble s=new EnglishScrabble();
		s.removeTiles("AEERTYOXMCNB_S");
		assertEquals(
				"10: E\n"+
				"9: I\n"+
				"8: A\n"+
				"7: O\n"+
				"5: N, R, T\n"+
				"4: D, L, U\n"+
				"3: G, S\n"+
				"2: F, H, P, V, W\n"+
				"1: B, C, J, K, M, Q, Y, Z, _\n"+
				"0: X\n",s.toString());
	}

	@Test
	public void test2() {
		Scrabble s=new EnglishScrabble();
		s.removeTiles("LQTOONOEFFJZT");
		assertEquals(
				"11: E\n"+
				"9: A, I\n"+
				"6: R\n"+
				"5: N, O\n"+
				"4: D, S, T, U\n"+
				"3: G, L\n"+
				"2: B, C, H, M, P, V, W, Y, _\n"+
				"1: K, X\n"+
				"0: F, J, Q, Z\n",s.toString());
	}

	@Test
	public void test3() {
		Scrabble s=new EnglishScrabble();
		s.removeTiles("AXHDRUIOR_XHJZUQEE");
		assertEquals("Invalid input. More X's have been taken from the bag than possible.",s.toString());
	}
}
