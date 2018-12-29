public class SensitiveFilter implements Filter {

	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {
		request.setString(request.getString().replaceAll("敏感", "XX"));
		request.setString(request.getString() + "--SensitiveFilter");
		
		chain.doFilter(request, response, chain);
		
		response.setString(response.getString() + "--SensitiveFilter");

	}

}
