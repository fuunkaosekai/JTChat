package jtchat.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class ChatMessage {
    private String messages;    //save all current chat msgs
    private SimpleAttributeSet chatAttr;
    
    //regular expression
    private Pattern nickPattern;
    private Pattern sysPattern;
    
    public enum Type{
        Text, Nick, Sys
    }
    
    
    public ChatMessage(){
        messages = "";
        chatAttr = new SimpleAttributeSet();
        
        nickPattern = Pattern.compile("(^|\\n)(\\[OP\\] )?(\\[(.)*\\] )?\\w+:");
        sysPattern = Pattern.compile("(^|\\n)\\[SYS\\] [^\\n]*");
    }

    
    
    
    public void add(String newMsg){
        messages += newMsg += "\n";
    }
    
    private void setAttribute(ChatMessage.Type type){
        Color color=null;
        Font font=null;
        switch(type){
            case Text:
                color = SettingTable.ins().ChatTextColor;
                font = SettingTable.ins().ChatTextFont;
                break;
            case Nick:
                color = SettingTable.ins().ChatNickColor;
                font = SettingTable.ins().ChatNickFont;
                break; 
            case Sys:
                color = SettingTable.ins().ChatSysColor;
                font = SettingTable.ins().ChatSysFont;
                break;
        }
        
        
        chatAttr.addAttribute(StyleConstants.CharacterConstants.Foreground, color);
        chatAttr.addAttribute(StyleConstants.FontConstants.FontFamily, font.getFamily());
        chatAttr.addAttribute(StyleConstants.FontConstants.FontSize, font.getSize());
    }
    
    public void setText(final JTextPane chatPane){
        //remove old msgs
        
        
        //
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                try{
                    //layout all text with chat text first
                    ChatMessage.this.setAttribute(Type.Text);
                    Document doc = chatPane.getDocument();
                    doc.remove(0, doc.getLength());
                    doc.insertString(0, ChatMessage.this.messages, chatAttr);
                    
                    //layout nicknames
                    Matcher mx = nickPattern.matcher(messages);
                    ChatMessage.this.setAttribute(Type.Nick);
                    while(mx.find()){
                        doc.remove(mx.start(), mx.end()-mx.start());
                        doc.insertString(mx.start(), mx.group(), chatAttr);
                    }
                    
                    //layout sys msgs
                    mx = sysPattern.matcher(messages);
                    ChatMessage.this.setAttribute(Type.Sys);
                    while(mx.find()){
                        doc.remove(mx.start(), mx.end()-mx.start());
                        doc.insertString(mx.start(), mx.group(), chatAttr);
                    }
                    
                    
                } catch (BadLocationException ex) {
                    //need improved
                    System.err.printf("BadLocationException %d\n",ex.offsetRequested());
                }
            }
        });
         
    }
    
    
}
