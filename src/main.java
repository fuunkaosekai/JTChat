
import javax.swing.SwingUtilities;
import jtchat.IRCBot;
import jtchat.gui.Chatroom;


public class main {
    public static void main(String[] args){
        /*IRCBot ircbot = new IRCBot();
        ircbot.connect("irc.twitch.tv", 443, "megasonimon", "", "");
        ircbot.join("#append");
        ircbot.loop();*/
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Chatroom chatroom = new Chatroom();
                chatroom.setVisible(true);
            }
        });
    }
}
