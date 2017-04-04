/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/micromoving/bcp">bcp</a> All rights reserved.
 */
package cn.micromoving.bcp.common.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import cn.micromoving.bcp.common.utils.DateUtils;
import cn.micromoving.bcp.common.utils.Reflections;
import cn.micromoving.bcp.common.utils.excel.annotation.ExcelField;
import cn.micromoving.bcp.common.utils.excel.fieldtype.OfficeType;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalaryDetails;
import cn.micromoving.bcp.modules.hr.entity.SalaryInstance;
import cn.micromoving.bcp.modules.hr.entity.SalaryItems;
import cn.micromoving.bcp.modules.hr.entity.Teacher;
import cn.micromoving.bcp.modules.hr.service.SalaryInstanceService;
import cn.micromoving.bcp.modules.sys.entity.Office;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;
import cn.micromoving.bcp.modules.sys.utils.UserUtils;

import com.google.common.collect.Lists;

/**
 * 导入Excel文件（支持“XLS”和“XLSX”格式）
 * 
 * @author songcm
 * @version 2013-03-10
 */
public class ImportExcel {

    private static Logger log = LoggerFactory.getLogger(ImportExcel.class);

    /**
     * 工作薄对象
     */
    private Workbook wb;

    /**
     * 工作表对象
     */
    private Sheet sheet;

    /**
     * 标题行号
     */
    private int headerNum;

    /**
     * 构造函数
     * 
     * @param path
     *            导入文件，读取第一个工作表
     * @param headerNum
     *            标题行号，数据行号=标题行号+1
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcel(String fileName, int headerNum)
            throws InvalidFormatException, IOException {
        this(new File(fileName), headerNum);
    }

    /**
     * 构造函数
     * 
     * @param path
     *            导入文件对象，读取第一个工作表
     * @param headerNum
     *            标题行号，数据行号=标题行号+1
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcel(File file, int headerNum) throws InvalidFormatException,
            IOException {
        this(file, headerNum, 0);
    }

    /**
     * 构造函数
     * 
     * @param path
     *            导入文件
     * @param headerNum
     *            标题行号，数据行号=标题行号+1
     * @param sheetIndex
     *            工作表编号
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcel(String fileName, int headerNum, int sheetIndex)
            throws InvalidFormatException, IOException {
        this(new File(fileName), headerNum, sheetIndex);
    }

    /**
     * 构造函数
     * 
     * @param path
     *            导入文件对象
     * @param headerNum
     *            标题行号，数据行号=标题行号+1
     * @param sheetIndex
     *            工作表编号
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcel(File file, int headerNum, int sheetIndex)
            throws InvalidFormatException, IOException {
        this(file.getName(), new FileInputStream(file), headerNum, sheetIndex);
    }

    /**
     * 构造函数
     * 
     * @param file
     *            导入文件对象
     * @param headerNum
     *            标题行号，数据行号=标题行号+1
     * @param sheetIndex
     *            工作表编号
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcel(MultipartFile multipartFile, int headerNum,
            int sheetIndex) throws InvalidFormatException, IOException {
        this(multipartFile.getOriginalFilename(), multipartFile
                .getInputStream(), headerNum, sheetIndex);
    }

    /**
     * 构造函数
     * 
     * @param path
     *            导入文件对象
     * @param headerNum
     *            标题行号，数据行号=标题行号+1
     * @param sheetIndex
     *            工作表编号
     * @throws InvalidFormatException
     * @throws IOException
     */
    public ImportExcel(String fileName, InputStream is, int headerNum,
            int sheetIndex) throws InvalidFormatException, IOException {
        if (StringUtils.isBlank(fileName)) {
            throw new RuntimeException("导入文档为空!");
        } else if (fileName.toLowerCase().endsWith("xls")) {
            this.wb = new HSSFWorkbook(is);
        } else if (fileName.toLowerCase().endsWith("xlsx")) {
            this.wb = new XSSFWorkbook(is);
        } else {
            throw new RuntimeException("文档格式不正确!");
        }
        if (this.wb.getNumberOfSheets() < sheetIndex) {
            throw new RuntimeException("文档中没有工作表!");
        }
        this.sheet = this.wb.getSheetAt(sheetIndex);
        this.headerNum = headerNum;
        log.debug("Initialize success.");
    }

    /**
     * 获取行对象
     * 
     * @param rownum
     * @return
     */
    public Row getRow(int rownum) {
        return this.sheet.getRow(rownum);
    }

    /**
     * 获取数据行号
     * 
     * @return
     */
    public int getDataRowNum() {
        return headerNum + 1;
    }

    /**
     * 获取最后一个数据行号
     * 
     * @return
     */
    public int getLastDataRowNum() {
        return this.sheet.getLastRowNum() + headerNum;
    }

    /**
     * 获取最后一个列号
     * 
     * @return
     */
    public int getLastCellNum() {
        return this.getRow(headerNum).getLastCellNum();
    }

    /**
     * 获取单元格值
     * 
     * @param row
     *            获取的行
     * @param column
     *            获取单元格列号
     * @return 单元格值
     */
    public Object getCellValue(Row row, int column) {
        Object val = "";
        try {
            Cell cell = row.getCell(column);
            if (cell != null) {
                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                    val = cell.getNumericCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                    val = cell.getStringCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                    val = cell.getCellFormula();
                } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
                    val = cell.getBooleanCellValue();
                } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
                    val = cell.getErrorCellValue();
                }
            }
        } catch (Exception e) {
            return val;
        }
        return val;
    }

    /**
     * 获取导入数据列表
     * 
     * @param cls
     *            导入对象类型
     * @param groups
     *            导入分组
     * @see int... 表示可变参数，可以是1个可以是n个，数组在传递参数时只能讲一个数组传入。
     * @param Annotation
     *            --注解处理器：用来读取注解的方法和工作，在这里用到了java的反射机制。
     * @param Object
     *            所有类的父类，万金油一样的存在。
     * @param method
     *            反射机制。
     */
    public <E> List<E> getDataList(Class<E> cls, int... groups)
            throws InstantiationException, IllegalAccessException {
        List<Object[]> annotationList = Lists.newArrayList(); // 创建一个Object[]类的泛型ArrayList容器
        // Get annotation field
        Field[] fs = cls.getDeclaredFields(); // 返回 Field 对象的一个数组，这些对象反映此 Class
                                                // 对象所表示的类或接口所声明的所有字段。
        for (Field f : fs) { // 遍历循环fs数组
            ExcelField ef = f.getAnnotation(ExcelField.class); // getAnnotation如果存在该元素的指定类型的注释，则返回这些注释，否则返回
                                                                // null。
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) { // type() 判断
                                                                    // ef是否导入
                if (groups != null && groups.length > 0) { // 判断groups不为空
                    boolean inGroup = false;
                    for (int g : groups) { // 循环这个可变参数中的值
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) { // 遍历循环 ef的字段归属组
                            if (g == efg) { // 如果传入的groups的值与ef中字段归属组的值相等则跳出循环，并且将ef和fs数组对应的值封装成一个Object数组添加到annotationList容器中
                                inGroup = true;
                                annotationList.add(new Object[] { ef, f });
                                break;
                            }
                        }
                    }
                } else { // 若为空或者长度为0则直接将ef和fs数组对应的值封装成一个Object数组添加到annotationList容器中
                    annotationList.add(new Object[] { ef, f });
                }
            }
        }
        // Get annotation method
        /*
         * 返回 Method 对象的一个数组，这些对象反映此 Class 对象表示的类或接口声明的所有方法，
         * 包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。返回数组中的元素没有排序，也没有任何特定的顺序。
         */
        Method[] ms = cls.getDeclaredMethods();
        for (Method m : ms) { // 循环遍历ms这个数组
            ExcelField ef = m.getAnnotation(ExcelField.class); // 以下同上，不懂得仔细阅读上文
            if (ef != null && (ef.type() == 0 || ef.type() == 2)) {
                if (groups != null && groups.length > 0) {
                    boolean inGroup = false;
                    for (int g : groups) {
                        if (inGroup) {
                            break;
                        }
                        for (int efg : ef.groups()) {
                            if (g == efg) {
                                inGroup = true;
                                annotationList.add(new Object[] { ef, m });
                                break;
                            }
                        }
                    }
                } else {
                    annotationList.add(new Object[] { ef, m });
                }
            }
        }

        Collections.sort(annotationList, new Comparator<Object[]>() { // 在Collections.sort()方法中重写compare方法来自定义排序规则对指定的ArrayList容器进行排序
                    public int compare(Object[] o1, Object[] o2) { // compare是一个匿名内部类，用来自定义排序规则
                        /*
                         * 先将传入的o1、o2强转为ExcelField类型，在按照系统自定义的规则先进行排序，
                         * 然后将每个数组的第一个值拿出来进行比较，规则如下： (o1 < o2) ? -1 : ((x == y)
                         * ? 0 : 1)
                         */
                        return new Integer(((ExcelField) o1[0]).sort())
                                .compareTo(new Integer(((ExcelField) o2[0])
                                        .sort()));
                    };
                });
        // log.debug("Import column count:"+annotationList.size());
        // Get excel data
        List<E> dataList = Lists.newArrayList(); // 创建一个泛型容器
        for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++) { // 从第一个数据行开始循环遍历整个数据行
            E e = (E) cls.newInstance(); // 创建此 Class
                                            // 对象所表示的类的一个新实例。如同用一个带有一个空参数列表的 new
                                            // 表达式实例化该类。如果该类尚未初始化，则初始化这个类。
            int column = 0;
            Row row = this.getRow(i); // 获取指定的行对象
            StringBuilder sb = new StringBuilder(); // 创建一个可变字符串系列的对象
            for (Object[] os : annotationList) {
                Object val = this.getCellValue(row, column++); // 获取指定的单元格值
                if (val != null) { // 若该单元格不为空
                    ExcelField ef = (ExcelField) os[0];
                    // If is dict type, get dict value
                    if (StringUtils.isNotBlank(ef.dictType())) { // 若字典的type值不为空
                        val = DictUtils.getDictValue(val.toString(),
                                ef.dictType(), ""); // 获取字典中val.toString()与ef.dictType()相符的值
                        // log.debug("Dictionary type value: ["+i+","+colunm+"] "
                        // + val);
                    }
                    // Get param type and type cast
                    Class<?> valType = Class.class; // <?>是泛型中的通配符，此处并无实际意义 |
                                                    // 将Class的class对象赋给valType
                    if (os[1] instanceof Field) { // 若os[1]的类型是Field类型
                        valType = ((Field) os[1]).getType(); // 将os[1]强转为Field类型并且将对应的Class对象赋值给valType
                                                                // |
                                                                // getType()-->返回一个
                                                                // Class
                                                                // 对象，它标识了此
                                                                // Field
                                                                // 对象所表示字段的声明类型。
                    } else if (os[1] instanceof Method) { // 若os[1]的类型是Method类型
                        Method method = ((Method) os[1]);
                        if ("get".equals(method.getName().substring(0, 3))) { // 若method对象表示的方法名称的前三个字符串为get则将valType的值改变成metehod对应的Class对象
                                                                                // |
                                                                                // getReturnType()
                                                                                // -->返回一个
                                                                                // Class
                                                                                // 对象，该对象描述了此
                                                                                // Method
                                                                                // 对象所表示的方法的正式返回类型。
                            valType = method.getReturnType();
                        } else if ("set".equals(method.getName()
                                .substring(0, 3))) { // 若method对象表示的方法名称的前三个字符串为set则将valType的值改变成(Method)os[1]对应的Class对象数组的第一个值
                                                        // |getParameterTypes()
                                                        // -->按照声明顺序返回 Class
                                                        // 对象的数组，这些对象描述了此 Method
                                                        // 对象所表示的方法的形参类型。如果底层方法不带参数，则返回长度为
                                                        // 0 的数组。
                            valType = ((Method) os[1]).getParameterTypes()[0];
                        }
                    }
                    // log.debug("Import value type: ["+i+","+column+"] " +
                    // valType);
                    try { // try-catch 为异常处理机制 若try中的语句有一场则直接执行catch中的语句并抛出异常
                        if (valType == String.class) { // 若valType的值为Strng.class
                            String s = String.valueOf(val.toString()); // 返回
                                                                        // 参数的字符串表示形式。
                            if (StringUtils.endsWith(s, ".0")) { // 若s的后缀名为.0
                                val = StringUtils.substringBefore(s, ".0"); // 将
                                                                            // s的名称赋给val(不含后缀名的)
                            } else { // 否则将val以String的形式返回
                                val = String.valueOf(val.toString());
                            }
                        } else if (valType == Integer.class) {
                            val = Double.valueOf(val.toString()).intValue(); // intValue()-->将double的值转为Integer类型的值
                        } else if (valType == Long.class) {
                            val = Double.valueOf(val.toString()); // 返回保存用参数字符串val.toString()
                                                                    // 表示的
                                                                    // double 值的
                                                                    // Double
                                                                    // 对象。
                                                                    // 这几个的不同方法但都是实现的这个功能
                        } else if (valType == Float.class) {
                            val = Float.valueOf(val.toString());
                        } else if (valType == Double.class) {
                            val = Double.valueOf(val.toString());
                        }
                        else if (valType == Date.class) {
                            val = DateUtils.parseDate(val);
                        } else { // 如果valType不属于以上类型
                            if (ef.fieldType() != Class.class) { // 如果ef的反射类型不是基本类型的class对象
                                val = ef.fieldType()
                                        .getMethod("getValue", String.class)
                                        .invoke(null, val.toString()); // 将对应的类型赋给val(一般为自定义的类型)
                            } else {
                                val = Class
                                        .forName(
                                                this.getClass()
                                                        .getName()
                                                        .replaceAll(
                                                                this.getClass()
                                                                        .getSimpleName(),
                                                                "fieldtype."
                                                                        + valType
                                                                                .getSimpleName()
                                                                        + "Type"))
                                        .getMethod("getValue", String.class)
                                        .invoke(null, val.toString()); // 将除了以上的基本数据类型之外的剩下的基本数据类型的class对象赋值给val
                            }
                        }
                    } catch (Exception ex) {
                        log.info("Get cell value [" + i + "," + column
                                + "] error: " + ex.toString()); // 通过日志类输出指定内容
                        val = null;
                    }
                    // set entity value
                    if (os[1] instanceof Field) {
                        Reflections.invokeSetter(e, ((Field) os[1]).getName(),
                                val); // 匹配方法名
                    } else if (os[1] instanceof Method) {
                        String mthodName = ((Method) os[1]).getName();
                        if ("get".equals(mthodName.substring(0, 3))) { // 若mthodName的前三个字符为get
                            mthodName = "set"
                                    + StringUtils.substringAfter(mthodName,
                                            "get"); // 将set+第一次出现后的子串的分隔符 的值付给
                                                    // mthodName
                        }
                        Reflections.invokeMethod(e, mthodName,
                                new Class[] { valType }, new Object[] { val }); // invokeMethod直接调用e对象方法，无视private/protected修饰符.
                    }
                }
                sb.append(val + ", "); // 将每一次的val的值不断拼接到sb对象中
            }
            dataList.add(e); // 添加e到dataList中
            log.debug("Read success: [" + i + "] " + sb.toString()); // 通过日志类输出指定内容
        }
        return dataList;
    }

    public List<Teacher> getTeacherList() {

        List<Teacher> result = Lists.newArrayList();

        for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++) {

            Row row = this.getRow(i);

            Teacher data = new Teacher();
            data.setNum(row.getCell(0).getStringCellValue());
            data.setName(row.getCell(1).getStringCellValue());

            String valGender = DictUtils.getDictValue(row.getCell(2)
                    .getStringCellValue(), "gender", "无");
            data.setGender(valGender);

            data.setBirthDate(row.getCell(3).getDateCellValue());

            String valNation = DictUtils.getDictValue(row.getCell(4)
                    .getStringCellValue(), "nation", "无");
            data.setNation(valNation);

            data.setNativePlace(row.getCell(5).getStringCellValue());
            String valPapersType = DictUtils.getDictValue(row.getCell(6)
                    .getStringCellValue(), "papers_type", "无");
            data.setPapersType(valPapersType);
            data.setPapersNumber(row.getCell(7).getStringCellValue());

            String valMaritalStatus = DictUtils.getDictValue(row.getCell(8)
                    .getStringCellValue(), "marital_status", "无");
            data.setMaritalStatus(valMaritalStatus);

            Object obj = OfficeType.getValue(row.getCell(9)
                    .getStringCellValue());
            data.setOffice((Office) obj);
            // data.setOfficeName(row.getCell(9).getStringCellValue());

            data.setSpecialty(row.getCell(10).getStringCellValue());
            String valEndEduBackground = DictUtils.getDictValue(row.getCell(11)
                    .getStringCellValue(), "end_edu_background", "无");
            data.setEndEduBackground(valEndEduBackground);
            String valAcademicDegree = DictUtils.getDictValue(row.getCell(12)
                    .getStringCellValue(), "academic_degree", "无");
            data.setAcademicDegree(valAcademicDegree);

            data.setGraduateSchool(row.getCell(13).getStringCellValue());
            data.setResearchArea(row.getCell(14).getStringCellValue());
            data.setDuties(row.getCell(15).getStringCellValue());
            String valProfessionalTitle = DictUtils.getDictValue(row.getCell(16)
                    .getStringCellValue(), "professional_title", "无");
            data.setProfessionalTitle(valProfessionalTitle);
            
            String valProfessionalType = DictUtils.getDictValue(row.getCell(17)
                    .getStringCellValue(), "professional_type", "无");
            data.setProfessionalType(valProfessionalType);
            
            String valProfessionalLevel = DictUtils.getDictValue(row.getCell(18)
                    .getStringCellValue(), "professional_level", "无");
            data.setProfessionalLevel(valProfessionalLevel);

            data.setJobTitleDate(row.getCell(19).getDateCellValue());
            data.setInWorkDate(row.getCell(20).getDateCellValue());
            data.setInSchoolDate(row.getCell(21).getDateCellValue());
            String valDproTitled = DictUtils.getDictValue(row.getCell(22)
                    .getStringCellValue(), "dpro_titled", "无");
            data.setDproTitled(valDproTitled);
            String valPoliticsStatus = DictUtils.getDictValue(row.getCell(23)
                    .getStringCellValue(), "politics_status", "无");
            data.setPoliticsStatus(valPoliticsStatus);
            data.setEmail(row.getCell(24).getStringCellValue());
            data.setMobile(row.getCell(25).getStringCellValue());
            data.setPhone(row.getCell(26).getStringCellValue());
            data.setQqId(row.getCell(27).getStringCellValue());
            data.setWechatId(row.getCell(28).getStringCellValue());
            data.setHomeAdd(row.getCell(29).getStringCellValue());
            data.setPostcode(row.getCell(30).getStringCellValue());
            data.setResume(row.getCell(31).getStringCellValue());
            data.setHomepage(row.getCell(32).getStringCellValue());
            result.add(data);
        }
        return result;

    }
    
    /**
     * @param siList
     * @param salaryInstanceId
     * @return
     */
    public List<SalaryDetails> getSalaryDetailsList(List<SalaryItems> siList,SalaryInstance salaryInstance) {
    	/*将勾选项的英文名保存到remarkList中*/
    	List<String> remarkList = Lists.newArrayList();
		for (SalaryItems temp : siList) {
			remarkList.add(temp.getSalaryMark());
		}
		/*任何时候无论勾选不勾选都导入社保项的信息*/
		List<String> list = Lists.newArrayList();
		list.add("buckleRoomA");
		list.add("buckleHealthCare");
		list.add("buckleUnemploymentInsurance");
		list.add("buckleEndowmentInsurance");
		list.add("birthInsurance");
		for (String temp : list) {
			if(!remarkList.contains(temp)){
				remarkList.add(temp);
			}
		}
		/*初始化结果集*/
        List<SalaryDetails> result = Lists.newArrayList();
        
        /*循环文件中的每行*/
        for (int i = this.getDataRowNum(); i < this.getLastDataRowNum(); i++) {
        	/*获取当前行*/
        	Row row = this.getRow(i);
        	/*创建一个新的工资详情对象*/
        	SalaryDetails sd = new SalaryDetails();
        	System.out.println(row.getCell(0).getStringCellValue());
        	/*通过用户的登录名找到该用户并同步到工资详情对象中，其对应的姓名，部门，等信息也都同步过去了*/
        	Reflections.setFieldValue(sd, "user", UserUtils.getByLoginName(String.valueOf(row.getCell(0).getStringCellValue())));
        	/*设置上报年月*/
        	Reflections.setFieldValue(sd, "yearMonth", salaryInstance.getYearMonth());
        	/*循环勾选项*/
        	/*通过反射为工资详情对象赋值*/
        	for(int j = 0;j<remarkList.size();j++){
        		Reflections.setFieldValue(sd, remarkList.get(j), row.getCell(j+3).getNumericCellValue());
        	}
        	/*设置工资详情对象的salaryInstanceId*/
        	SalaryInstance si = new SalaryInstance();
        	si.setId(salaryInstance.getId());
        	Reflections.setFieldValue(sd, "salaryInstance",si);
        	/*设置总计*/
        	Reflections.setFieldValue(sd, "total",row.getCell(remarkList.size()+3).getNumericCellValue());
        	result.add(sd);
        }
        /*返回该结果集*/
        return result;
    }
    
    /**
     * @param siList
     * @param salaryInstanceId
     * @return
     */
    public List<ReportedWorkloade> getReportedWorkloadeList() {
		/*初始化结果集*/
        List<ReportedWorkloade> result = Lists.newArrayList();
        /*循环文件中的每行*/
        for (int i = 5; i < this.getLastDataRowNum(); i++) {
        	/*获取当前行*/
        	Row row = this.getRow(i);
        	ReportedWorkloade rw = new ReportedWorkloade();
        	if(row.getCell(1)==null){
        		continue;
        	}
        	Reflections.setFieldValue(rw, "user", UserUtils.getByLoginName(String.valueOf(row.getCell(1).getStringCellValue())));
        	if(rw.getUser()==null){
        		continue;
        	}
        	Reflections.setFieldValue(rw, "teaching",row.getCell(11).getNumericCellValue());
        	
        	Reflections.setFieldValue(rw, "dissertation",row.getCell(12).getNumericCellValue());
        	Reflections.setFieldValue(rw, "tutorialSystem",row.getCell(13).getNumericCellValue());
        	
        	Reflections.setFieldValue(rw, "optional",row.getCell(14).getNumericCellValue());
        	Reflections.setFieldValue(rw, "termPaper",row.getCell(15).getNumericCellValue());
        	
        	Reflections.setFieldValue(rw, "marking",row.getCell(16).getNumericCellValue());
        	Reflections.setFieldValue(rw, "theTopic",row.getCell(17).getNumericCellValue());
        	
        	Reflections.setFieldValue(rw, "invigilator",row.getCell(18).getNumericCellValue());
        	Reflections.setFieldValue(rw, "smallClassDiscussion",row.getCell(19).getNumericCellValue());
        	
        	Reflections.setFieldValue(rw, "allKindsOfCompetition",row.getCell(20).getNumericCellValue());
        	Reflections.setFieldValue(rw, "other",row.getCell(21).getNumericCellValue());
        	/*此处少一个职务补助，目前不读*/
        	Reflections.setFieldValue(rw, "workload",row.getCell(23).getNumericCellValue());
        	result.add(rw);
        }
        /*返回该结果集*/
        return result;
    }

    // /**
    // * 导入测试
    // */
    // public static void main(String[] args) throws Throwable {
    //
    // ImportExcel ei = new ImportExcel("f:/import_teacher.xlsx", 1);
    //
    // List<Teacher> result = ei.getTeacherList();
    // for (Teacher data : result) {
    // System.out.println(data.getName());
    // System.out.println(data.getMaritalStatus());
    // System.out.println(data.getInWorkDate());
    // // System.out.println(data.getOfficeName());
    // System.out.println(data.getOffice().getName());
    // }
    //
    // }

}
