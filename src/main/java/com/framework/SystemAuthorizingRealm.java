package com.framework;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.subject.WebSubject;

import com.framework.util.DateTool;
import com.framework.util.MD5;
import com.framework.util.Tools;
import com.fs.comm.model.Department;
import com.fs.comm.model.Role;
import com.fs.comm.model.Sysuser;
import com.fs.web.service.DepartmentService;
import com.fs.web.service.RoleService;
import com.fs.web.service.SysuserService;
  
/** 
 * 自定义的指定Shiro验证用户登录的类  
 * @author dyl 
 */  
public class SystemAuthorizingRealm extends AuthorizingRealm {
	@Resource
    private SysuserService sysuserService;
	@Resource
	private RoleService roleService;
	@Resource
	private DepartmentService departmentService;
    /** 
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用 
     */  
    @Override  
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){  
        //获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()  
//        String currentUsername = (String)super.getAvailablePrincipal(principals);  
//        Sysuser member = sysuserService.adminLogin(currentUsername);  
//        if(member == null){  
//            throw new AuthenticationException("用户不存在");  
//        }  
//        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
//          
//        List<RoleModel> roleList = memberService.selectRoleByMemberId(member.getId());  
//        List<PermissionModel> permList = memberService.selectPermissionByMemberId(member.getId());  
//          
//        if(roleList != null && roleList.size() > 0){  
//            for(RoleModel role : roleList){  
//                if(role.getRoleCode() != null){  
//                    simpleAuthorInfo.addRole(role.getRoleCode());  
//                }  
//            }  
//        }  
//          
//        if(permList != null && permList.size() > 0){  
//            for(PermissionModel perm : permList){  
//                if(perm.getCode() != null){  
//                    simpleAuthorInfo.addStringPermission(perm.getCode());  
//                }  
//            }  
//        }  
//        return simpleAuthorInfo;  
    	return null;
    }  
  
      
    /** 
     * 认证回调函数, 登录时调用 
     */  
    @Override  
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {  
        //获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        UsernamePasswordToken token = (UsernamePasswordToken)authcToken;  
        Sysuser sysuser = sysuserService.adminLogin(token.getUsername(),null);  
        if(sysuser != null){  
            if(sysuser.getIsclose() !=null && sysuser.getIsclose() == 2){  
                throw new DisabledAccountException("已经注销");  
            }  
            if(token.getPassword()==null){
            	throw new IncorrectCredentialsException("密码错误");
            }else{
            	if(MD5.MD5Encode(String.valueOf(token.getPassword())).equals(sysuser.getLoginpwd())){
            		  AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(sysuser.getLoginname(), sysuser.getLoginpwd(), this.getName()); 
            		  sysuser.setLastlogin(DateTool.getTimestamp());
            		  sysuser.setLogintimes((sysuser.getLogintimes()==null?0:sysuser.getLogintimes())+1);
            		  if(sysuser.getPrivilege()!=null){
            			  Role role = roleService.loadRole(sysuser.getPrivilege());
                		  sysuser.setSysrole(role);
            		  }
            		  if(sysuser.getDepid()!=null){
            			  Department department = departmentService.loadDepartment(sysuser.getDepid());
            			  sysuser.setDepartment(department);
            		  }
                      this.setSession("currentUser", sysuser); 
                      ServletRequest request = ((WebSubject)SecurityUtils.getSubject()).getServletRequest(); 
                      String ip=Tools.getIpAddr((HttpServletRequest)request);
                      sysuserService.saveSysloginlog(sysuser, ip, "自主登录");
                      return authcInfo;  
                }else{
                	throw new IncorrectCredentialsException("密码错误");
                }
            }
        }  else{
        	throw new UnknownAccountException("账号不存在");
        }  
          
    }  
      
    /** 
     * 保存登录信息 
     */  
    private void setSession(Object key, Object value){  
        Session session = getSession();  
        if(null != session){  
            session.setAttribute(key, value);  
        }  
    }  
      
    private Session getSession(){  
        try{  
            Subject subject = SecurityUtils.getSubject();  
            Session session = subject.getSession(false);  
            if (session == null){  
                session = subject.getSession();  
            }  
            if (session != null){  
                return session;  
            }  
        }catch (InvalidSessionException e){  
              
        }  
        return null;  
    }  }
