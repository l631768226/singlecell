package com.example.singlecell.mapper;

import com.example.singlecell.model.Description;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DesMapper {


    @Select("select * from description")
    List<Description> findList();

    @Select("select tissue from description group by tissue")
    List<String> findTissue();

    @Select("select datasetname from description where tissue = #{tissue} group by datasetname")
    List<String> findDatasetName(@Param("tissue")String tissue);

    @Select("select * from description where datasetname = #{dataset} limit 1")
    Description findByDataset(@Param("dataset")String dataset);

    @Select("select * from description group by tissue order by databaseid")
    List<Description> findGroupByTissue();

    @Select("select * from description where tissue = #{tissue} group by datasetname order by databaseid")
    List<Description> findByTissueGroupByDataset(@Param("tissue")String tissue);

    @Select("select * from description group by tissue order by databaseid asc")
    List<Description> findListOrderByDID();
}
