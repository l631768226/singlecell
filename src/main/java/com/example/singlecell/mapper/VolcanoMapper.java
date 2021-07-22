package com.example.singlecell.mapper;

import com.example.singlecell.model.VolcanoData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface VolcanoMapper {

    /**
     * 根据gene查询火山图相关散点信息
     * @return
     */
    @Select({
            "<script>",
                "select * from volcanodata where gene in ",
                    "<foreach open = '(' close = ')' separator = ',' index = 'index' collection = 'geneList' item = 'gene' >",
                        "#{gene}",
                    "</foreach>",
                " order by status asc",
            "</script>"
    })
    List<VolcanoData> findListInGene(@Param("geneList")List<String> geneList);


    @Select("select * from volcanodata where celltype = #{celltype} and dataset = #{dataset}")
    List<VolcanoData> findListByCellTypeAndDataset(@Param("celltype")String celltype, @Param("dataset")String dataset);
}
