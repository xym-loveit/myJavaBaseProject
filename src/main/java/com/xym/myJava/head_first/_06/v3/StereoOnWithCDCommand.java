package com.xym.myJava.head_first._06.v3;

import com.xym.myJava.head_first._06.v2.Command;

/**
 * 打开音响命令
 *
 * @author xym
 * @create 2019-04-11 14:50
 */
public class StereoOnWithCDCommand implements Command {
    private Stereo stereo;

    public StereoOnWithCDCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        //打开音响
        this.stereo.on();
        //播放CD
        this.stereo.setCd();
        //设置音量
        this.stereo.setVolume(11);
    }

    @Override
    public void undo() {

    }
}
