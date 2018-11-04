package designModel.命令模式;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Invoker {

    private Command command;

    public void action() {
        command.exe();
    }

}
