package cn.micromoving.bcp.modules.hr.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.micromoving.bcp.common.config.Global;
import cn.micromoving.bcp.common.persistence.Page;
import cn.micromoving.bcp.common.service.CrudService;
import cn.micromoving.bcp.common.utils.word.ImportWord;
import cn.micromoving.bcp.modules.hr.dao.EmployDao;
import cn.micromoving.bcp.modules.hr.entity.Employ;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;

@Service
@Transactional(readOnly = true)
public class EmployService extends CrudService<EmployDao, Employ> {
    @Autowired
    private EmployDao employDao;

    public Page<Employ> findEmploy(Page<Employ> page, Employ employ) {
        // TODO Auto-generated method stub
        // 生成数据权限过滤条件（dsf为dataScopeFilter的简写，在xml中使用 ${sqlMap.dsf}调用权限SQL）

        // 设置分页参数
        employ.setPage(page);
        // 执行分页查询
        page.setList(employDao.findList(employ));
        return page;

    }

    public void saveEmploy(Employ entity) {
        if (entity.getIsNewRecord()) {
            entity.preInsert();
            entity.setApplyDate(new Date());
            entity.setUpdateDate(new Date());
            employDao.insert(entity);
        } else {
            entity.preUpdate();
            entity.setUpdateDate(new Date());
            employDao.update(entity);
        }
    }

    public Employ getByUser(String userId) {

        Employ data = employDao.getByUser(userId);
        if (data == null) {
            data = new Employ();
        }
        return data;
    }

    public void importWord(Employ employ, String path) throws UnsupportedEncodingException {

        Map<String, Object> dataMap = new HashMap<String, Object>();

        if (employ.getUser().getPhoto() != null) {

            dataMap.put("image", ImportWord.getImage(path + Global.getConfig("uploads.user") + "profile/", employ
                    .getUser().getPhoto()));
        } else {
            dataMap.put("image", "");
        }
        dataMap.put("name", employ.getName());
        dataMap.put("gender", DictUtils.getDictLabel(employ.getGender(), "gender", ""));
        dataMap.put("nation", DictUtils.getDictLabel(employ.getNation(), "nation", ""));
        dataMap.put("placeDomicile", employ.getPlaceDomicile());
        dataMap.put("declarePositionNumber",
                DictUtils.getDictLabel(employ.getDeclarePositionNumber(), "declare_position_number", ""));
        dataMap.put("postType", DictUtils.getDictLabel(employ.getPostType(), "post_type", ""));
        dataMap.put("birthDate", employ.getBirthDateString());
        dataMap.put("politicsStatus", DictUtils.getDictLabel(employ.getPoliticsStatus(), "politics_status", ""));
        dataMap.put("maritalStatus", DictUtils.getDictLabel(employ.getMaritalStatus(), "marital_status", ""));
        dataMap.put("IDCardNumber", employ.getIDCardNumber());
        dataMap.put("mobile", employ.getMobile());
        if (employ.getCorrespondenceAddress() == null) {
            dataMap.put("correspondenceAddress", "无");
        } else {
            dataMap.put("correspondenceAddress", employ.getCorrespondenceAddress());
        }
        if (employ.getFixedPhone() == null) {
            dataMap.put("fixedPhone", "无");
        } else {
            dataMap.put("fixedPhone", employ.getFixedPhone());
        }
        if (employ.getEmail() == null) {
            dataMap.put("email", "无");
        } else {
            dataMap.put("email", employ.getEmail());
        }
        dataMap.put("highestEduBackground",
                DictUtils.getDictLabel(employ.getHighestEduBackground(), "highest_edu_background", ""));
        dataMap.put("highestDegree", DictUtils.getDictLabel(employ.getHighestDegree(), "academic_degree", ""));
        dataMap.put("highestStartDateString", employ.getHighestStartDateString());
        dataMap.put("highestEndDateString", employ.getHighestEndDateString());
        dataMap.put("highestGraduateSchool", employ.getHighestGraduateSchool());
        dataMap.put("highestMajor", employ.getHighestMajor());
        dataMap.put("firstEduBackground", DictUtils.getDictLabel(employ.getFirstEduBackground(), "first_edu_background", ""));
        dataMap.put("firstDegree", DictUtils.getDictLabel(employ.getFirstDegree(), "academic_degree", ""));
        dataMap.put("firstStartDateString", employ.getFirstStartDateString());
        dataMap.put("firstEndDateString", employ.getFirstEndDateString());
        dataMap.put("firstGraduateSchool", employ.getFirstGraduateSchool());
        dataMap.put("firstMajor", employ.getFirstMajor());
        if (employ.getIsWorkExperience().equals("0")) {
            dataMap.put("workingUnit", "无");
            dataMap.put("workingPost", "无");
            dataMap.put("inWorkDate", "无");
        } else {
            dataMap.put("workingUnit", employ.getWorkingUnit());
            dataMap.put("workingPost", employ.getWorkingPost());
            dataMap.put("inWorkDate", employ.getInWorkDateString());
        }
        if (employ.getQualifications() == null) {
            dataMap.put("Qualifications", "无");
        } else {
            dataMap.put("Qualifications", employ.getQualifications());
        }
        if (employ.getTechPosition() == null) {
            dataMap.put("techPosition", "无");
        } else {
            dataMap.put("techPosition", employ.getTechPosition());
        }
        dataMap.put("familyStstus", employ.getFamilyStatus());
        dataMap.put("personalProfile", employ.getPersonalProfile());
        if (employ.getComments() == null) {
            dataMap.put("comments", "无");
        } else {
            dataMap.put("comments", employ.getComments());
        }

        ImportWord w = new ImportWord();
        w.createDoc(dataMap, path + Global.getConfig("uploads.user") + "employ/" + employ.getId() + ".doc");

    }
}
