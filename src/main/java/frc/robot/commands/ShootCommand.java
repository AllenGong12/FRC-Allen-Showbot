package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ShooterSubsystem;

public class ShootCommand extends Command {
    
    private ShooterSubsystem shooterSubsystem;
    private JoystickButton joystickButton;

    public ShootCommand(ShooterSubsystem shooterSubsystem, JoystickButton joystickButton){
        this.shooterSubsystem = shooterSubsystem;
        this.joystickButton = joystickButton;
        
        addRequirements(shooterSubsystem);
    }

    public void execute(){

        if (joystickButton.getAsBoolean()){

            shooterSubsystem.setMotorPower(1, "safety button is pressed, spin motors to shoot balls");

        }
        else{

            shooterSubsystem.setMotorPower(0, "safety button is not pressed, stop motors");

        }
    }

    public void end(boolean interrupted){    

        shooterSubsystem.setMotorPower(0,"command has ended");

    }

}
