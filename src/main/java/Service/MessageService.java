package Service;

import java.util.List;

import DAO.MessageDAO;
import Model.Message;

public class MessageService {
    public MessageDAO messageDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
    }

    public MessageService(MessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    //adding a message to the database
    public Message addMessage(Message message) {
        if(messageDAO.getMessageById(message.getMessage_id()) == null && !message.getMessage_text().isEmpty() && message.getMessage_text().length() < 255) {
            return messageDAO.insertMessage(message);
        }
        return null;
    }//end addMessage

    //return all messages in the database
    public List<Message> getAllMessages() {
        return messageDAO.getAllMessages();
    } //end getAllMessages

    //return message by id
    public Message getMessageById(int id) {
        if(messageDAO.getMessageById(id) != null ) {
        return messageDAO.getMessageById(id);
        }
        return null;
    }

    //delete a message
    public Message deleteMessage(int id) {
        if(messageDAO.getMessageById(id) != null) {
            messageDAO.deleteMessage(id);
            return messageDAO.getMessageById(id);
        }
        return null;
    } //end deleteMessage

    public Message updateMessage(int id, Message message) {
        if(messageDAO.getMessageById(id) != null && !message.getMessage_text().isEmpty() && message.getMessage_text().length() < 255) {
            messageDAO.updateMessage(id, message);
            return messageDAO.getMessageById(id);
        }
        return null;
    } //end updateMessage

    public List<Message> getAllMessagesForUser(int id) {
        return messageDAO.getAllMessagesForUser(id);
    } //end getAllMessagesFromUserByUID
}
