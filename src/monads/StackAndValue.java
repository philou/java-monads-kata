package monads;


public final class StackAndValue<T> implements Monad<T> {
	
	public final Stack stack;
	public final T value;

	public static StackAndValue<Void> mFail(Stack stack) {
		return mReturn(stack, null);
	}
	public static <U> StackAndValue<U> mReturn(Stack stack, U value) {
		return new StackAndValue<U>(stack, value);
	}
		
	public StackAndValue(Stack stack, T value) {
		this.stack = stack;
		this.value = value;
	}

	@Override
	public <U> StackAndValue<U> bind(Stack.Function<U> operation) {
		return operation.eval(this.stack);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((stack == null) ? 0 : stack.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StackAndValue<?> other = (StackAndValue<?>) obj;
		if (stack == null) {
			if (other.stack != null)
				return false;
		} else if (!stack.equals(other.stack))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
