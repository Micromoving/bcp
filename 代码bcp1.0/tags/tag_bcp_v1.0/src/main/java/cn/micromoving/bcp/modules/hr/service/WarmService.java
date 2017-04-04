package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.SalaryPlanDao;
import cn.micromoving.bcp.modules.hr.dao.WarmDao;
import cn.micromoving.bcp.modules.hr.entity.Warm;
@Service
@Transactional(readOnly = true)
public class WarmService extends CrudService<WarmDao, Warm>{
	
	@Autowired
	/*定义一个WarmDao类型的字段*/
	private WarmDao warmDao;
	@Autowired
	/*定义一个SalaryPlanDao类型的字段*/
	private SalaryPlanDao salaryPlanDao;

	/*定义批量保存数据的方法，其中有Warm类型的entity*/
	public void batchSave(Warm entity) {
		// TODO Auto-generated method stub
		/*将数据id放入dataIdList1*/
		List<String> dataIdList1 = entity.getDataIdList1();
		/*将工资标准放入chargeStandardList1*/
		List<Double> chargeStandardList1 = entity.getChargeStandardList1();
		/*根据id循环进行更新取暖标准*/
		for (int index = 0; index < dataIdList1.size(); index++) {
			/*定义id为dataIdList1中对应数据*/
			String id = dataIdList1.get(index);
			/*定义chargeStandard为chargeStandardList1中对应数据*/
			Double chargeStandard = chargeStandardList1.get(index);
			/*定义Warm类型data，根据id同过warmDao获取值*/
			Warm data = warmDao.get(id);
			/*在data中设置chargeStandard值为chargeStandard*/
			data.setChargeStandard(chargeStandard);
			/*调用warmDao中update方法更新数据*/
			warmDao.update(data);
		}
		/*以下同上*/
		List<String> dataIdList3 = entity.getDataIdList3();
		List<Double> chargeStandardList3 = entity.getChargeStandardList3();
		for (int index = 0; index < dataIdList3.size(); index++) {
			String id = dataIdList3.get(index);
			Double chargeStandard = chargeStandardList3.get(index);
			Warm data = warmDao.get(id);
			data.setChargeStandard(chargeStandard);
			warmDao.update(data);
		}
		List<String> dataIdList2 = entity.getDataIdList2();
		List<Double> chargeStandardList2 = entity.getChargeStandardList2();
		for (int index = 0; index < dataIdList2.size(); index++) {
			String id = dataIdList2.get(index);
			Double chargeStandard = chargeStandardList2.get(index);
			Warm data = warmDao.get(id);
			data.setChargeStandard(chargeStandard);
			warmDao.update(data);
		}
		List<String> dataIdList5 = entity.getDataIdList5();
		List<Double> chargeStandardList5 = entity.getChargeStandardList5();
		for (int index = 0; index < dataIdList5.size(); index++) {
			String id = dataIdList5.get(index);
			Double chargeStandard = chargeStandardList5.get(index);
			Warm data = warmDao.get(id);
			data.setChargeStandard(chargeStandard);
			warmDao.update(data);
		}
		List<String> dataIdList4 = entity.getDataIdList4();
		List<Double> chargeStandardList4 = entity.getChargeStandardList4();
		for (int index = 0; index < dataIdList4.size(); index++) {
			String id = dataIdList4.get(index);
			Double chargeStandard = chargeStandardList4.get(index);
			Warm data = warmDao.get(id);
			data.setChargeStandard(chargeStandard);
			warmDao.update(data);
		}
	}
}
