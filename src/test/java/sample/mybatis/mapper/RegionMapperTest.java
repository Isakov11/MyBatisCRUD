package sample.mybatis.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import sample.mybatis.domain.Region;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
class RegionMapperTest {

  @Autowired
  private RegionMapper regionMapper;

  @Test
  void findAllTest() {
    LinkedList<Region> regionList = regionMapper.findAll();
    Region region = regionList.getFirst();
    assertThat(region.getId()).isEqualTo(1);
    assertThat(region.getName()).isEqualTo("Центральный федеральный округ");
    assertThat(region.getShortName()).isEqualTo("ЦФО");
    assertThat(region.getId()).isEqualTo(2);
    assertThat(region.getName()).isEqualTo("Северо-Западный федеральный округ");
    assertThat(region.getShortName()).isEqualTo("СЗФО");
  }

  @Test
  void createTest() {
    Region regionForCreate = new Region ("Южный федеральный округ","ЮФО");
    regionMapper.create(regionForCreate);
    LinkedList<Region> regionList = regionMapper.findAll();
    Region region = regionList.getLast();
    assertThat(region.getId()).isEqualTo(3);
    assertThat(region.getName()).isEqualTo("Южный федеральный округ");
    assertThat(region.getShortName()).isEqualTo("ЮФО");
  }

}
