package net.icocoa.oa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.icocoa.oa.dao.TestDao;
import net.icocoa.oa.po.Test;

@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestDao testDao;

	public Test findById(String Id) {
		return testDao.findById(Id);
	}

	public void insert(Test u) {
		testDao.insert(u);
	}


}
