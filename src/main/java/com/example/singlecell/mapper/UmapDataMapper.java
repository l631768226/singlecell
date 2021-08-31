package com.example.singlecell.mapper;

import com.example.singlecell.model.UmapData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UmapDataMapper {

    @Select({
            "<script>",
                "select * from umapdata where celltype in ",
                "<foreach open='(' close=')' separator = ',' item = 'celltype' index = 'index' collection = 'celltypeList'>",
                    "#{celltype}",
                "</foreach>",
                " group by celltype order by celltype asc ",
            "</script>"
    })
    List<UmapData> findDataByCellTypeList(@Param("celltypeList")List<String> cellTypeList);

    @Select("select * from umapdata where celltype = #{celltype} and dataset = #{dataset} order by celltype asc")
    List<UmapData> findDataByCellType(@Param("celltype")String celltype, @Param("dataset")String dataset);
}
