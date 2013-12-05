package monads;

import static org.junit.Assert.assertEquals;
import static monads.Stack.*;
import static monads.Pair.*;

import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private Pair pair = pair(empty());

	@Test public void
	starts_empty() {
		assertEquals(empty(), pair.stack);
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		pop(pair.stack);
	}
	
	private static interface StackFunction {
		Pair eval(Stack stack);
	}
	@Test public void
	pushes_and_pops_an_objects() {	
		pair = new StackFunction() {
			public Pair eval(Stack stack) {
				return stack.push(stack, A);
			}
		}.eval(pair.stack);
		
		pair = pop(pair.stack);
		assertEquals(A, pair.value);
		assertEquals(empty(), pair.stack);
	}
	
	@Test public void
	pops_objects_in_reverse_push_order() {
		pair = push(pair.stack, A);
		pair = push(pair.stack, B);
		
		pair = pop(pair.stack);
		assertEquals(B, pair.value);

		pair = pop(pair.stack);
		assertEquals(A, pair.value);

		assertEquals(empty(), pair.stack);
	}
}
