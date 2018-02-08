package com.framework;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
//import org.apache.ibatis.executor.Executor;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.plugin.Interceptor;
//import org.apache.ibatis.plugin.Intercepts;
//import org.apache.ibatis.plugin.Invocation;
//import org.apache.ibatis.plugin.Plugin;
//import org.apache.ibatis.plugin.Signature;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.framework.util.DateTool;
import com.framework.util.Tools;
import com.fs.comm.model.Sysuser;

@Intercepts({ @Signature(type = Executor.class, method = "update", args = { MappedStatement.class, Object.class }) })
public class SqlInterceptor implements Interceptor {
	private static final Logger logger = Logger.getLogger(SqlInterceptor.class);

	/**
	 * 实现拦截的地方
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		Object target = invocation.getTarget();
		Object result = null;
		final Object[] args = invocation.getArgs();
		if (target instanceof Executor) {
			MappedStatement ms = (MappedStatement) args[0];
			Object parameterObject = args[1];
			BoundSql boundSql = ms.getBoundSql(parameterObject);
			long start = System.currentTimeMillis();
			Method method = invocation.getMethod();
			/** 执行方法 */
			result = invocation.proceed();
			long end = System.currentTimeMillis();
			logger.info("[TimerInterceptor] execute [" + method.getName() + "] cost [" + (end - start) + "] ms");
			String sql = boundSql.getSql();
			ObjectMapper mapper = new ObjectMapper();
			String objectjson = mapper.writeValueAsString(boundSql.getParameterObject());
			DataSource dataSource = ms.getConfiguration().getEnvironment().getDataSource();
			insertSysloginlog(dataSource, method.getName(), objectjson, sql);
		}
		return result;
	}

	/**
	 * insert日志
	 * 
	 * @param dataSource
	 * @return
	 */
	public String insertSysloginlog(DataSource dataSource, String method, String objectjson, String sourcesql) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int i = 0;
		try {
			conn = dataSource.getConnection();
			String sql = "insert into systemlog (id,username,ipadd,opertype,operobject,dotime,slogcomment) "
					+ "values(?,?,?,?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			UUID uuid = UUID.randomUUID();
			pstmt.setString(1, uuid.toString().replace("-", "").toUpperCase());
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			Sysuser sysuser = (Sysuser) session.getAttribute("currentUser");
			
			pstmt.setString(2, sysuser==null ? "interfaceAPI":sysuser.getLoginname());
			
			ServletRequest request = ((WebSubject) SecurityUtils.getSubject()).getServletRequest();
			String ip = Tools.getIpAddr((HttpServletRequest) request);
			pstmt.setString(3, ip);
			pstmt.setString(4, method);
			pstmt.setString(5, sourcesql);
			pstmt.setTimestamp(6, DateTool.getTimestamp());
			pstmt.setString(7, objectjson);
			try {
				i = pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return conn.getMetaData().getURL();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null) {
					// if(conn.isClosed()){
					conn.close();
					// }
				}
				if (pstmt != null) {
					// if(pstmt.isClosed()){
					pstmt.close();
					// }
				}
			} catch (SQLException e) {
				// ignore
			}
		}
	}

	/**
	 * Plugin.wrap生成拦截代理对象
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

}
