package monads;

import static org.junit.Assert.assertEquals;
import static monads.Stack.*;

import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private Pair pair = new Pair(empty(),null);

	@Test public void
	starts_empty() {
		assertEquals(empty(), pair.stack);
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		pop(pair.stack);
	}
	
	@Test public void
	pushes_and_pops_an_objects() {		
		pair = new Pair(push(pair.stack, A),null);
		
		pair = pop(pair.stack);
		assertEquals(A, pair.value);
		assertEquals(empty(), pair.stack);
	}
	
	@Test public void
	pops_objects_in_reverse_push_order() {
		pair = new Pair(push(pair.stack, A),null);
		pair = new Pair(push(pair.stack, B),null);
		
		pair = pop(pair.stack);
		assertEquals(B, pair.value);

		pair = pop(pair.stack);
		assertEquals(A, pair.value);

		assertEquals(empty(), pair.stack);
	}
}
