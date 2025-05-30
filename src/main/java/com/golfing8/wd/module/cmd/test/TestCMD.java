package com.golfing8.wd.module.cmd.test;

import com.golfing8.kcommon.command.Cmd;
import com.golfing8.kcommon.command.CommandContext;
import com.golfing8.kcommon.command.MCommand;
import com.golfing8.wd.module.DebuggerModule;
import org.jetbrains.annotations.NotNull;

@Cmd(
        name = "test",
        description = "Test aspects of the game"
)
public class TestCMD extends MCommand<DebuggerModule> {
    @Override
    protected void onRegister() {
        addSubCommand(new TestAsyncChatCMD());
        addSubCommand(new TestMapCMD());
    }
}
