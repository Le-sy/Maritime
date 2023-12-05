package com.example.app;

import com.example.app.fragment.SquareFragment;
import com.example.app.fragment.MessageFragment;
import com.example.app.fragment.ProfileFragment;

public class InitFragment {

    private SquareFragment squareFragment;
    private MessageFragment messageFragment;
    private ProfileFragment profileFragment;

    public void init(){
        squareFragment = new SquareFragment();
        messageFragment = new MessageFragment();
        profileFragment = new ProfileFragment();
    }


    public SquareFragment getPageSquare(){ return squareFragment;}

    public MessageFragment getPageMessage(){
        return messageFragment;
    }

    public ProfileFragment getPageProfile(){
        return profileFragment;
    }
}
