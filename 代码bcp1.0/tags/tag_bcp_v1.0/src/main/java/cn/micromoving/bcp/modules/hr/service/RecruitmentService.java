package cn.micromoving.bcp.modules.hr.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.modules.hr.dao.RecruitmentDao;
import cn.micromoving.bcp.modules.hr.entity.Recruitment;

@Service
@Transactional(readOnly = true)
public class RecruitmentService extends
		CrudService<RecruitmentDao, Recruitment> {

	public boolean date(Recruitment recruitment) {
		Recruitment userData = new Recruitment();
		userData.setUser(recruitment.getUser());
		List<Recruitment> list = dao.findList(userData);
		boolean data = true;
		if(recruitment.getId() != null){
			list.remove(dao.get(recruitment.getId()));
			
		}
		for (Recruitment recruitment2 : list) {
			/* 记录 s 加s 记录e */
			if (recruitment2.getStartDate().before(recruitment.getStartDate())
					&& recruitment2.getEndDate().after(
							recruitment.getStartDate())) {
				data = false;
				break;
			} /* 记录s 加e 记录e */
			else if (recruitment2.getStartDate().before(
					recruitment.getEndDate())
					&& recruitment2.getEndDate()
							.after(recruitment.getEndDate())) {
				data = false;
				break;
			} /* 加s 记录s 加e */
			else if (recruitment.getStartDate().before(
					recruitment2.getStartDate())
					&& recruitment.getEndDate().after(
							recruitment.getStartDate())) {
				data = false;
				break;
			}/* 加s 记录e 加e */
			else if (recruitment.getStartDate().before(
					recruitment2.getEndDate())
					&& recruitment.getEndDate()
							.after(recruitment2.getEndDate())) {
				data = false;
				break;
			}
		}
		return data;
	}
}
