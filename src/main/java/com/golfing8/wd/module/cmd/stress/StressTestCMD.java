package com.golfing8.wd.module.cmd.stress;

import com.golfing8.kcommon.command.Cmd;
import com.golfing8.kcommon.command.MCommand;
import com.golfing8.wd.module.DebuggerModule;

@Cmd(
        name = "stresstest",
        description = "Run a stress test"
)
public class StressTestCMD extends MCommand<DebuggerModule> {
    @Override
    protected void onRegister() {
        addSubCommand(new StressTestLightingCMD());
    }
}
