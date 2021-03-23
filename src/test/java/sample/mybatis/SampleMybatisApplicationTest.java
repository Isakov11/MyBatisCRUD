
package sample.mybatis;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import sample.mybatis.domain.Region;
import sample.mybatis.mapper.RegionMapper;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SampleMybatisApplicationTest {

  @Autowired
  private TestRestTemplate restTemplate;
  //@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  @Test
  @Order(1)
  void findAllTest() {
    @SuppressWarnings("unchecked")
    LinkedList<Map<String, Object>> bodyList = this.restTemplate.getForObject("/regions", LinkedList.class);
    Map<String, Object> body = bodyList.getFirst();
    assertThat(body).hasSize(3).containsEntry("id", 1).containsEntry("name", "Центральный федеральный округ")
        .containsEntry("shortName", "ЦФО");
  }
  //@DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  @Test
  @Order(2)
  void findByIdTest() {
    @SuppressWarnings("unchecked")
    Map<String, Object> body = this.restTemplate.getForObject("/regions/{id}", Map.class, "1");
    assertThat(body).hasSize(3).containsEntry("id", 1).containsEntry("name", "Центральный федеральный округ")
            .containsEntry("shortName", "ЦФО");
  }

  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  @Test
  @Order(3)
  void createTest() {
    @SuppressWarnings("unchecked")
    Region region = new Region ("Южный федеральный округ","ЮФО");
    Map<String,Object> requestMap = new HashMap();
    requestMap.put("region",region);
    ResponseEntity<HttpStatus> response =  this.restTemplate.postForEntity("/regions",region, HttpStatus.class);
    assertThat(response.getBody()).isEqualTo(HttpStatus.CREATED);

    LinkedList<Map<String, Object>> bodyList = this.restTemplate.getForObject("/regions", LinkedList.class);
    Map<String, Object> body = bodyList.getLast();
    assertThat(body).hasSize(3).containsEntry("id", 3).containsEntry("name", "Южный федеральный округ")
            .containsEntry("shortName", "ЮФО");
  }

  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  @Test
  @Order(4)
  void updateTest() {
    @SuppressWarnings("unchecked")
    Region region = new Region (1L,"Ц2","ЦФО2");
    ResponseEntity<HttpStatus> response =  this.restTemplate.postForEntity("/regions/update",region, HttpStatus.class);
    assertThat(response.getBody()).isEqualTo(HttpStatus.OK);

    LinkedList<Map<String, Object>> bodyList = this.restTemplate.getForObject("/regions", LinkedList.class);
    Map<String, Object> body = bodyList.getFirst();
    assertThat(body).hasSize(3).containsEntry("id", 1).containsEntry("name", "Ц2")
            .containsEntry("shortName", "ЦФО2");
  }

  @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
  @Test
  @Order(5)
  void deleteTest() {

    this.restTemplate.delete("/regions/1");
    LinkedList<Map<String, Object>> bodyList = this.restTemplate.getForObject("/regions", LinkedList.class);
    Map<String, Object> body = bodyList.getFirst();
    assertThat(body).hasSize(3).containsEntry("id", 2).containsEntry("name", "Северо-Западный федеральный округ")
            .containsEntry("shortName", "СЗФО");
  }
}
