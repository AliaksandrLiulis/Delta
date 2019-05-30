package by.delta.service;

import by.delta.dto.MessageDto;
import org.springframework.security.core.Authentication;

import java.util.Map;

public interface IMessageService {

    MessageDto createMessage(Authentication authentication, MessageDto messageDto);
}
