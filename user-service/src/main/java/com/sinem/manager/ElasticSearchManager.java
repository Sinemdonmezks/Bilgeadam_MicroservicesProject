package com.sinem.manager;

import com.sinem.repository.entity.UserProfile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.sinem.constants.ApiUrls.*;

@FeignClient(name="elastic-service",
url="${myapplication.elastic-service.feign-client}",decode404 = true)
public interface ElasticSearchManager {

    @PostMapping(SAVE)
    public ResponseEntity<Void> save(@RequestBody UserProfile userProfile);

    @PostMapping(UPDATE)
    public ResponseEntity<Void> update(@RequestBody UserProfile userProfile);
    }
