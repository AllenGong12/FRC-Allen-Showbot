package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DriveCommand extends Command{
    
    private DrivetrainSubsystem drivetrainSubsystem;
    private DoubleSupplier rightStick;
    private DoubleSupplier leftStick;

    public DriveCommand(DrivetrainSubsystem drivetrainSubsystem, DoubleSupplier rightStick, DoubleSupplier leftStick){

        this.drivetrainSubsystem = drivetrainSubsystem;
        this.rightStick = rightStick;
        this.leftStick = leftStick;

        addRequirements(drivetrainSubsystem);
    }

    double cubePower(double input){

        return Math.pow(input, 3);

    }

    double deadZone (double input){

        if (Math.abs(input) <= Constants.DEADZONE_AMOUNT) return 0;

        return input;
        }

    public void execute(){

        drivetrainSubsystem.setDriveTrainMotorPower(cubePower(deadZone(leftStick.getAsDouble())), cubePower(deadZone(rightStick.getAsDouble())));

    }

}
