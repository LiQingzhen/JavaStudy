package personal.lab01;

public interface Filter {
	void doFilter(Request request, Response response, FilterChain chain);
}
