package com.example.singlecell.mapper;

import com.example.singlecell.model.Description;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DesMapper {


    @Select("select * from description")
    List<Description> findList();

}
