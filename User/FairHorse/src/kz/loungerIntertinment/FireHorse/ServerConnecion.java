package kz.loungerIntertinment.FireHorse;

import java.util.List;

public interface ServerConnecion {
    void setText(String setter, String getter, String text);
    String getText(String setter, String getter);
    List<String[]> getAllUser();
    boolean login (String identifier, String password);
    boolean doIHaveNewMessage (String setter);
    boolean signUp (String name, String login, String password);
}