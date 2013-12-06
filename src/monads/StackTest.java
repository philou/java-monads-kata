package monads;

import static org.junit.Assert.assertEquals;
import static monads.Stack.*;
import static monads.StackAndValue.*;

import org.junit.Test;

public class StackTest {
	
	private static final Object A = new Object();
	private static final Object B = new Object();

	private StackAndValue<?> monad = mFail(empty());

	@Test public void
	starts_empty() {
		assertEquals(empty(), monad.stack);
	}
	
	@Test(expected = Exception.class) public void
	forbids_pop_when_empty() {
		monad = monad.bind(pop());
	}
	
	@Test public void
	pushes_and_pops_an_objects() {	
		monad = monad.
				bind(push(A)).
				bind(pop());

		assertEquals(A, monad.value);
		assertEquals(empty(), monad.stack);
	}

	@Test public void
	pops_objects_in_reverse_push_order() {
		monad = monad.
				bind(push(A)).
				bind(push(B)).
				
				bind(pop());

		assertEquals(B, monad.value);

		monad = monad.bind(pop());
		assertEquals(A, monad.value);

		assertEquals(empty(), monad.stack);
	}
}
