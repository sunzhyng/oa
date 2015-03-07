package net.icocoa.oa.dao;

import org.springframework.stereotype.Repository;

import net.icocoa.oa.po.Test;

@Repository
public interface TestDao {

	public Test findById(String Id);

	public void insert(Test u);
}