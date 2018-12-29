import java.util.ArrayList;
import java.util.List;

public class FilterChain {
	List<Filter> filters = new ArrayList<Filter>(); 
	private int index = 0;
	
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	public void addFilter(Filter filter){
		filters.add(filter);
	}
	public void removeFilter(int index){
		filters.remove(index);
	}
	
	public void doFilter(Request request, Response response, FilterChain chain){
		if(index == filters.size()){
			// index归零
			index = 0;
			return;
		}
		// 进行过滤
		Filter filter = chain.filters.get(index);
		// 转到下一个过滤器
		index++;
		filter.doFilter(request, response, chain);
	}
}
