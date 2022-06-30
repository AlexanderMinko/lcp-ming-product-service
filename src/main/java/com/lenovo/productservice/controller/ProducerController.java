package com.lenovo.productservice.controller;

import java.util.List;

import com.lenovo.productservice.entity.Producer;
import com.lenovo.productservice.entity.param.CreateProducerParam;
import com.lenovo.productservice.service.ProducerService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("producers")
public class ProducerController {

  private final ProducerService producerService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<Producer> getProducers() {
    return producerService.getProducers();
  }

  @PostMapping
  public Producer createProducer(@RequestBody CreateProducerParam param) {
    return producerService.createProducer(param);
  }

}
