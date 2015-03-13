package net.icocoa.oa.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.icocoa.oa.po.SysResource;

@Repository
public class ResourceDaoImpl implements IResourceDao {

	@Autowired
	private IResourceDao resourceDao;
	
	public List<SysResource> findAll() {
		return resourceDao.findAll();
	}

	public List<String> loadRoleByResource(String url) {
		return resourceDao.loadRoleByResource(url);
	}

}
