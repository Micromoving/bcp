package cn.micromoving.bcp.modules.hr.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.micromoving.bcp.modules.hr.entity.Employee;
import cn.micromoving.bcp.modules.hr.entity.ReportedWorkloade;
import cn.micromoving.bcp.modules.hr.entity.SalEmpView;
import cn.micromoving.bcp.modules.sys.utils.DictUtils;

/**
 * @author 微动信息 Thinkpad
 * @version $Revision: $ $Date: $
 * 
 * <pre>
 * ■变更记录■
 * Oct 22, 2016 创建
 * </pre>
 */
public class HrEmpUtils {

    public static Workbook exportEmpDetails(Employee data) {
        return null;
    }

    /**
     * 导出工作量
     * 
     * @param filePath 该路径为全路径（包括路径和文件名）
     * @param list
     * @return
     * @throws Exception
     */
    public static Workbook exportWorkLoad(String filePath, List<ReportedWorkloade> list, List<SalEmpView> empList)
            throws Exception {

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        Workbook wb = new XSSFWorkbook(new BufferedInputStream(fis));

        Sheet sheet = wb.getSheetAt(0);

        int size = list.size();
        // 获取模板中68行的信息
        Row row68 = sheet.getRow(67);
        String message = row68.getCell(0).getStringCellValue();
        // 获取模板中68行的格式
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        style.cloneStyleFrom(sheet.getRow(67).getCell(0).getCellStyle());
        // 删除68行
        CellRangeAddress region = new CellRangeAddress(68, 68, 0, 0);
        sheet.addMergedRegion(region);
        sheet.getRow(67).getCell(0).setCellStyle(style);
        for (int i = 0; i < sheet.getNumMergedRegions(); ++i) {

            sheet.removeMergedRegion(i);
        }
        // sheet.removeRow(sheet.getRow(67));
        // 定义普通单元格格式
        CellStyle styleNormal = wb.createCellStyle();

        styleNormal.cloneStyleFrom(sheet.getRow(5).getCell(1).getCellStyle());
        // 针对工作量模板，所以从第6行开始循环，添加数据
        for (int i = 0; i < size; i++) {

            Row row = sheet.getRow(i);
            // 获取列数
            int columnNum = row.getLastCellNum();
            CellStyle cs = sheet.getRow(5).getRowStyle();
            if (i >= 60) {
                row = sheet.createRow(i + 5);
                row.setRowStyle(cs);
                for (int j = 0; j < 24; j++) {
                    row.createCell(j);
                }
            } else {
                row = sheet.getRow(i + 5);
            }
            SalEmpView sev = list.get(i).getSalEmpView();
            ReportedWorkloade rw = list.get(i);
            // 序号
            row.getCell(0).setCellValue(i + 1);
            row.getCell(0).setCellStyle(styleNormal);
            // 教师号
            row.getCell(1).setCellValue(sev.getLoginName());
            row.getCell(1).setCellStyle(styleNormal);
            // 姓名
            row.getCell(2).setCellValue(sev.getName());
            row.getCell(2).setCellStyle(styleNormal);
            // 岗位类别
            row.getCell(3).setCellValue(DictUtils.getDictLabel(sev.getPostType(), "post_type", ""));
            row.getCell(3).setCellStyle(styleNormal);
            // 职称
            row.getCell(4).setCellValue(DictUtils.getDictLabel(sev.getMaxTechPositionType(), "tech_position_type", ""));
            row.getCell(4).setCellStyle(styleNormal);
            // 职级
            row.getCell(5).setCellValue(
                    DictUtils.getDictLabel(sev.getMaxTechPositionLevel(), "tech_position_level", ""));
            row.getCell(5).setCellStyle(styleNormal);
            // 小计
            row.getCell(11).setCellValue(rw.getTeaching());
            row.getCell(11).setCellStyle(styleNormal);
            // 毕业论文
            row.getCell(12).setCellValue(rw.getDissertation());
            row.getCell(12).setCellStyle(styleNormal);
            // 导师制
            row.getCell(13).setCellValue(rw.getTutorialSystem());
            row.getCell(13).setCellStyle(styleNormal);
            // 公选
            row.getCell(14).setCellValue(rw.getOptional());
            row.getCell(14).setCellStyle(styleNormal);
            // 学年论文
            row.getCell(15).setCellValue(rw.getTermPaper());
            row.getCell(15).setCellStyle(styleNormal);
            // 阅卷
            row.getCell(16).setCellValue(rw.getTeaching());
            row.getCell(16).setCellStyle(styleNormal);
            // 出题
            row.getCell(17).setCellValue(rw.getTheTopic());
            row.getCell(17).setCellStyle(styleNormal);
            // 监考
            row.getCell(18).setCellValue(rw.getInvigilator());
            row.getCell(18).setCellStyle(styleNormal);
            // 小班讨论
            row.getCell(19).setCellValue(rw.getSmallClassDiscussion());
            row.getCell(19).setCellStyle(styleNormal);
            // 各类竞赛
            row.getCell(20).setCellValue(rw.getAllKindsOfCompetition());
            row.getCell(20).setCellStyle(styleNormal);
            // 其他
            row.getCell(21).setCellValue(rw.getOther());
            row.getCell(21).setCellStyle(styleNormal);
            // 职务补贴(此处没有值)
            row.getCell(22).setCellValue(0);
            row.getCell(22).setCellStyle(styleNormal);
            // 合计
            row.getCell(23).setCellValue(rw.getWorkload());
            row.getCell(23).setCellStyle(styleNormal);
        }
        // 若list为空则证明只导出空数据
        if (list.size() == 0) {
            size = empList.size();
            for (int i = 0; i < size; i++) {
                Row row = null;
                SalEmpView sev = empList.get(i);
                // 如果要导入的数据过多
                // 获取row的格式
                CellStyle cs = sheet.getRow(5).getRowStyle();
                if (i >= 60) {
                    row = sheet.createRow(i + 5);
                    row.setRowStyle(cs);
                    for (int j = 0; j < 24; j++) {
                        row.createCell(j);
                    }
                } else {
                    row = sheet.getRow(i + 5);
                }
                // 获取列数
                // int columnNum = row.getLastCellNum();
                // 序号
                row.getCell(0).setCellValue(i + 1);
                row.getCell(0).setCellType(Cell.CELL_TYPE_NUMERIC);
                row.getCell(0).setCellStyle(styleNormal);
                // 教师号
                row.getCell(1).setCellValue(sev.getLoginName());
                row.getCell(1).setCellStyle(styleNormal);
                // 姓名
                row.getCell(2).setCellValue(sev.getName());
                row.getCell(2).setCellStyle(styleNormal);
                // 岗位类别
                row.getCell(3).setCellValue(DictUtils.getDictLabel(sev.getPostType(), "post_type", ""));
                row.getCell(3).setCellStyle(styleNormal);
                // 职称
                row.getCell(4).setCellValue(
                        DictUtils.getDictLabel(sev.getMaxTechPositionType(), "tech_position_type", ""));
                row.getCell(4).setCellStyle(styleNormal);
                // 职级
                row.getCell(5).setCellValue(
                        DictUtils.getDictLabel(sev.getMaxTechPositionLevel(), "tech_position_level", ""));
                row.getCell(5).setCellStyle(styleNormal);
            }
        }
        // 设置最后一行
        Cell lastRowCell = sheet.createRow(size + 5).createCell(0);
        region = new CellRangeAddress(size + 5, size + 5, 0, 24);
        sheet.addMergedRegion(region);
        style.setFillBackgroundColor(new HSSFColor.YELLOW().getIndex());
        lastRowCell.setCellValue(message);
        lastRowCell.setCellStyle(style);
        return wb;
    }
}
