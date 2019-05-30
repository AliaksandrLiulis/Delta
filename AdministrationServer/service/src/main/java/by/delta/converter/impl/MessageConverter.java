package by.delta.converter.impl;

import by.delta.converter.IConverter;
import by.delta.dto.MessageDto;
import by.delta.model.Message;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class MessageConverter implements IConverter<Message, MessageDto> {

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Message dtoToModel(MessageDto messageDto) {
        Message message = new Message();
        modelMapper.map(messageDto, message);
        return message;
    }

    @Override
    public MessageDto modelToDto(Message message) {
        MessageDto messageDto = new MessageDto();
        modelMapper.map(message, messageDto);
        return messageDto;
    }

    @Override
    public Set<Message> dtoToModelList(Set<MessageDto> listDto) {
        Set<Message> listMessage = new LinkedHashSet<>();
        for (MessageDto messageDto : listDto) {
            listMessage.add(dtoToModel(messageDto));
        }
        return listMessage;
    }

    @Override
    public Set<MessageDto> modelToDtoList(Set<Message> listModel) {
        Set<MessageDto> listMessageDto = new LinkedHashSet<>();
        for (Message message : listModel) {
            listMessageDto.add(modelToDto(message));
        }
        return listMessageDto;
    }
}