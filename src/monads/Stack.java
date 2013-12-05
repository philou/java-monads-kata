package monads;

import static monads.Pair.*;

public final class Stack {

	private final Stack previous;
	private final Object state;
	
	public static Stack empty() {
		return null;
	}
	
	private Stack(Stack previous, Object state) {
		this.previous = previous;
		this.state = state;
	}
	
	public static Pair push(Stack self, Object a) {
		return pair(new Stack(self, a));
	}

	public static Pair pop(Stack self) {
		return pair(self.previous, self.state);
	}
}
