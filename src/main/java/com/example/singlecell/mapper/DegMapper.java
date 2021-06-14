package com.example.singlecell.mapper;

import com.example.singlecell.model.Deg;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DegMapper {

    @Select("select * from deg where gene = #{gene}")
    List<Deg> findListByGene(@Param("gene")String gene);

}
