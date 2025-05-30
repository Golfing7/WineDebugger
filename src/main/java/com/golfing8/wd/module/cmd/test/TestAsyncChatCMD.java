package com.golfing8.wd.module.cmd.test;

import com.golfing8.kcommon.command.Cmd;
import com.golfing8.kcommon.command.CommandContext;
import com.golfing8.kcommon.command.MCommand;
import com.golfing8.kcommon.command.argument.CommandArguments;
import com.golfing8.wd.module.DebuggerModule;
import org.jetbrains.annotations.NotNull;

@Cmd(
        name = "asyncchat",
        description = "Test order of async packets using chat",
        async = true
)
public class TestAsyncChatCMD extends MCommand<DebuggerModule> {
    @Override
    protected void onRegister() {
        addArgument("messages", CommandArguments.POSITIVE_INTEGER, (sender) -> 15);
    }

    @Override
    protected void execute(@NotNull CommandContext context) {
        int messages = context.next();

        for (int i = 0; i < messages; i++) {
            context.getSender().sendMessage("Async chat " + i);
        }
    }
}
