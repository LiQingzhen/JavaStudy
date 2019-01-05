package personal.util;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	/*
	 * SqlSessionFactory是MyBatis的关键对象,它是个单个数据库映射关系经过编译后的内存镜像.
	 * SqlSessionFactory对象的实例可以通过SqlSessionFactoryBuilder对象类获得,
	 * 而SqlSessionFactoryBuilder则可以从XML配置文件或一个预先定制的Configuration的实例构建出SqlSessionFactory的实例.
	 * 每一个MyBatis的应用程序都以一个SqlSessionFactory对象的实例为核心.
	 * 同时SqlSessionFactory也是线程安全的,SqlSessionFactory一旦被创建,应该在应用执行期间都存在.
	 * 在应用运行期间不要重复创建多次,建议使用单例模式.SqlSessionFactory是创建SqlSession的工厂.
	 */
	private static SqlSessionFactory sessionFactory; 
	
	public static SqlSession getSqlSession(){
		try {
			// 加载主配置文件(mapper.xml已被注册其中)
			InputStream inputStream = Resources.getResourceAsStream("config.xml");
			// 创建SqlSessionFactory对象
			if (sessionFactory == null) {
				// inputStream不用关闭（build方法源码中已经关闭）
				sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			}
			
			// 返回SqlSession对象；阅读openSession源码发现默认关闭自动提交
			return sessionFactory.openSession();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
