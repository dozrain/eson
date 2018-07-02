package com.rain.mapper;

import com.rain.model.vo.bill.AirlinesData;
import com.rain.model.vo.bill.BillOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface ExcelMapper extends Mapper<AirlinesData> {

    @Select({"select Z08.ZHTNO,Z08.ZHTYP,Z08.ZCURRPRICE ,Z08.ZCURRPRICE1,Z08.ZPRICE1,Z08.ZYD1,Z08.WAERS,Z08.ZSTORT,"+
            "Z09.ZZMCDM AS XFZZMCDM,Z10.ZZMCDM AS GFZZMCDM FROM zzlt008 Z08 " +
            "Left JOIN zzlt009  Z09 ON	Z08.ZHTNO = Z09.ZHTNO "+
            "Left JOIN zzlt010  Z10 ON	Z08.ZHTNO = Z10.ZHTNO "+
            "WHERE Z08.ZHTDAT = #{zhtdat} AND Z08.MATNR = #{matnr}"})
    AirlinesData selectByMatnrAndDate(@Param("matnr") String matnr ,@Param("zhtdat") String zhtdat);

    @Select({"SELECT * FROM company_bill_rule where COMPANY = #{company}"})
    BillOrder selectRuleByCompany(@Param("company") String company);

}