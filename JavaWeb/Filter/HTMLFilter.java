package personal.lab01;
/**
 * HTML标签的过滤器
 * @author LIQINGZHEN
 *
 */
public class HTMLFilter implements Filter {
	
	@Override
	public void doFilter(Request request, Response response, FilterChain chain) {

		request.setString(request.getString().replace('<', '[').replace('>', ']'));
		request.setString(request.getString() + "--HTMLFilter");
		
		chain.doFilter(request, response, chain);
		
		response.setString(response.getString() + "--HTMLFilter");	
	}
}
