package com.mybatisplus.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author JiangHao at 10/31/2021 11:19 AM
 */
public interface OracleMapper {

    @Select("select count(*) from APP_ROLE")
    int test();

    @Insert("insert into CS20200813.bms_st_io_doc(inoutid,credate,comefrom,sourcetable,sourceid,companyid,companyname,keepdate,keepmanid,goodsid,storageid,inoutflag,outqty,oldqty,goodsunit,usestatus,entryid,preparestatus)\n" +
            "(select a.inoutid , a.credate, 40,  40, 16224750, a.companyid,   a.companyname, a.keepdate,a.keepmanid, a.goodsid,  a.storageid,  a.inoutflag,  a.outqty,  a.oldqty,  a.goodsunit,  2, 2, 3\n" +
            " from CS20200813.bms_st_io_doc_tmp a where  a.inoutid = #{erpDetailId} or a.inoutid =8256931)")
    void addInsertTable1(@Param("erpDetailId") Integer erpDetailId);
}
