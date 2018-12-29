
/**
 * 模拟Servlet中的Filter，学习责任链模式
 * @author LIQINGZHEN
 *
 */
public class Main {

	public static void main(String[] args) {
		FilterChain filterChain = new FilterChain();
		HTMLFilter htmlFilter = new HTMLFilter();
		SensitiveFilter sensitiveFilter = new SensitiveFilter();
		// 将各种过滤器添加到FilterChain
		filterChain.addFilter(htmlFilter);
		filterChain.addFilter(sensitiveFilter);
		// 示例
		Request request = new Request();
		Response response = new Response();
		request.setString("<script>，敏感词汇！");
		response.setString("response");
		// 过滤前
		System.out.println("filterChain index:" + filterChain.getIndex());
		System.out.println(request.getString());
		System.out.println(response.getString());
		System.out.println("------------------------------");
		
		filterChain.doFilter(request, response, filterChain);
		// 过滤后
		System.out.println("filterChain index:" + filterChain.getIndex());
		System.out.println(request.getString());
		System.out.println(response.getString());

	}

}
