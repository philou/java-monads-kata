package monads;

import static org.junit.Assert.assertEquals;
import static monads.Stack.*;

import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private Stack stack = empty();

	@Test public void
	starts_empty() {
		assertEquals(empty(), stack);
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		pop(stack);
	}
	
	@Test public void
	pushes_and_pops_an_objects() {		
		stack = push(stack, A);
		
		Pair pair = pop(stack);
		assertEquals(A, pair.value);
		assertEquals(empty(), pair.stack);
	}
	
	@Test public void
	pops_objects_in_reverse_push_order() {
		stack = push(stack, A);
		stack = push(stack, B);
		
		Pair pair = pop(stack);
		assertEquals(B, pair.value);

		pair = pop(pair.stack);
		assertEquals(A, pair.value);

		assertEquals(empty(), pair.stack);
	}
}
