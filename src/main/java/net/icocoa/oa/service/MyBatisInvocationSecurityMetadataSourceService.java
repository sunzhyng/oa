package net.icocoa.oa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.icocoa.oa.dao.ResourceDaoImpl;
import net.icocoa.oa.po.SysResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MyBatisInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private ResourceDaoImpl resourcesDao;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public MyBatisInvocationSecurityMetadataSourceService(ResourceDaoImpl resourcesDao) {
		this.resourcesDao = resourcesDao;
		System.out.println("加载MyInvocationSecurityMetadataSourceService..." + resourcesDao);
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		if (resourceMap == null) {
			resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
			List<SysResource> resources = resourcesDao.findAll();
			for (SysResource resource : resources) {
				List<String> authList = resourcesDao.loadRoleByResource(resource.getResource());
				
				Collection<ConfigAttribute> auths = new ArrayList<ConfigAttribute>();
				for (String roleName : authList) {
					ConfigAttribute auth = new SecurityConfig(roleName);
					auths.add(auth);
				}
				
				System.out.println("power=" + auths);
				resourceMap.put(resource.getResource(), auths);
			}
		}
	}

	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation) object).getRequestUrl();
		System.out.println("requestUrl is " + requestUrl);

		int firstQuestionMarkIndex = requestUrl.indexOf("?");

		if (firstQuestionMarkIndex != -1) {
			requestUrl = requestUrl.substring(0, firstQuestionMarkIndex);
		}

		if (resourceMap == null) {
			loadResourceDefine();
		}
		//
		Iterator<String> ite = resourceMap.keySet().iterator();

		while (ite.hasNext()) {
			String resURL = ite.next();

			if (resURL.equals(requestUrl)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> arg0) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

}