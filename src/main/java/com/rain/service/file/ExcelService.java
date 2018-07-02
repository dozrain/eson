package com.rain.service.file;

import com.rain.mapper.ExcelMapper;
import com.rain.model.vo.bill.Bill;
import com.rain.model.vo.bill.AirlinesData;
import com.rain.model.vo.bill.BillOrder;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017\11\10 0010.
 */
@Service
public class ExcelService {
    @Resource
    private ExcelMapper excelMapper;
    /**
     * @author  rain
     * @date    2016年10月17日 下午2:19:39
     * @version 1.0
     */
    private static String jbFormula = "现价*2*7";
    private static String zlFormula = "现价*汇率*折旧";
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;

    /**
     * 读取Excel里面客户的信息
     * @param
     * @return
     */
    public List getBill(List<Bill> billList) {
        double bestBill = 0.00;
        double finalBill = 0.00;
        for (Bill bill : billList) {
            AirlinesData airlinesData =  excelMapper.selectByMatnrAndDate(bill.getMatnr(),bill.getZhtdat());
            Map map = getBestBill(airlinesData,bill);
            BillOrder billOrder =  excelMapper.selectRuleByCompany(airlinesData.getGFZZMCDM());
            if(airlinesData!=null&&airlinesData.getZHTYP().equals("GNJB")){//价拨
                if(bill.getTaxCost()<billOrder.getMINCONSUM()){//最低消费
                    bill.setBestBill(billOrder.getMINCONSUM());
                    bill.setFormula("最低消费");
                }else{//非最低消费
                    if(billOrder.getBillOrder().equals("NMG")){
                        if(Double.parseDouble(map.get(1).toString())!=0){
                            finalBill = Double.parseDouble(map.get(1).toString());
                        }else if(finalBill ==0&&Double.parseDouble(map.get(2).toString())!=0){
                            finalBill = Double.parseDouble(map.get(2).toString());
                        }
                        bestBill =  finalBill >bill.getTaxCost()? finalBill: bill.getTaxCost();
                        bill.setFormula(jbFormula);
                    }else if(billOrder.getBillOrder().equals("NG")){
                        bestBill =  Double.parseDouble(map.get(1).toString()) >bill.getTaxCost()? Double.parseDouble(map.get(1).toString()): bill.getTaxCost();
                        bill.setFormula(jbFormula);
                    }else{
                        bill.setFormula("无");
                        bill.setStatus("暂无报价");
                    }
                    bill.setBestBill(bestBill);
                }
            }else{//租赁
                if(billOrder.getBillOrder().equals("MNAG")){
                    if(Double.parseDouble(map.get(1).toString())!=0){
                        finalBill = Double.parseDouble(map.get(1).toString());
                    }else if(finalBill ==0&&Double.parseDouble(map.get(2).toString())!=0){
                        finalBill = Double.parseDouble(map.get(2).toString());
                    }else if(finalBill ==0&&Double.parseDouble(map.get(3).toString())!=0){
                        finalBill = Double.parseDouble(map.get(3).toString());
                    }
                    bestBill =  finalBill >bill.getTaxCost()? finalBill: bill.getTaxCost();
                    bill.setFormula(zlFormula);
                }else if(billOrder.getBillOrder().equals("NMAG")){
                    if(Double.parseDouble(map.get(1).toString())!=0){
                        finalBill = Double.parseDouble(map.get(1).toString());
                    }else if(finalBill ==0&&Double.parseDouble(map.get(2).toString())!=0){
                        finalBill = Double.parseDouble(map.get(2).toString());
                    }
                    bestBill =  finalBill >bill.getTaxCost()? finalBill: bill.getTaxCost();
                    bill.setFormula(jbFormula);
                }else{
                    bill.setFormula("无");
                    bill.setStatus("暂无报价");
                }
                bill.setBestBill(bestBill);
                bill.setFormula(zlFormula);
            }
        }
        return billList;
    }

    public Map getBestBill(AirlinesData airlinesData,Bill bill){
        bill.setStatus("可参考");
        String zprice1String = airlinesData.getZPRICE1();
        Map billMap = new HashMap();
        double nmoePrice =0.00;
        double boeingPrice = 0.00;
        double airbusPrice = 0.00;
        double aoemPrice = 0.00;
        if(!zprice1String.equals("")&&airlinesData.getZYD1().equals(getCurrentYear())){
            nmoePrice = Double.parseDouble(zprice1String.replaceAll("\\$",""));
            billMap.put(1,nmoePrice);
        }else{
            billMap.put(1,nmoePrice);
        }
        if(!zprice1String.equals("")&&!airlinesData.getZYD1().equals(getCurrentYear())){
            aoemPrice = Double.parseDouble(zprice1String.replaceAll("\\$",""));
            billMap.put(3,aoemPrice);
        }else {
            billMap.put(3,aoemPrice);
        }
        if(airlinesData.getZSTORT().contains("A")){
            airbusPrice = airlinesData.getZCURRPRICE1();
            if(airbusPrice ==0){
                bill.setStatus("待确认");
                airbusPrice = airlinesData.getZCURRPRICE();
            }
            billMap.put(2,airbusPrice);
        }else if(airlinesData.getZSTORT().contains("B")){
            boeingPrice = airlinesData.getZCURRPRICE();
            if(boeingPrice == 0){
                bill.setStatus("待确认");
                boeingPrice = airlinesData.getZCURRPRICE1();
            }
            billMap.put(2,boeingPrice);
        }else {
            billMap.put(2,boeingPrice);
        }
        return billMap;
    };
    public static String getCurrentYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }
    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    public List readExcelValue(Workbook wb) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        //获取sheet数量
        int sheetNum = wb.getNumberOfSheets();
        // 得到第一个sheet
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List billList = new ArrayList();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            Bill bill = new Bill();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            String zhtdat = sdf.format(cell.getDateCellValue());
                            bill.setZhtdat(zhtdat);
                        }else{
                            bill.setZhtdat(cell.getStringCellValue());
                        }
                    }
                    if(c == 3 ){
                        bill.setZbjsta(cell.getStringCellValue());//类型
                    }
                    if (c == 9) {
                        bill.setMatnr(cell.getStringCellValue());//件号
                    }
                    if (c == 17) {
                        bill.setTaxCost(cell.getNumericCellValue());//含税费用
                    }

                }
            }
            billList.add(bill);
        }
        return billList;
    }

    public List getExcelDataByDB(){
        return excelMapper.selectAll();
    }


}
