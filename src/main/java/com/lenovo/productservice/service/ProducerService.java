package com.lenovo.productservice.service;

import java.util.List;
import java.util.UUID;

import com.lenovo.productservice.entity.Producer;
import com.lenovo.productservice.entity.param.CreateProducerParam;
import com.lenovo.productservice.repository.ProducerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {

  private final ProducerRepository producerRepository;

  public List<Producer> getProducers() {
    List<Producer> producers = producerRepository.findAll();
    log.debug("{} producers found", producers.size());
    return producers;
  }

  public Producer getProducer(String producerId) {
    return producerRepository.findById(producerId)
        .orElseThrow(() -> new RuntimeException("Producer not found with id: " + producerId));
  }

  public Producer createProducer(CreateProducerParam param) {
    validate(param.getName());
    var producer = new Producer();
    producer.setId(UUID.randomUUID().toString());
    producer.setName(param.getName());
    producer.setDisplayName(param.getDisplayName());
    producer.setDescription(param.getDescription());
    var saved = producerRepository.save(producer);
    log.debug("Producer successfully saved with ID: {}", saved.getId());
    return saved;
  }

  private void validate(String name) {
    var isExists = producerRepository.existsByName(name);
    if(isExists) {
      throw new RuntimeException(String.format("Producer with name %s already exists", name));
    }
  }
}
