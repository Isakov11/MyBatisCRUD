
package sample.mybatis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import sample.mybatis.domain.Region;
import sample.mybatis.mapper.RegionMapper;

import java.util.Collection;
import java.util.LinkedList;

@RequestMapping("/regions")
@RestController
public class RegionRestController {

  private final RegionMapper regionMapper;

  public RegionRestController(RegionMapper regionMapper) {
    this.regionMapper = regionMapper;
  }

  @GetMapping
  LinkedList<Region> getAllRegions() {
    return regionMapper.findAll();
  }

  @GetMapping("{id}")
  Region getRegionById(@PathVariable Long id) {
    return regionMapper.findRegionById(id);
  }

  @PostMapping
  HttpStatus createRegion(@RequestBody Region region) {
    regionMapper.create(region);
    return HttpStatus.CREATED;
  }

  @PostMapping("/update")
  HttpStatus updateRegion(@RequestBody Region region) {
    regionMapper.update(region);
    return HttpStatus.OK;
  }

  @DeleteMapping("{id}")
  void deleteRegion(@PathVariable Long id) {
    regionMapper.delete(id);
  }
}
