package net.icocoa.oa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import net.icocoa.oa.po.SysResource;

@Repository
public interface IResourceDao {
	
	public List<SysResource> findAll();

	public List<String> loadRoleByResource(String url);

}