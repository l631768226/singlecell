package com.example.singlecell.mapper;

import com.example.singlecell.model.Deg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DegMapper {

    @Select("select * from deg where gene = #{gene} order by gene asc")
    List<Deg> findListByGene(@Param("gene")String gene);

    @Select("select * from deg where dataset = #{dataset} and celltype = #{celltype} order by gene asc")
    List<Deg> findByDatasetAndCellType(@Param("dataset")String dataset, @Param("celltype")String cellType);

    @Select({
            "<script>",
                "select * from deg where 1 = 1 ",
                "<if test='tissue!=null and tissue != \"\"'>",
                    "and tissue = #{tissue} ",
                "</if>",
                "<if test='dataset!=null and dataset != \"\"'>",
                    "and dataset = #{dataset} ",
                "</if>",
                "<if test='celltype!=null and celltype != \"\"'>",
                    "and celltype = #{celltype} ",
                "</if>",
            "</script>"
    })
    List<Deg> findSearch(@Param("tissue")String tissue, @Param("dataset")String dataset, @Param("celltype")String celltype);

    @Select("select celltype from deg where dataset = #{dataset} group by celltype order by celltype asc")
    List<String> findcelltypeList(@Param("dataset")String dataset);

    @Select("select * from deg where id = #{id}")
    Deg findById(@Param("id") int id);

    @Select("select gene from deg where gene like #{gene} group by gene order by gene asc")
    List<String> findGeneLike(@Param("gene")String gene);


    @Select("select * from deg where tissue = #{tissue} and dataset = #{dataset} and celltype = #{celltype} limit 1")
    List<Deg> findByTDC(@Param("tissue")String tissue, @Param("dataset")String dataset, @Param("celltype")String cellType);

    @Select("select gene from deg where tissue = #{tissue} and dataset = #{dataset} and celltype = #{celltype} order by gene asc")
    List<String> findListByTDC(@Param("tissue")String tissue, @Param("dataset")String dataset, @Param("celltype")String cellType);

}
