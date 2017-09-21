package org.eleron.lris.niokr.dao;

import org.eleron.lris.niokr.model.Message;

import java.util.List;

public interface MessageDAO {

    void addMessage(Message message);
    void removeMessage(Long id);
    void updateMessage(Message message);
    List<Message> listMessage();
}
