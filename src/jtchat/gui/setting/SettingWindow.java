
package jtchat.gui.setting;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import jtchat.gui.MainWindow;


public class SettingWindow extends JFrame{
    JTabbedPane tabPane;
    LogPane logPane;
    ChatroomSetPane chatSetPane;
    IRCSetPane ircSetPane;
    ProfilePane profilePane;
    private MainWindow mainWinRef;
    //MainWindow chatroomWindow;
    public SettingWindow(MainWindow mainWinRef){
        super("JTChat Setting");
        this.mainWinRef = mainWinRef;
        init(mainWinRef);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,500);
        this.setResizable(false);
        this.addWindowListener(new closeEventWindowListener());
    }
    
    private void init(MainWindow mainWinRef){
        
        
        tabPane = new JTabbedPane();
        this.add(tabPane);
        logPane = new LogPane();
        ircSetPane = new IRCSetPane();
        chatSetPane = new ChatroomSetPane(this);
        profilePane = new ProfilePane(this);
        tabPane.addTab("IRC Set", ircSetPane);
        tabPane.addTab("Chat Set", chatSetPane);
        tabPane.addTab("Profile", profilePane);
        tabPane.addTab("Log", logPane);
    }
    

    private class closeEventWindowListener extends WindowAdapter{
        public void windowClosing(WindowEvent e) {
            //apply setting
            //ircSetPane.save();
        }
    }
    

    //temp method
    public LogPane logPaneRef(){
        return logPane;
    }
    
    public void load(){
        ircSetPane.load();
        chatSetPane.load();
        
    }
    
    public void applyChangeToChatroom(){
        mainWinRef.applyChange();
    }
    

}