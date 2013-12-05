package monads;

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
	
	public static Stack push(Stack self, Object a) {
		return new Stack(self, a);
	}

	public static Pair pop(Stack self) {
		return new Pair(self.previous, self.state);
	}
}
