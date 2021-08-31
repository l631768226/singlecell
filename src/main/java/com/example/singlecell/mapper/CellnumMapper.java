package com.example.singlecell.mapper;

import com.example.singlecell.model.Cellnum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CellnumMapper {

    @Select("select * from cellnum where dataset = #{dataset} ORDER BY sample asc, celltype asc")
    List<Cellnum> findListByDataset(@Param("dataset")String dataset);
}
