/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.test.dao;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.test.entity.TestDataChild;

/**
 * 主子表生成DAO接口
 * @author songcm
 * @version 2015-04-06
 */
@MyBatisDao
public interface TestDataChildDao extends CrudDao<TestDataChild> {
	
}