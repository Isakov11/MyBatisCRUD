
package sample.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import sample.mybatis.domain.Region;

import java.util.Collection;
import java.util.LinkedList;

@Mapper
public interface RegionMapper {

  @Select("select * from region")

  LinkedList<Region> findAll();


  @Select("select * from region WHERE id =#{id}")
  Region findRegionById(Long id);


  @Insert("insert into region (name,shortName) values (#{name},#{shortName})")
  void create(Region regionEntity);


  @Update("UPDATE region SET name=#{name}, shortName =#{shortName} WHERE id =#{id}")
  void update(Region regionEntity);


  @Delete("DELETE FROM region WHERE id =#{id}")
  void delete(Long id);
}
