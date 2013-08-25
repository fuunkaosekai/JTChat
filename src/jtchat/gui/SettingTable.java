
package jtchat.gui;

//singleton data structure

import java.awt.Color;
import java.awt.Font;

public class SettingTable {
    private static SettingTable ins = null;
    //private HashMap<String,String> settingMap;
    
    //IRC
    public String IRCserver = "irc.twitch.tv";
    public int IRCport = 443;
    public String IRCnickname = "world9918";
    public String IRCservpass="";
    public String IRCchannel="#world9918";
    
    //Chatroom
    public int ChatWidth = 160;
    public int ChatHeight = 330;
    public Color ChatBgColor = Color.decode("#000000");
    public int ChatNumOfLines = 20;
    public Color ChatTextColor = Color.decode("#FFFFFF");
    public Font ChatTextFont = new Font("Arial Unicode MS",Font.PLAIN,12);
    public Color ChatNickColor = Color.decode("#FFFF00");
    public Font ChatNickFont = new Font("Arial",Font.PLAIN,12);
    public boolean ChatAlwaysOnTop = false;
    public boolean ChatUseTiwtchColor = true;
    public Color ChatSysColor = Color.decode("#FF9999");
    public Font ChatSysFont = new Font("Arial",Font.PLAIN,12);
    

       
    public static SettingTable ins(){
        if(ins == null){
            ins = new SettingTable();
        }
        return ins;
    }


    
}
