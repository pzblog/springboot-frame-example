package com.example.smail.service;

import com.example.smail.common.ServerResponse;
import com.example.smail.entity.Mail;

public interface ProduceService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
