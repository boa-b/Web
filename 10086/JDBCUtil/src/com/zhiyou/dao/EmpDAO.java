package com.zhiyou.dao;

import java.util.List;

import com.zhiyou.model.Emp;

// EMP ��DAO �ӿ�
public interface EmpDAO {
	// ��
	void add(Emp e);
	// ɾ
	void delete(Emp e);
	// ��
	void update(Emp e); 
	// ��
	List<Emp> selectAll();
	
}
