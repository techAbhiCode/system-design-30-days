// 1. Command Interface
interface ICommand {
    void execute();
    void undo();
}

// 2. The Receiver
class Light {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}

// 3. Concrete Command
class LightCommand implements ICommand {
    private Light light;

    public LightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() { light.on(); }

    @Override
    public void undo() { light.off(); }
}

// 4. The Invoker
class RemoteControl {
    private ICommand[] buttons;

    public RemoteControl(int size) {
        buttons = new ICommand[size];
    }

    // Dynamically assign commands to buttons
    public void setCommand(int index, ICommand command) {
        buttons[index] = command;
    }

    public void pressButton(int index) {
        if (buttons[index] != null) {
            buttons[index].execute();
        }
    }

    public void pressUndo(int index) {
        if (buttons[index] != null) {
            buttons[index].undo();
        }
    }
}

// 5. Client
public class Main {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        ICommand lightCmd = new LightCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl(4);
        
        // Map Button 0 to the Light
        remote.setCommand(0, lightCmd);

        // Turn ON and then Undo (Turn OFF)
        remote.pressButton(0); 
        remote.pressUndo(0);
    }
}