package com.example.singlecell.mapper;

import com.example.singlecell.model.GoDown;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GodownMapper {

    @Insert({
            "<script>",
                "Insert into godown (ontology, goid, description, pvalue, logpvalue, geneid, status, celltype, dataset) values ",
                "<foreach collection = 'godownlist' item='item' index = 'index' separator = ','>",
                    "(#{item.ontology}, #{item.goid}, #{item.description}, #{item.pvalue}, #{item.logpvalue}, #{item.geneid}," +
                            "#{item.status}, #{item.celltype}, #{item.dataset})",
                "</foreach>",
            "</script>"
    })
    Integer insertBatchGoDown(@Param("godownlist") List<GoDown> godonwList);


    @Select("select * from godown where celltype = #{celltype} and dataset = #{dataset} and status = #{status} order by pvalue desc")
    List<GoDown> findListByCelltypeAndDataset(@Param("celltype")String celltype, @Param("dataset")String dataset, @Param("status")String status);
}
