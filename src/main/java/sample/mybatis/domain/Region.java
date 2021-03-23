
package sample.mybatis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Region implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  private String name;

  private String shortName;

  public Region(String name, String shortName) {
    this.name = name;
    this.shortName = shortName;
  }
}
