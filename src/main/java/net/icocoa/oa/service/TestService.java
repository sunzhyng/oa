package net.icocoa.oa.service;

import net.icocoa.oa.po.Test;

public interface TestService {
	public Test findById(String Id);
	public void insert(Test u);
}
