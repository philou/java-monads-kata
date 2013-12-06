package monads;

public interface Monad<T> {

	//public static Monad<Void> mFail(Stack stack) {
	//public static <U> Monad<U> mReturn(Stack stack, U value) {
	public abstract <U> Monad<U> bind(Stack.Function<U> operation);

}