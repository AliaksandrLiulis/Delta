package by.delta.service;

import by.delta.dto.MessageDto;

import java.util.Map;

public interface IMessageService {

    MessageDto createMessage(Map<String, String> resources);
}
