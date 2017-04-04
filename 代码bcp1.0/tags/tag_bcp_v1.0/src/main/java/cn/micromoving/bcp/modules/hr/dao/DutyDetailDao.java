package cn.micromoving.bcp.modules.hr.dao;

import java.util.List;

import cn.micromoving.bcp.common.persistence.CrudDao;
import cn.micromoving.bcp.common.persistence.annotation.MyBatisDao;
import cn.micromoving.bcp.modules.hr.entity.DutyDetail;

/**
 * 值班明细表Dao接口
 * 
 * @author Administrator
 * @version wenayng
 */
@MyBatisDao
public interface DutyDetailDao extends CrudDao<DutyDetail> {
	/**
	 * 根据流程实例ID，取得值班实体记录。
	 * 
	 * @param procInsId
	 *            值班实体中的流程实例ID
	 * @return 值班实体记录。
	 */
	public DutyDetail getByProcInsId(DutyDetail dutyDetail);
	void deleteByReportId(DutyDetail dutyDetail);

	int count(DutyDetail data);
	
}
