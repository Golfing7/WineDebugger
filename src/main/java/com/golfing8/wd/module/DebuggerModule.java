package com.golfing8.wd.module;

import com.golfing8.kcommon.module.Module;
import com.golfing8.kcommon.module.ModuleInfo;
import com.golfing8.wd.module.cmd.debug.DebugItemCMD;
import com.golfing8.wd.module.cmd.stress.StressTestCMD;
import com.golfing8.wd.module.item.DebugItemType;

@ModuleInfo(
        name = "debugger"
)
public class DebuggerModule extends Module {
    @Override
    public void onEnable() {
        addCommand(new StressTestCMD());
        addCommand(new DebugItemCMD());

        DebugItemType.init(this);
    }

    @Override
    public void onDisable() {

    }
}
