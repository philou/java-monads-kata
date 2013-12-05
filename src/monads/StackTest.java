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
	private static class StackPush implements StackFunction {
		private Object value;

		public StackPush(Object value) {
			this.value = value;
		}

		public Pair eval(Stack stack) {
			return push(stack, value);
		}
	}
	private static class StackPop implements StackFunction {
		public Pair eval(Stack stack) {
			return pop(stack);
		}
	}
	
	@Test public void
	pushes_and_pops_an_objects() {	
		pair = new StackPush(A).eval(pair.stack);
		pair = new StackPop().eval(pair.stack);

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
